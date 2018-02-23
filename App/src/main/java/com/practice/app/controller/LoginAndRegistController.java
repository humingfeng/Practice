package com.practice.app.controller;

import com.practice.dto.VerifyStudentDTO;
import com.practice.po.Parent;
import com.practice.result.JsonResult;
import com.practice.service.LoginService;
import com.practice.service.PersonnelService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/16 22:20
 */
@RestController
public class LoginAndRegistController {

    @Resource
    private PersonnelService personnelService;
    @Resource
    private LoginService loginService;

    /**
     * Verify student
     * @param verifyStudentDTO
     * @return
     */
    @RequestMapping(value = "/app/register/vierify/student")
    public JsonResult verifyStudent(VerifyStudentDTO verifyStudentDTO){
        return personnelService.verifyStudent(verifyStudentDTO);
    }

    /**
     * Parent register
     * @return
     */
    @RequestMapping(value = "/app/parent/register")
    public JsonResult parentRegister(Parent parent){
        return personnelService.registerParent(parent);
    }

    /**
     * Parent by phone
     * @param phone
     * @return
     */
    @RequestMapping(value = "/app/get/parent/{phone}")
    public JsonResult getParentByPhone(@PathVariable String phone){
        return personnelService.getParentByPhone(phone);
    }

    /**
     * Reset parent pass
     * @param id
     * @param pass
     * @return
     */
    @RequestMapping(value = "/app/reset/parent/pass/{id}")
    public JsonResult resetParentPass(@PathVariable Long id,String pass){
        return personnelService.resetParentPass(id,pass);
    }

    /**
     * Parent login check
     * @param phone
     * @param pass
     * @return
     */
    @RequestMapping(value = "/app/parent/login/check")
    public JsonResult parentLoginCheck(String phone,String pass){
        return personnelService.checkParentLogin(phone,pass);
    }

    /**
     * Get parent token by id
     * @param id
     * @return
     */
    @RequestMapping(value = "/app/parent/token/{id}")
    public JsonResult getParentToken(@PathVariable Long id){
        return loginService.getParentToken(id);
    }


    /**
     * Update parent token
     * @param token
     * @return
     */
    @RequestMapping(value = "/app/auth/update/token")
    public JsonResult updateToken(@RequestAttribute String token){
        return loginService.updateToken(token);
    }
}
