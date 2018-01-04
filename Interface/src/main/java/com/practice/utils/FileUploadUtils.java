package com.practice.utils;

import com.aliyun.oss.OSSClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * File upload to aliyun oss
 * @author Xushd  2017/12/31 17:59
 */
public class FileUploadUtils {

    /**
     * Upload file byte to oss
     * @param param
     * @param bytes
     * @return
     */
    public static String UploadFileOSS(Map<String, String> param, byte[] bytes) {

        OSSClient client = new OSSClient(param.get("endpoint"), param.get("accessKeyId"), param.get("accessKeySecret"));

        String newFileName = IDUtils.genImageName() + param.get("originalFilename").substring(param.get("originalFilename").lastIndexOf("."));

        client.putObject(param.get("bucketName"),param.get("key")+newFileName, new ByteArrayInputStream(bytes));

        return newFileName;

    }

    /**
     * Upload stream to oss
     * @param param
     * @param inputStream
     * @return
     */
    public static String UploadStreamOSS(Map<String, String> param, InputStream inputStream){

        OSSClient client = new OSSClient(param.get("endpoint"), param.get("accessKeyId"), param.get("accessKeySecret"));

        String newFileName = IDUtils.genImageName() + ".png";

        client.putObject(param.get("bucketName"),param.get("key")+newFileName, inputStream);

        return newFileName;
    }



}
