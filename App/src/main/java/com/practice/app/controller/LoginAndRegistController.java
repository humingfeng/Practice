package com.practice.app.controller;

import com.practice.dto.VerifyStudentDTO;
import com.practice.result.JsonResult;
import com.practice.service.PersonnelService;
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

    /**
     * Verify student
     * @param verifyStudentDTO
     * @return
     */
    @RequestMapping(value = "/app/register/vierify/student")
    public JsonResult verifyStudent(VerifyStudentDTO verifyStudentDTO){
        return personnelService.verifyStudent(verifyStudentDTO);
    }
}
