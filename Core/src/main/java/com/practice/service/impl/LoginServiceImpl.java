package com.practice.service.impl;

import com.practice.dto.ParentDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.dto.TokenTeacherManageDTO;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.AuthEnum;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageUserMapper;
import com.practice.po.ManageUser;
import com.practice.po.ManageUserExample;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.LoginService;
import com.practice.service.PersonnelService;
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
    @Resource
    private CacheService cacheService;
    @Resource
    private PersonnelService personnelService;

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

    /**
     * Get Parent Token
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getParentToken(Long id) {

        ParentDTO parent = cacheService.getParent(id);

        if(parent==null){
            try{
                parent = personnelService.getParentDTO(id);
                cacheService.setParent(parent);
            }catch (Exception e){
                return JsonResult.error(AuthEnum.TIME_OUT);
            }
        }

        TokenParentDTO tokenParentDTO = new TokenParentDTO(parent.getId(), parent.getPhone(), parent.getName(), parent.getStudentId());

        return JsonResult.success(JwtTokenUtil.createAPPJWT(JsonUtils.objectToJson(tokenParentDTO)));

    }

    /**
     * Update parent token
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult updateToken(String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);



        return this.getParentToken(tokenParent.getId());
    }

    /**
     * Update teacher manage token
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult updateTeacherManageToken(String token) {

        TokenTeacherManageDTO tokenTeacherManage = JwtTokenUtil.getTokenTeacherManage(token);

        token = JwtTokenUtil.createAPPJWT(JsonUtils.objectToJson(tokenTeacherManage));


        return JsonResult.success(OperateEnum.SUCCESS.getStateInfo(),token);
    }
}
