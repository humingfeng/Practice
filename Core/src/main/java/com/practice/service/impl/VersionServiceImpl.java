package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.VersionItemDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageVersionItemMapper;
import com.practice.mapper.ManageVersionMapper;
import com.practice.po.ManageVersion;
import com.practice.po.ManageVersionExample;
import com.practice.po.ManageVersionItem;
import com.practice.po.ManageVersionItemExample;
import com.practice.result.JsonResult;
import com.practice.service.VersionService;
import com.practice.utils.JsonUtils;
import com.practice.utils.TimeUtils;
import com.practice.vo.VersionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/23 14:56
 */
@Service
public class VersionServiceImpl implements VersionService {

    @Resource
    private ManageVersionMapper versionMapper;
    @Resource
    private ManageVersionItemMapper versionItemMapper;

    /**
     * List version
     *
     * @return
     */
    @Override
    public List<VersionVO> listVersion() {

        List<VersionVO> list = new ArrayList<>();

        PageHelper.startPage(1,5);

        ManageVersionExample versionExample = new ManageVersionExample();

        versionExample.setOrderByClause("create_time desc");

        List<ManageVersion> manageVersions = versionMapper.selectByExample(versionExample);

        ManageVersionItemExample versionItemExample = new ManageVersionItemExample();

        for (ManageVersion manageVersion : manageVersions) {

            VersionVO versionVO = new VersionVO();

            versionVO.setId(manageVersion.getId());
            versionVO.setAuthor(manageVersion.getLeader());
            versionVO.setTime(TimeUtils.getDateString(manageVersion.getCreateTime()));
            versionVO.setTitle(manageVersion.getVersionGeneral());
            versionVO.setVersion(manageVersion.getVersionNum());

            List<VersionVO> children = new ArrayList<>();

            versionItemExample.clear();

            versionItemExample.createCriteria().andVersionIdEqualTo(manageVersion.getId());

            List<ManageVersionItem> manageVersionItems = versionItemMapper.selectByExample(versionItemExample);

            for (ManageVersionItem manageVersionItem : manageVersionItems) {
                VersionVO child = new VersionVO();

                child.setId(manageVersionItem.getId());
                child.setAuthor(manageVersionItem.getAuthor());
                child.setTime("");
                child.setTitle(manageVersionItem.getItem());
                child.setVersion(manageVersion.getVersionNum());

                children.add(child);

            }

            versionVO.setItems(children);

            list.add(versionVO);

        }

        return list;
    }

    /**
     * List vertsion by param
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listVersion(PageSearchParam param) {

        if(param.getPageIndex()==1){
            PageHelper.startPage(param.getPageIndex(),param.getPageSize());
        }

        ManageVersionExample versionExample = new ManageVersionExample();

        String filed = "time";

        String time = param.getFiled(filed);

        if(time!=null){

            String[] split = time.split(" - ");

            Date dateBegin = TimeUtils.getDateFromStringShort(split[0]);
            Date dateEnd= TimeUtils.getDateFromStringShort(split[1]);
            versionExample.createCriteria().andCreateTimeBetween(dateBegin,dateEnd);

        }


        versionExample.setOrderByClause("create_time desc");

        List<ManageVersion> manageVersions = versionMapper.selectByExample(versionExample);

        PageInfo<ManageVersion> manageVersionPageInfo = new PageInfo<>(manageVersions);

        return JsonResult.success(manageVersionPageInfo);
    }

    /**
     * Get version
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getVersion(Long id) {
        return JsonResult.success(versionMapper.selectByPrimaryKey(id));
    }

    /**
     * Add version
     *
     * @param token
     * @param manageVersion
     * @return
     */
    @Override
    public JsonResult addVersion(String token, ManageVersion manageVersion) {

        manageVersion.setId(null);

        manageVersion.setCreateTime(new Date());

        versionMapper.insertSelective(manageVersion);

        if (StringUtils.isNotBlank(manageVersion.getItems())){

            List<VersionItemDTO> versionItemDTOS = JsonUtils.jsonToList(manageVersion.getItems(), VersionItemDTO.class);

            for (VersionItemDTO versionItemDTO : versionItemDTOS) {

                ManageVersionItem manageVersionItem = new ManageVersionItem();

                manageVersionItem.setVersionId(manageVersion.getId());

                manageVersionItem.setItem(versionItemDTO.getTitle());

                manageVersionItem.setAuthor(versionItemDTO.getAuthor());

                versionItemMapper.insertSelective(manageVersionItem);

            }


        }


        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update version
     *
     * @param token
     * @param manageVersion
     * @return
     */
    @Override
    public JsonResult updateVersion(String token, ManageVersion manageVersion) {

        versionMapper.updateByPrimaryKeySelective(manageVersion);


        ManageVersionItemExample versionItemExample = new ManageVersionItemExample();

        versionItemExample.createCriteria().andVersionIdEqualTo(manageVersion.getId());

        versionItemMapper.deleteByExample(versionItemExample);


        if (StringUtils.isNotBlank(manageVersion.getItems())){

            List<VersionItemDTO> versionItemDTOS = JsonUtils.jsonToList(manageVersion.getItems(), VersionItemDTO.class);

            for (VersionItemDTO versionItemDTO : versionItemDTOS) {

                ManageVersionItem manageVersionItem = new ManageVersionItem();

                manageVersionItem.setVersionId(manageVersion.getId());

                manageVersionItem.setItem(versionItemDTO.getTitle());

                manageVersionItem.setAuthor(versionItemDTO.getAuthor());

                versionItemMapper.insertSelective(manageVersionItem);

            }


        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Delete version
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteVersion(Long id) {

        versionMapper.deleteByPrimaryKey(id);

        ManageVersionItemExample versionItemExample = new ManageVersionItemExample();

        versionItemExample.createCriteria().andVersionIdEqualTo(id);

        versionItemMapper.deleteByExample(versionItemExample);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List version item
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult listVersionItem(Long id) {
        ManageVersionItemExample versionItemExample = new ManageVersionItemExample();

        versionItemExample.createCriteria().andVersionIdEqualTo(id);

        return JsonResult.success(versionItemMapper.selectByExample(versionItemExample));
    }
}
