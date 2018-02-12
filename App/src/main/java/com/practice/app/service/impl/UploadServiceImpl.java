package com.practice.app.service.impl;

import com.practice.app.service.UploadService;
import com.practice.enums.OperateEnum;
import com.practice.result.JsonResult;
import com.practice.utils.FileUploadUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xushd on 2018/2/12 16:58
 */
public class UploadServiceImpl implements UploadService {


    @Value("${ENDPOINT}")
    private String ENDPOINT;

    @Value("${ACCESSKEYID}")
    private String ACCESSKEYID;

    @Value("${ACCESSKEYSECRET}")
    private String ACCESSKEYSECRET;

    @Value("${BUCKETNAME}")
    private String BUCKETNAME;

    @Value("${IMGURL}")
    private String IMGURL;

    @Value("${PARENET_DIR}")
    private String PARENET_DIR;

    /**
     * 获取OSS对象参数
     *
     * @return
     */
    private Map<String, String> getParam() {

        Map<String, String> param = new HashMap<>();
        param.put("endpoint", ENDPOINT);
        param.put("accessKeyId", ACCESSKEYID);
        param.put("accessKeySecret", ACCESSKEYSECRET);
        param.put("bucketName", BUCKETNAME);

        return param;
    }

    /**
     * img upload
     *
     * @param file
     * @param dir
     * @return
     */
    @Override
    public JsonResult uploadImg(MultipartFile file, String dir) {

        try {

            Long maxSize = 10 * 1024 * 1024L;

            if (file.isEmpty()) {
                return JsonResult.error(OperateEnum.FILE_EMPTY);
            }
            if (file.getSize() > maxSize) {
                return JsonResult.error(OperateEnum.FILE_SIZE);
            }

            Map<String, String> param = getParam();
            param.put("originalFilename", file.getOriginalFilename());
            param.put("key", PARENET_DIR + dir);
            String newFileName = FileUploadUtils.UploadFileOSS(param, file.getBytes());

            return JsonResult.success(OperateEnum.FILE_UPLOAD_SUCCESS.getStateInfo(), IMGURL + PARENET_DIR + dir + newFileName);

        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(OperateEnum.FAILE);
        }
    }
}
