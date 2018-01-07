package com.practice.manage.controller;

import com.practice.manage.service.UploadService;
import com.practice.po.ManageActivity;
import com.practice.po.ManageActivitySign;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.utils.CommonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Utils controller
 *
 * @author Xushd  2017/12/31 18:03
 */
@RestController
public class UtilsController {

    @Resource
    private UploadService uploadService;
    @Resource
    private ActivityService activityService;
    /**
     * Upload file
     * @param file
     * @param dir
     * @return
     */
    @RequestMapping(value = "/upload/img/{dir}")
    public JsonResult uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
                                      @PathVariable String dir) {
        return uploadService.uploadImg(file,dir + "/");
    }


    @RequestMapping(value = "/download/ercode/{activityId}/{type}")
    public void downloadErcode(HttpServletResponse response,
                               @PathVariable Long activityId,
                               @PathVariable int type) throws UnsupportedEncodingException {



        ManageActivitySign activitySign = activityService.getActivitySign(activityId);

        ManageActivity activity = activityService.getActivityManagePO(activityId);


        String url = "",fileName = "";
        if(type == 1 && StringUtils.isNotBlank(activitySign.getSignInErcode())){

            url = activitySign.getSignInErcode();

            fileName = activity.getName()+"签到二维码";

        }else if(type==2&& StringUtils.isNotBlank(activitySign.getSignOutErcode())){

            url = activitySign.getSignOutErcode();
            fileName = activity.getName()+"签退二维码";
        }

        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");

        response.reset();

        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" +downloadFielName+".png");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ServletOutputStream out = null;
        try {

            out = response.getOutputStream();

            InputStream picInputStream = CommonUtils.createPicInputStream(url);

            byte[] bytes = IOUtils.toByteArray(picInputStream);

            out.write(bytes);

            out.flush();

            out.close();

            picInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
