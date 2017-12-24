package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.mapper.ManagePermissionMapper;
import com.practice.mapper.ManageRolePermissionMapper;
import com.practice.po.ManagePermission;
import com.practice.po.ManagePermissionExample;
import com.practice.result.JsonResult;
import com.practice.service.PermissionService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/23 20:20
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private ManagePermissionMapper permissionMapper;
    @Resource
    private ManageRolePermissionMapper rolePermissionMapper;

    /**
     * List permission
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listPermission(PageSearchParam param) {

        if(param.getPageStatus()==1){
            PageHelper.startPage(param.getPageIndex(),param.getPageSize());
        }

        ManagePermissionExample permissionExample = new ManagePermissionExample();

        ManagePermissionExample.Criteria criteria = permissionExample.createCriteria().andDelflagEqualTo(0);

        String filed1 = "name";

        if(param.getFiled(filed1)!=null){

            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed1)));
        }

        permissionExample.setOrderByClause("create_time desc");

        List<ManagePermission> managePermissions = permissionMapper.selectByExample(permissionExample);

        PageInfo<ManagePermission> managePermissionPageInfo = new PageInfo<>(managePermissions);

        return JsonResult.success(managePermissionPageInfo);

    }

    /**
     * Get permission
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getPermission(Long id) {
        return JsonResult.success(permissionMapper.selectByPrimaryKey(id));
    }

    /**
     * Add permissison
     *
     * @param token
     * @param managePermission
     * @return
     */
    @Override
    public JsonResult addPermission(String token, ManagePermission managePermission) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        managePermission.setId(null);

        Date date = new Date();

        managePermission.setCreateTime(date);

        managePermission.setUpdateTime(date);

        managePermission.setUpdateUser(tokeUser.getId());

        managePermission.setStatus(1);

        managePermission.setDelflag(0);

        permissionMapper.insertSelective(managePermission);

        return JsonResult.success(managePermission);
    }

    /**
     * Update permission
     *
     * @param token
     * @param managePermission
     * @return
     */
    @Override
    public JsonResult updatePermission(String token, ManagePermission managePermission) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        managePermission.setUpdateTime(new Date());

        managePermission.setUpdateUser(tokeUser.getId());

        permissionMapper.updateByPrimaryKeySelective(managePermission);

        return JsonResult.success(managePermission);
    }

    /**
     * List permission usable
     * @param roleId
     * @return
     */
    @Override
    public JsonResult listPermissionUsable(Long roleId) {
        ManagePermissionExample permissionExample = new ManagePermissionExample();

        permissionExample.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(1);

        List<ManagePermission> managePermissions = permissionMapper.selectByExample(permissionExample);

        if(roleId!=null){
            List<Long> longs = rolePermissionMapper.selectByRoleId(roleId);
            for (ManagePermission managePermission : managePermissions) {

                if(longs.contains(managePermission.getId())){
                    managePermission.setChecked(1);
                }else{
                    managePermission.setChecked(0);
                }

            }
        }

        return JsonResult.success(managePermissions);
    }
}
