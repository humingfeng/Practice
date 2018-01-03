package com.practice.service.impl;

import com.practice.dto.NavDTO;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.AuthEnum;
import com.practice.mapper.ManageUserMapper;
import com.practice.po.ManageUser;
import com.practice.po.ManageUserExample;
import com.practice.result.JsonResult;
import com.practice.service.LoginService;
import com.practice.service.UserService;
import com.practice.utils.ExceptionUtil;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xushd  2017/12/21 22:26
 */
@Service
public class LoginServiceImpl implements LoginService {


    public static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource
    private ManageUserMapper manageUserMapper;
    @Resource
    private UserService userService;

    /**
     * Manage Login Check
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public JsonResult manageLoginCheck(String account, String password) {

        ManageUserExample manageUserExample = new ManageUserExample();

        manageUserExample.createCriteria().andPhoneEqualTo(Long.valueOf(account)).andDelflagEqualTo(0);

        List<ManageUser> manageUsers = manageUserMapper.selectByExample(manageUserExample);

        if (manageUsers.size() == 0) {
            return JsonResult.error(AuthEnum.USER_NO_EXIST);
        } else {

            ManageUser manageUser = manageUsers.get(0);

            Integer stopStatus = 0;

            if (manageUser.getStatus().equals(stopStatus)) {
                return JsonResult.error(AuthEnum.USER_NO_STATUS);
            }

            if (!StringUtils.equals(password, manageUser.getPassword())) {
                return JsonResult.error(AuthEnum.PASS_ERROR);
            }

            try {
                String token = JwtTokenUtil.createJWT(JsonUtils.objectToJson(new TokenUserDTO(manageUser.getId(),manageUser.getNickName(),manageUser.getHeadImg(),manageUser.getOrganizeId())));



                userService.createUserNavsAndPermissionCache(manageUser.getId());


                return JsonResult.success(token);

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(ExceptionUtil.getStackTrace(e));

                return JsonResult.error(ExceptionUtil.getStackTrace(e));
            }


        }


    }
}
