package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageRoleMapper;
import com.practice.mapper.ManageRoleNavMapper;
import com.practice.mapper.ManageRolePermissionMapper;
import com.practice.mapper.ManageUserRoleMapper;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.RoleService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/23 20:19
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Resource
    private ManageRoleMapper roleMapper;
    @Resource
    private ManageRoleNavMapper roleNavMapper;
    @Resource
    private ManageRolePermissionMapper rolePermissionMapper;
    @Resource
    private ManageUserRoleMapper userRoleMapper;

    /**
     * List role
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listRole(PageSearchParam param) {

        if(param.getPageIndex()==1){
            PageHelper.startPage(param.getPageIndex(),param.getPageSize());
        }

        ManageRoleExample roleExample = new ManageRoleExample();

        ManageRoleExample.Criteria criteria = roleExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name";

        if(param.getFiled(filed)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed)));
        }

        roleExample.setOrderByClause("create_time desc");

        List<ManageRole> manageRoles = roleMapper.selectByExample(roleExample);

        PageInfo<ManageRole> manageRolePageInfo = new PageInfo<>(manageRoles);

        return JsonResult.success(manageRolePageInfo);
    }

    /**
     * Get role
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getRole(Long id) {
        return JsonResult.success(roleMapper.selectByPrimaryKey(id));
    }

    /**
     * Add role
     *
     * @param token
     * @param manageRole
     * @return
     */
    @Override
    public JsonResult addRole(String token, ManageRole manageRole) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        manageRole.setId(null);

        manageRole.setStatus(1);

        manageRole.setDelflag(0);

        Date date = new Date();

        manageRole.setCreateTime(date);

        manageRole.setUpdateTime(date);

        manageRole.setUpdateUser(tokeUser.getId());

        roleMapper.insertSelective(manageRole);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update role
     *
     * @param token
     * @param manageRole
     * @return
     */
    @Override
    public JsonResult updateRole(String token, ManageRole manageRole) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        manageRole.setUpdateUser(tokeUser.getId());

        manageRole.setUpdateTime(new Date());

        roleMapper.updateByPrimaryKeySelective(manageRole);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List role usable
     *
     * @return
     */
    @Override
    public JsonResult listRoleUsable(Long userId) {

        ManageRoleExample roleExample = new ManageRoleExample();

        roleExample.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(1);

        List<ManageRole> manageRoles = roleMapper.selectByExample(roleExample);

        List<Long> list = userRoleMapper.selectByUserId(userId);

        for (ManageRole manageRole : manageRoles) {

            if(list.contains(manageRole.getId())){
                manageRole.setChecked(1);
            }else{
                manageRole.setChecked(0);
            }
        }



        return JsonResult.success(manageRoles);
    }

    /**
     * List role menu
     *
     * @return
     */
    @Override
    public JsonResult listRoleMenuId(Long id) {

        List<Long> list = roleNavMapper.selectByRoleId(id);


        return JsonResult.success(list);
    }

    /**
     * List role permission
     *
     * @return
     */
    @Override
    public JsonResult listRolePermissionId(Long id) {

        List<Long> list = rolePermissionMapper.selectByRoleId(id);

        return JsonResult.success(list);
    }

    /**
     * Save role nva and permission
     *
     * @param token
     * @param id
     * @param navs
     * @param pers
     * @return
     */
    @Override
    public JsonResult saveRoleNavAndPermission(String token, Long id, String navs, String pers) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);



        roleNavMapper.deleteByRoleId(id);

        String[] split = navs.split(",");

        if(StringUtils.isNotBlank(navs)){
            ManageRoleNav manageRoleNav = new ManageRoleNav();

            for (String s : split) {

                String[] split1 = s.split("_");

                manageRoleNav = new ManageRoleNav();

                manageRoleNav.setRoleId(id);

                manageRoleNav.setNavId(Long.valueOf(split1[0]));

                manageRoleNav.setNavPid(Long.valueOf(split1[1]));

                roleNavMapper.insertSelective(manageRoleNav);

            }
        }


        rolePermissionMapper.deleteByRoleId(id);

        if(StringUtils.isNotBlank(pers)){
            ManageRolePermission manageRolePermission = new ManageRolePermission();

            String[] split1 = pers.split(",");

            for (String s : split1) {

                manageRolePermission.setRoleId(id);

                manageRolePermission.setPermissionId(Long.valueOf(s));

                rolePermissionMapper.insertSelective(manageRolePermission);
            }

        }




        return JsonResult.success(OperateEnum.SUCCESS);
    }
}
