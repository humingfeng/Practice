package com.practice.manage.service.impl;

import com.practice.dto.SignErcodeDTO;
import com.practice.enums.OperateEnum;
import com.practice.manage.service.UploadService;
import com.practice.mapper.ManageActivitySignMapper;
import com.practice.po.ManageActivitySign;
import com.practice.result.JsonResult;
import com.practice.utils.ErCodeUtils;
import com.practice.utils.FileUploadUtils;
import com.practice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xushd  2017/12/31 17:50
 */
@Service
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
     * 上传图片
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


    /**
     * Create activity sign in ercode
     *
     * @param activityId
     * @param id
     * @return
     */
    @Override
    public JsonResult createActivitySignInErcode(Long activityId, Long id) {

        OutputStream out = new ByteArrayOutputStream();

        SignErcodeDTO signErcodeDTO = new SignErcodeDTO();

        signErcodeDTO.setActivityId(activityId);
        signErcodeDTO.setSignId(id);
        signErcodeDTO.setEvent(1);
        signErcodeDTO.setType("sign");

        ErCodeUtils.createErCode(out, JsonUtils.objectToJson(signErcodeDTO));

        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;

        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());

        Map<String, String> param = this.getParam();

        param.put("key", PARENET_DIR + "ercode/");

        String newFileName = FileUploadUtils.UploadStreamOSS(param, swapStream);



        String url = IMGURL + PARENET_DIR + "ercode/" + newFileName;


        return JsonResult.success(OperateEnum.FILE_UPLOAD_SUCCESS.getStateInfo(), url);
    }

    /**
     * Create activity sign out ercode
     *
     * @param activityId
     * @param id
     * @param diff
     * @return
     */
    @Override
    public JsonResult createActivitySignOutErcode(Long activityId, Long id, int diff) {

        OutputStream out = new ByteArrayOutputStream();

        SignErcodeDTO signErcodeDTO = new SignErcodeDTO();

        signErcodeDTO.setActivityId(activityId);
        signErcodeDTO.setSignId(id);
        signErcodeDTO.setEvent(2);
        signErcodeDTO.setType("sign");

        ErCodeUtils.createErCode(out, JsonUtils.objectToJson(signErcodeDTO));

        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;

        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());

        Map<String, String> param = this.getParam();

        param.put("key", PARENET_DIR + "ercode/");

        String newFileName = FileUploadUtils.UploadStreamOSS(param, swapStream);

        ManageActivitySign sign = new ManageActivitySign();

        String url = IMGURL + PARENET_DIR + "ercode/" + newFileName;

        return JsonResult.success(OperateEnum.FILE_UPLOAD_SUCCESS.getStateInfo(), url);
    }
}
