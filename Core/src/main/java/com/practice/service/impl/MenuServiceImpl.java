package com.practice.service.impl;

import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageNavMapper;
import com.practice.mapper.ManageRoleNavMapper;
import com.practice.po.ManageNav;
import com.practice.po.ManageNavExample;
import com.practice.result.JsonResult;
import com.practice.service.MenuService;
import com.practice.utils.JwtTokenUtil;
import com.practice.vo.MenuVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/23 20:21
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private ManageNavMapper navMapper;
    @Resource
    private ManageRoleNavMapper roleNavMapper;

    /**
     * List menu
     * @return
     */
    @Override
    public JsonResult listMenu() {

        List<MenuVO> list = new ArrayList<>();

        ManageNavExample manageNavExample = new ManageNavExample();

        manageNavExample.createCriteria().andParentIdEqualTo(0L).andDelflagEqualTo(0);

        List<ManageNav> manageNavs = navMapper.selectByExample(manageNavExample);

        for (ManageNav manageNav : manageNavs) {

            MenuVO menuVO = new MenuVO();

            menuVO.setId(manageNav.getId());

            menuVO.setName(manageNav.getName());

            menuVO.setIcon(manageNav.getIcon());

            menuVO.setStatus(manageNav.getStatus());

            menuVO.setUrl(manageNav.getUrl());

            menuVO.setParentId(0L);

            manageNavExample.clear();

            manageNavExample.createCriteria().andParentIdEqualTo(manageNav.getId()).andDelflagEqualTo(0);

            List<ManageNav> childNavs = navMapper.selectByExample(manageNavExample);

            List<MenuVO> children = new ArrayList<>();

            for (ManageNav childNav : childNavs) {
                MenuVO childVO = new MenuVO();

                childVO.setId(childNav.getId());

                childVO.setName(childNav.getName());

                childVO.setIcon(childNav.getIcon());

                childVO.setStatus(childNav.getStatus());

                childVO.setUrl(childNav.getUrl());

                childVO.setParentId(manageNav.getId());

                children.add(childVO);
            }

            menuVO.setChildren(children);

            list.add(menuVO);

        }

        return JsonResult.success(list);
    }

    /**
     * Get menu
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getMenu(Long id) {
        return JsonResult.success(navMapper.selectByPrimaryKey(id));
    }

    /**
     * Add menu
     *
     * @param token
     * @param manageNav
     * @return
     */
    @Override
    public JsonResult addMenu(String token, ManageNav manageNav) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        manageNav.setId(null);

        manageNav.setStatus(1);
        manageNav.setDelflag(0);
        manageNav.setUpdateUser(tokeUser.getId());

        Date date = new Date();
        manageNav.setCreateTime(date);
        manageNav.setUpdateTime(date);

        if(manageNav.getParentId()==0L){
            manageNav.setType(1);
        }else{
            manageNav.setType(2);
        }

        navMapper.insertSelective(manageNav);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update menu
     *
     * @param token
     * @param manageNav
     * @return
     */
    @Override
    public JsonResult updateMenu(String token, ManageNav manageNav) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        manageNav.setUpdateUser(tokeUser.getId());

        Date date = new Date();
        manageNav.setUpdateTime(date);

        navMapper.updateByPrimaryKeySelective(manageNav);

        if(manageNav.getParentId()==0L&&manageNav.getStatus()!=null&&manageNav.getStatus()==0){

            ManageNavExample manageNavExample = new ManageNavExample();
            manageNavExample.createCriteria().andParentIdEqualTo(manageNav.getId());
            ManageNav nav = new ManageNav();
            nav.setStatus(manageNav.getStatus());
            navMapper.updateByExampleSelective(nav,manageNavExample);

        }

        if(manageNav.getParentId()==0L&&manageNav.getDelflag()!=null&&manageNav.getDelflag()==1){

            ManageNavExample manageNavExample = new ManageNavExample();
            manageNavExample.createCriteria().andParentIdEqualTo(manageNav.getId());
            ManageNav nav = new ManageNav();
            nav.setDelflag(manageNav.getDelflag());
            navMapper.updateByExampleSelective(nav,manageNavExample);

        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List menu parent
     *
     * @return
     */
    @Override
    public JsonResult listMenuParent() {

        ManageNavExample manageNavExample = new ManageNavExample();

        manageNavExample.createCriteria().andParentIdEqualTo(0L).andDelflagEqualTo(0).andStatusEqualTo(1);

        List<ManageNav> manageNavs = navMapper.selectByExample(manageNavExample);

        return JsonResult.success(manageNavs);
    }

    /**
     * List menu usable
     * @param roleId
     * @return
     */
    @Override
    public JsonResult listMenuUsable(Long roleId) {

        List<MenuVO> list = new ArrayList<>();

        ManageNavExample manageNavExample = new ManageNavExample();

        manageNavExample.createCriteria().andParentIdEqualTo(0L).andDelflagEqualTo(0).andStatusEqualTo(1);

        List<ManageNav> manageNavs = navMapper.selectByExample(manageNavExample);


        List<Long> checks = new ArrayList<>();

        if(roleId!=null){
            checks = roleNavMapper.selectByRoleId(roleId);
        }


        for (ManageNav manageNav : manageNavs) {

            MenuVO menuVO = new MenuVO();

            menuVO.setId(manageNav.getId());

            menuVO.setName(manageNav.getName());

            menuVO.setIcon(manageNav.getIcon());

            menuVO.setStatus(manageNav.getStatus());

            menuVO.setUrl(manageNav.getUrl());

            menuVO.setParentId(0L);

            manageNavExample.clear();

            manageNavExample.createCriteria().andParentIdEqualTo(manageNav.getId()).andDelflagEqualTo(0);

            List<ManageNav> childNavs = navMapper.selectByExample(manageNavExample);

            List<MenuVO> children = new ArrayList<>();

            for (ManageNav childNav : childNavs) {
                MenuVO childVO = new MenuVO();

                childVO.setId(childNav.getId());

                childVO.setName(childNav.getName());

                childVO.setIcon(childNav.getIcon());

                childVO.setStatus(childNav.getStatus());

                childVO.setUrl(childNav.getUrl());

                childVO.setParentId(manageNav.getId());

                if(checks.contains(childNav.getId())){
                    childVO.setChecked(1);
                }else{
                    childVO.setChecked(0);
                }


                children.add(childVO);
            }
            if(children.size()==0){
                continue;
            }
            menuVO.setChildren(children);

            list.add(menuVO);

        }

        return JsonResult.success(list);
    }
}
