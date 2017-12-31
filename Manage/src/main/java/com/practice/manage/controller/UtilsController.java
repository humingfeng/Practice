package com.practice.manage.controller;

import com.practice.manage.service.UploadService;
import com.practice.result.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Utils controller
 *
 * @author Xushd  2017/12/31 18:03
 */
@RestController
public class UtilsController {

    @Resource
    private UploadService uploadService;


    /**
     * Upload file
     * @param file
     * @param dir
     * @return
     */
    @RequestMapping(value = "/upload/img/{dir}")
    public JsonResult uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
                                      @PathVariable String dir) {
        return uploadService.uploadImg(file,dir + "/");

    }
}
