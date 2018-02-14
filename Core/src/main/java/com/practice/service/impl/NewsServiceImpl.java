package com.practice.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.practice.dto.NewsDto;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.NewsService;
import com.practice.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xushd on 2018/2/14 11:04
 */
@Service
public class NewsServiceImpl implements NewsService {


    @Value("${APPCODE}")
    private String APPCODE;
    @Value("${NEWS_URL}")
    private String NEWS_URL;

    @Resource
    private CacheService cacheService;
    /**
     * List news
     *
     * @param pageIndex
     * @return
     */
    @Override
    public JsonResult listNews(Integer pageIndex) {

        List<NewsDto> newsDtos = cacheService.listNews(pageIndex);

        if(newsDtos==null){
            if(pageIndex>1){
                return JsonResult.success(new JSONArray());
            }
            //获取最新10条 后 开启异步同步新闻
            JSONObject pagebean = getNews(1);

            int allPages = pagebean.getIntValue("allPages");
            List<NewsDto> list = getNewsDtoList(pagebean);

            cacheService.setNewsList(1,list);


            //开启异步获取

            syncGetAllNews(allPages);

            return JsonResult.success(list);

        }

        return JsonResult.success(newsDtos);
    }




    /**
     * 异步同步新闻信息
     */
    private void syncGetAllNews(int allPages){

        for (int i = 2; i <= allPages; i++) {
            Thread thread = new Thread(new ThreadChanle(i),"news"+i);
            thread.start();

        }
    }

    /**
     * 接口获取数据转换成NewsDto
     * @param pagebean
     * @return
     */
    private List<NewsDto> getNewsDtoList(JSONObject pagebean){
        List<NewsDto> list = new ArrayList<>();

        JSONArray contentlist = pagebean.getJSONArray("contentlist");
        for (Object o : contentlist) {
            JSONObject obj = (JSONObject) o;
            NewsDto dto = new NewsDto();
            dto.setId(obj.getString("id"));
            dto.setContent(obj.getString("html"));
            dto.setLink(obj.getString("link"));
            dto.setSource(obj.getString("source"));
            dto.setPubDate(obj.getString("pubDate"));
            dto.setTitle(obj.getString("title"));
            List<String> imgArry = new ArrayList<>();
            if(obj.getBoolean("havePic")){
                JSONArray imageurls = obj.getJSONArray("imageurls");
                for (Object imageurl : imageurls) {
                    JSONObject objImg = (JSONObject) imageurl;
                    imgArry.add(objImg.getString("url"));
                }
                dto.setPic(imgArry.get(0));

            }else{
                dto.setPic("");
            }
            dto.setImgArry(imgArry);
            list.add(dto);
        }
        return list;
    }

    /**
     * 获取新闻
     * @param pageIndex
     * @return
     */
    private JSONObject getNews(int pageIndex){
        Map<String, String> headers = new HashMap<>();

        headers.put("Authorization", "APPCODE " + APPCODE);
        Map<String, String> querys = new HashMap<>();
        querys.put("channelId", "5572a108b3cdc86cf39001d7");
        querys.put("channelName", "");
        querys.put("maxResult", "10");
        querys.put("needAllList", "1");
        querys.put("needContent", "0");
        querys.put("needHtml", "1");
        querys.put("page", String.valueOf(pageIndex));
        querys.put("title", "");

        try {

            HttpResponse response = HttpUtils.doGet(NEWS_URL, "/newsList", "GET", headers, querys);
            //获取response的body
            String resposeBody = EntityUtils.toString(response.getEntity());
            JSONObject result = JSON.parseObject(resposeBody);
            JSONObject showapi_res_body = result.getJSONObject("showapi_res_body");
            JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
            return pagebean;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 内部类线程类
     */
    class ThreadChanle implements Runnable {
        public ThreadChanle(int pageIndex) {
            this.pageIndex = pageIndex;
        }
        private int pageIndex;
        @Override
        public void run() {
            try {
                JSONObject news = getNews(pageIndex);
                if(news!=null){
                    List<NewsDto> newsDtoList = getNewsDtoList(news);
                    cacheService.setNewsList(pageIndex,newsDtoList);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
