package com.practice.utils;


import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xushd on 2017/6/30.
 */
public class ExcelUtils {


    /**
     * 2003- 版本的excel
     */
    private final static String excel2003L =".xls";
    /**
     * 2007+ 版本的excel
     */
    private final static String excel2007U =".xlsx";

    /**
     * Read excel
     * @param in
     * @param fileName
     * @param verify
     * @return
     * @throws Exception
     */
    public static List<List<String>> getDataFromExcelStream(InputStream in, String fileName,String... verify) throws Exception {
        List<List<String>> list = new ArrayList<List<String>>();
        try{

            //创建Excel工作薄
            Workbook work = getWorkbook(in,fileName);
            if(null == work){
                throw new Exception("Excel工作薄为空！");
            }

            //验证
            Sheet sheet = work.getSheetAt(0);
            Row row = sheet.getRow(0);
            if(sheet==null){
                throw new Exception("上传文档格式不正确！");
            }
            for (int i = 0; i < verify.length; i++) {
                String s = verify[i];
                String s1 = row.getCell(i).toString();
                if(!StringUtils.equals(s,s1)){
                    throw new Exception("上传文档格式不正确！");
                }
            }

            //遍历Excel中所有的sheet
            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                if(sheet==null){continue;}

                int totalCellCount = row.getLastCellNum();
                //遍历当前sheet中的所有行
                for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    if(row==null||row.getFirstCellNum()==j){continue;}
                    //遍历所有的列
                    List<String> li = new ArrayList<String>();
                    for (int y = row.getFirstCellNum(); y < totalCellCount; y++) {
                        Cell cell = row.getCell(y);
                        if(cell==null){
                            li.add("");
                        }else{
                            li.add(getCellValue(cell));
                        }
                    }
                    list.add(li);
                }
            }

        }catch (Exception e){

            throw new Exception("上传文档格式不正确，请重新下载模版");
        }
        return list;



    }

    public static  List<List<String>> getBankListByExcel(InputStream in, String fileName,String firstHeadName) throws Exception {
        List<List<String>> list = null;

        //创建Excel工作薄
        Workbook work = getWorkbook(in,fileName);
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<List<String>>();

        //遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            int totalCellCount = 0;
            row = sheet.getRow(0);
            cell = row.getCell(0);
            if(!StringUtils.equals(getCellValue(cell).toString(),firstHeadName)){
                throw new Exception("上传文档格式不正确！");
            }
            totalCellCount = row.getLastCellNum();
            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum(); j < sheet.getLastRowNum(); j++) {

                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}

                //遍历所有的列
                List<String> li = new ArrayList<String>();
                for (int y = row.getFirstCellNum(); y < totalCellCount; y++) {
                    cell = row.getCell(y);
                    if(cell==null){
                        li.add("");
                    }else{
                        li.add(getCellValue(cell));
                    }
                }
                list.add(li);
            }
        }
        return list;
    }
    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public static  Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);
        }else if(excel2007U.equals(fileType)){
            wb = new XSSFWorkbook(inStr);
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }
    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public static  String getCellValue(Cell cell){
        String value = null;
        //格式化number String字符
        DecimalFormat df = new DecimalFormat("0");
        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        //格式化数字
        DecimalFormat df2 = new DecimalFormat("0.00");

        String keyG = "General",keyM = "m/d/yy";

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(keyG.equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if(keyM.equals(cell.getCellStyle().getDataFormatString())){
                    value = sdf.format(cell.getDateCellValue());
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue()?"true":"false";
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }



}
