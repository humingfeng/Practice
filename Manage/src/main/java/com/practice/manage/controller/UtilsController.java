package com.practice.manage.controller;

import com.practice.dto.ExcelExportDTO;
import com.practice.dto.StudentExcelDTO;
import com.practice.dto.TeacherExcelDTO;
import com.practice.enums.OperateEnum;
import com.practice.manage.service.UploadService;
import com.practice.po.ManageActivity;
import com.practice.po.ManageActivityEnroll;
import com.practice.po.ManageActivityEnrollRecord;
import com.practice.po.ManageActivitySign;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.utils.CommonUtils;
import com.practice.utils.ExcelUtils;
import com.practice.utils.ExceptionUtil;
import com.practice.utils.TimeUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils controller
 *
 * @author Xushd  2017/12/31 18:03
 */
@RestController
public class UtilsController {

    @Resource
    private UploadService uploadService;
    @Resource
    private ActivityService activityService;
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

    /**
     * 签到二维码下载
     * @param response
     * @param activityId
     * @param type
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/download/ercode/{activityId}/{type}")
    public void downloadErcode(HttpServletResponse response,
                               @PathVariable Long activityId,
                               @PathVariable int type) throws UnsupportedEncodingException {



        ManageActivitySign activitySign = activityService.getActivitySign(activityId);

        ManageActivity activity = activityService.getActivityManagePO(activityId);


        String url = "",fileName = "";
        if(type == 1 && StringUtils.isNotBlank(activitySign.getSignInErcode())){

            url = activitySign.getSignInErcode();

            fileName = activity.getName()+"签到二维码";

        }else if(type==2&& StringUtils.isNotBlank(activitySign.getSignOutErcode())){

            url = activitySign.getSignOutErcode();
            fileName = activity.getName()+"签退二维码";
        }

        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");

        response.reset();

        // 指定下载的文件名
        response.setHeader("Content-Disposition", "attachment;filename=" +downloadFielName+".png");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ServletOutputStream out = null;
        try {

            out = response.getOutputStream();

            InputStream picInputStream = CommonUtils.createPicInputStream(url);

            byte[] bytes = IOUtils.toByteArray(picInputStream);

            out.write(bytes);

            out.flush();

            out.close();

            picInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 导入模版下载
     * @param request
     * @param response
     * @param type
     * @return
     * @throws IOException
     */

    @RequestMapping("/download/excel/{type}/template")
    public void  download(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable String type) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("static/file/");

        String key1 = "teacher",key2 = "student";
        String fileName = "教师模版.xlsx";
        if(StringUtils.equals(type,key1)){
            fileName = "教师模版.xlsx";
        }else if(StringUtils.equals(type,key2)){
            fileName = "学生模版.xlsx";
        }
        String downloadFielName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
        File file = new File(realPath+"/"+fileName);
        if (file.exists()) {
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition","attachment;fileName=" + downloadFielName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * Teacher excel upload
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/file/teacher/excel")
    @ResponseBody
    public JsonResult teacherExcelUpload(@RequestParam(value = "file", required = false) MultipartFile file){

        try {
            if(file.isEmpty()){
                return JsonResult.error(OperateEnum.FILE_EMPTY);
            }

            String[] verify = {"教师名称","性别","手机","身份证"};

            List<List<String>> data = ExcelUtils.getDataFromExcelStream(file.getInputStream(), file.getOriginalFilename(),verify);

            List<TeacherExcelDTO> list = new ArrayList<>();
            for (List<String> strings : data) {

                TeacherExcelDTO dto = new TeacherExcelDTO();

                if(StringUtils.isBlank(strings.get(0))){
                    continue;
                }
                dto.setName(strings.get(0));
                dto.setSex(strings.get(1));
                dto.setPhone(strings.get(2));
                dto.setIdNum(strings.get(3));

                list.add(dto);
            }

            return JsonResult.success(list);

        } catch (Exception e) {
            return JsonResult.error(e.getMessage());
        }

    }

    /**
     * Student excel upload
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload/file/student/excel")
    @ResponseBody
    public JsonResult studentExcelUpload(@RequestParam(value = "file", required = false) MultipartFile file){
        try {
            if(file.isEmpty()){
                return JsonResult.error(OperateEnum.FILE_EMPTY);
            }

            String[] verify = {"姓名","性别","学籍号","身份证","出生日期"};

            List<List<String>> data = ExcelUtils.getDataFromExcelStream(file.getInputStream(), file.getOriginalFilename(),verify);

            List<StudentExcelDTO> list = new ArrayList<>();
            for (List<String> strings : data) {

                StudentExcelDTO dto = new StudentExcelDTO();

                if(StringUtils.isBlank(strings.get(0))||StringUtils.isBlank(strings.get(2))){
                    continue;
                }

                if(StringUtils.isBlank(strings.get(4))|| !TimeUtils.isDataFormat(strings.get(4))){
                    continue;
                }

                dto.setName(strings.get(0));
                dto.setSex(strings.get(1));
                dto.setEnuNum(strings.get(2));
                dto.setIdNum(strings.get(3));
                dto.setBirthday(strings.get(4));

                list.add(dto);
            }

            return JsonResult.success(list);

        } catch (Exception e) {

            return JsonResult.error(e.getMessage());
        }

    }

    @RequestMapping("/download/excel/enroll/{activityId}")
    public void exportEnroll(@PathVariable Long activityId, ManageActivityEnroll enroll){

        ExcelExportDTO excelExportDTO = new ExcelExportDTO();




        //List<ManageActivityEnrollRecord> list  =  activityService.listEnrollRecordUsable();
    }
}
