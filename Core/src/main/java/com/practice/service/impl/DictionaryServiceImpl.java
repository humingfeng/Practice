package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.DicParentEnum;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageDictionaryMapper;
import com.practice.po.ManageDictionary;
import com.practice.po.ManageDictionaryExample;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.DictionaryService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/23 16:02
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private CacheService cacheService;
    @Resource
    private ManageDictionaryMapper dictionaryMapper;

    /**
     * list dictionary
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listDictionary(PageSearchParam param) {

        if (param.getPageStatus() == 1) {
            PageHelper.startPage(param.getPageIndex(), param.getPageSize());
        }

        String filed1 = "parentId", filed2 = "name";

        ManageDictionaryExample dictionaryExample = new ManageDictionaryExample();

        ManageDictionaryExample.Criteria criteria = dictionaryExample.createCriteria().andDelflagEqualTo(0).andParentIdNotEqualTo(0L);

        if(param.getFiled(filed1)!=null){
            criteria.andParentIdEqualTo(Long.valueOf(param.getFiled(filed1)));
        }

        if(param.getFiled(filed2)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed2)));
        }

        dictionaryExample.setOrderByClause("create_time desc");

        List<ManageDictionary> manageDictionaries = dictionaryMapper.selectByExample(dictionaryExample);

        PageInfo<ManageDictionary> manageDictionaryPageInfo = new PageInfo<>(manageDictionaries);

        return JsonResult.success(manageDictionaryPageInfo);
    }

    /**
     * add dictionary
     *
     * @param token
     * @param dictionary
     * @return
     */
    @Override
    public JsonResult addDictionary(String token, ManageDictionary dictionary) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        dictionary.setId(null);

        dictionary.setKeyword(DicParentEnum.stateOf(dictionary.getParentId()));

        dictionary.setStatus(1);

        dictionary.setDelflag(0);

        Date date = new Date();

        dictionary.setUpdateTime(date);

        dictionary.setCreateTime(date);

        dictionary.setUpdateUser(tokeUser.getId());

        dictionaryMapper.insertSelective(dictionary);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * get dictionary
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getDictionary(Long id) {
        ManageDictionary manageDictionary = dictionaryMapper.selectByPrimaryKey(id);
        return JsonResult.success(manageDictionary);
    }

    /**
     * update dictionary
     *
     * @param token
     * @param dictionary
     * @return
     */
    @Override
    public JsonResult updateDictionary(String token, ManageDictionary dictionary) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        dictionary.setUpdateTime(new Date());

        dictionary.setUpdateUser(tokeUser.getId());

        dictionaryMapper.updateByPrimaryKeySelective(dictionary);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * reset dictionary chace
     *
     * @return
     */
    @Override
    public JsonResult resetDictionaryCache() {

        List<DicParentEnum> all = DicParentEnum.getAll();

        for (DicParentEnum dicParentEnum : all) {

            List<ManageDictionary> list = dictionaryMapper.selectByParentId(dicParentEnum.getId());

            cacheService.resetDictionary(dicParentEnum,list);

        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * list dictionary by enum
     *
     * @param dicParentEnum
     * @return
     */
    @Override
    public List<ManageDictionary> listDictionaryByEnumFromCache(DicParentEnum dicParentEnum) {

        List<ManageDictionary> list = cacheService.listMangeDictionaryByEnum(dicParentEnum);

        if(list!=null){
            return list;
        }

        list = dictionaryMapper.selectByParentId(dicParentEnum.getId());

        cacheService.resetDictionary(dicParentEnum,list);

        return list;
    }

    /**
     * Get dictionary PO
     *
     * @param id
     * @return
     */
    @Override
    public ManageDictionary getDictionaryPO(Long id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }
}
