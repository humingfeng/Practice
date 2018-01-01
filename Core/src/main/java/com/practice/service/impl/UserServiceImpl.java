package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.NavDTO;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.AuthEnum;
import com.practice.enums.ConstantEnum;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.UserService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Xushd on 2017/12/22 16:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private CacheService cacheService;
    @Resource
    private ManageNavMapper navMapper;
    @Resource
    private ManagePermissionMapper permissionMapper;
    @Resource
    private ManageUserMapper userMapper;
    @Resource
    private ManageUserRoleMapper userRoleMapper;
    @Resource
    private ManageRolePermissionMapper rolePermissionMapper;
    @Resource
    private ManageRoleNavMapper roleNavMapper;


    /**
     * User logout
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult logoutUser(String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        Long tokeUserId = tokeUser.getId();

        cacheService.delManageUserNavs(tokeUserId);


        return JsonResult.success(OperateEnum.SUCCESS);
    }


    /**
     * List user navs list
     *
     * @param id
     * @return
     */
    @Override
    public void createUserNavsAndPermissionCache(Long id) {

        List<Long> longs = userRoleMapper.selectByUserId(id);

        List<Long> pers = new ArrayList<>();
        List<Long> navs = new ArrayList<>();
        for (Long roleId : longs) {
            List<Long> permissions = rolePermissionMapper.selectByRoleId(roleId);
            for (Long perId : permissions) {
                if (!pers.contains(perId)) {
                    pers.add(perId);
                }
            }
            List<Long> menus = roleNavMapper.selectByRoleId(roleId);
            for (Long menuId : menus) {
                if (!navs.contains(menuId)) {
                    navs.add(menuId);
                }
            }

        }
        Map<Long, NavDTO> navParent = new HashMap<>();
        for (Long navId : navs) {

            ManageNav manageNav = navMapper.selectByPrimaryKey(navId);
            if (manageNav.getDelflag() == 1 || manageNav.getStatus() == 0) {
                continue;
            }
            NavDTO child = new NavDTO(manageNav.getName(), manageNav.getIcon(), manageNav.getUrl(), false);

            Long parentId = manageNav.getParentId();

            if (!navParent.containsKey(parentId)) {

                ManageNav manageNavParent = navMapper.selectByPrimaryKey(parentId);

                NavDTO navDTO = new NavDTO(manageNavParent.getName(), manageNavParent.getIcon(), manageNavParent.getUrl(), false);

                List<NavDTO> children = new ArrayList<>();

                children.add(child);

                navDTO.setChildren(children);

                navParent.put(parentId, navDTO);
            } else {
                navParent.get(parentId).getChildren().add(child);
            }
        }

        List<NavDTO> navList = new ArrayList<>();
        for (Map.Entry<Long, NavDTO> longNavDTOEntry : navParent.entrySet()) {
            navList.add(longNavDTOEntry.getValue());
        }

        cacheService.setManageUserNavs(navList, id);

        List<String> permissionList = new ArrayList<>();
        for (Long perId : pers) {

            ManagePermission managePermission = permissionMapper.selectByPrimaryKey(perId);

            if (managePermission.getDelflag() == 1 || managePermission.getStatus() == 0) {
                continue;
            }
            permissionList.add(managePermission.getUrl());
        }

        cacheService.setManageUserPermission(permissionList, id);

    }

    /**
     * List user permission
     *
     * @param token
     * @return
     */
    @Override
    public List<String> listUserPermission(String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        Long tokeUserId = tokeUser.getId();

        List<String> manageUserPermission = cacheService.getManageUserPermission(tokeUserId);

        return manageUserPermission;
    }

    /**
     * List  user nav
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult listUserNav(String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        Long tokeUserId = tokeUser.getId();

        List<NavDTO> cacheServiceManageUserNavs = cacheService.getManageUserNavs(tokeUserId);

        if (cacheServiceManageUserNavs == null) {
            return JsonResult.error(AuthEnum.TIME_OUT);
        }

        return JsonResult.success(cacheServiceManageUserNavs);
    }

    /**
     * List user
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listUser(PageSearchParam param) {

        if (param.getPageStatus() == 1) {
            PageHelper.startPage(param.getPageIndex(), param.getPageSize());
        }
        ManageUserExample userExample = new ManageUserExample();

        ManageUserExample.Criteria criteria = userExample.createCriteria().andDelflagEqualTo(0);

        String filed1 = "account", filed2 = "phone";

        if (param.getFiled(filed1) != null) {
            criteria.andAccountLike(CommonUtils.getLikeSql(param.getFiled(filed1)));
        }

        if (param.getFiled(filed2) != null) {
            criteria.andPhoneEqualTo(Long.valueOf(param.getFiled(filed2)));
        }

        userExample.setOrderByClause("update_time desc");


        List<ManageUser> manageUsers = userMapper.selectByExample(userExample);

        PageInfo<ManageUser> manageUserPageInfo = new PageInfo<>(manageUsers);

        return JsonResult.success(manageUserPageInfo);
    }

    /**
     * Add user
     *
     * @param manageUser
     * @param token
     * @return
     */
    @Override
    public JsonResult addUser(ManageUser manageUser, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);


        ManageUserExample userExample = new ManageUserExample();

        userExample.createCriteria().andDelflagEqualTo(0).andPhoneEqualTo(manageUser.getPhone());

        long l = userMapper.countByExample(userExample);

        if (l > 0) {
            return JsonResult.error(OperateEnum.USER_PHONE_EXIST);
        }


        manageUser.setId(null);

        manageUser.setStatus(0);

        manageUser.setDelflag(0);

        Date date = new Date();

        manageUser.setUpdateTime(date);

        manageUser.setCreateTime(date);

        manageUser.setUpdateId(tokeUser.getId());

        manageUser.setUpdateUser("");

        if (manageUser.getSex() == 1) {
            manageUser.setHeadImg(ConstantEnum.DEFAULT_HEADIMG_MAN.getStrValue());
        } else {
            manageUser.setHeadImg(ConstantEnum.DEFAULT_HEADIMG_WOMAN.getStrValue());
        }

        userMapper.insertSelective(manageUser);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update user
     *
     * @param manageUser
     * @param token
     * @return
     */
    @Override
    public JsonResult updateUser(ManageUser manageUser, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if (manageUser.getPhone() != null) {
            ManageUserExample userExample = new ManageUserExample();

            userExample.createCriteria().andDelflagEqualTo(0).andPhoneEqualTo(manageUser.getPhone()).andIdNotEqualTo(manageUser.getId());

            long l = userMapper.countByExample(userExample);

            if (l > 0) {
                return JsonResult.error(OperateEnum.USER_PHONE_EXIST);
            }
        }

        if (manageUser.getStatus() == 0 && manageUser.getId().equals(tokeUser.getId())) {
            return JsonResult.error(OperateEnum.USE_STATUS_ERROR);
        }

        manageUser.setUpdateTime(new Date());

        manageUser.setUpdateId(tokeUser.getId());

        userMapper.updateByPrimaryKeySelective(manageUser);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get user
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getUser(Long id) {

        ManageUser manageUser = userMapper.selectByPrimaryKey(id);

        return JsonResult.success(manageUser);
    }

    /**
     * Get user
     *
     * @param id
     * @return
     */
    @Override
    public ManageUser getUserPO(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * List user role
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult listRoleById(Long id) {

        //TODO

        return null;
    }

    /**
     * Save user role
     *
     * @param token
     * @param id
     * @param roles
     * @return
     */
    @Override
    public JsonResult saveUserRole(String token, Long id, String roles) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        userRoleMapper.deleteByUserId(id);

        if (StringUtils.isNotBlank(roles)) {
            String[] split = roles.split(",");

            ManageUserRole manageUserRole = new ManageUserRole();
            for (String s : split) {
                manageUserRole = new ManageUserRole();

                manageUserRole.setRoleId(Long.valueOf(s));

                manageUserRole.setUserId(id);

                userRoleMapper.insertSelective(manageUserRole);
            }
        }


        return JsonResult.success(OperateEnum.SUCCESS);
    }
}
