package com.practice.manage.service;

import com.practice.result.JsonResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xushd  2017/12/31 17:50
 */
public interface UploadService {

    /**
     * file upload
     * @param file
     * @param dir
     * @return
     */
    JsonResult uploadImg(MultipartFile file, String dir);


    /**
     * Create activity sign in ercode
     * @param activityId
     * @param id
     * @return
     */
    JsonResult createActivitySignInErcode(Long activityId, Long id);

    /**
     * Create activity sign out ercode
     * @param activityId
     * @param id
     * @param diff
     * @return
     */
    JsonResult createActivitySignOutErcode(Long activityId, Long id, int diff);
}
