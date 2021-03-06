package com.practice.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.plaf.nimbus.State;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * show 时间工具类
 *
 * @author Xushd
 * @since 2017/1/17 0017 下午 10:02
 */
public class TimeUtils {

    private static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_DEFAULT_SHORT = "yyyy-MM-dd";
    private static final String FORMAT_DEFAULT_HOUR = "yyyy/MM/dd HH";

    private static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern(FORMAT_DEFAULT);
    private static final DateTimeFormatter FORMAT_SHORT = DateTimeFormat.forPattern(FORMAT_DEFAULT_SHORT);
    private static final DateTimeFormatter FORMAT_HOUR = DateTimeFormat.forPattern(FORMAT_DEFAULT_HOUR);

    private static final Pattern TIME_PATTERN = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
    /**
     * 获取当期时间 "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getNowTime() {
        DateTime dateTime = new DateTime();
        return dateTime.toString(FORMAT_DEFAULT);
    }

    /**
     * 获取当期时间 "yyyy-MM-dd"
     *
     * @return
     */
    public static String getNowTimeShort() {
        DateTime dateTime = new DateTime();
        return dateTime.toString(FORMAT_SHORT);
    }

    /**
     * 获取当期时间 by format
     * @param format
     * @return
     */
    public static String getNowDateStringByFormat(String format){
        DateTime dateTime = new DateTime();
        return dateTime.toString(DateTimeFormat.forPattern(format));
    }

    /**
     * DateTime to String
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        SimpleDateFormat format3 = new SimpleDateFormat(FORMAT_DEFAULT);
        return format3.format(date);
    }

    /**
     * DateTime to String
     * @param date
     * @return
     */
    public static String getDateStringShort(Date date) {
        SimpleDateFormat format3 = new SimpleDateFormat(FORMAT_DEFAULT_SHORT);
        return format3.format(date);
    }


    /**
     * 通过字符串获取Date
     * @param time
     * @return
     */
    public static Date getDateFromStringShort(String time) {
        DateTime dateTime = DateTime.parse(time, FORMAT_SHORT);
        return dateTime.toDate();
    }

    /**
     * 通过字符串获取Date
     * @param time
     * @return
     */
    public static Date getDateFromString(String time) {
        DateTime dateTime = DateTime.parse(time, FORMAT);
        return dateTime.toDate();
    }

    /**
     * 判断日期字符串是否合法
     * @param dataStr
     * @return
     */
    public static boolean isDataFormat(String dataStr){

        return TIME_PATTERN.matcher(dataStr).matches();
    }

    /**
     * 时间是否大于系统当前时间
     * @param time
     * @return
     */
    public static boolean greaterThanNow(Date time){
        DateTime dateTime = new DateTime(time);
        if(dateTime.getMillis() > new DateTime().getMillis()) {
            return true;
        }
        return false;
    }

    /**
     * 时间是否小于系统当前时间
     * @param time
     * @return
     */
    public static boolean lessThanNow(Date time){
        DateTime dateTime = new DateTime(time);
        if(dateTime.getMillis() < new DateTime().getMillis()) {
            return true;
        }
        return false;
    }

    /**
     * 两个时间大小判断
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean Obj1LessObj2(Date obj1,Date obj2){

        DateTime dateTime1 = new DateTime(obj1);
        DateTime dateTime2 = new DateTime(obj2);

        if(dateTime1.getMillis() < dateTime2.getMillis()){
            return true;
        }

        return false;

    }

    /**
     * 获取 minutes 前的时间
     * @return
     */
    public static Date getDateBeforeMinutes(Integer minutes) {
        DateTime dt = new DateTime().minusMinutes(minutes);
        return dt.toDate();
    }
    /**
     * 获取 minutes 后的时间
     * @return
     */
    public static Date getDateAfterMinutes(Integer minutes){
        DateTime dt = new DateTime().plusMinutes(minutes);
        return dt.toDate();
    }

    public static Date getDateAfterMinutes(Date date,Integer minutes){
        DateTime dt = new DateTime(date).plusMinutes(minutes);
        return dt.toDate();
    }
    /**
     * Get Time form string hour
     * @param time
     * @return
     */
    public static Date getDateHourFromString(String time) {
        DateTime dateTime = DateTime.parse(time, FORMAT).withSecondOfMinute(0).withMinuteOfHour(0);
        return dateTime.toDate();
    }

    /**
     * Get String form data yyyy/MM/dd/ HH
     * @param date
     * @return
     */
    public static String getSringDateLastHour(Date date){
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DEFAULT_HOUR);
        return format.format(date);
    }

    /**
     * Get day diff
     * @param dateEnd
     * @param dateBegin
     * @return
     */
    public static int getDateDayDiff(Date dateEnd, Date dateBegin) {

        DateTime dateTime1 = new DateTime(dateEnd);
        DateTime dateTime2 = new DateTime(dateBegin);

        Days days = Days.daysBetween(dateTime2,dateTime1);

        return days.getDays();

    }

    /**
     * Get hour diff
     * @param timeEnd
     * @param timeBegin
     * @return
     */
    public static int getDateHourDiff(Date timeEnd, Date timeBegin) {
        DateTime dateTime2 = new DateTime(timeEnd);
        DateTime dateTime1 = new DateTime(timeBegin);

        Hours hours = Hours.hoursBetween(dateTime1, dateTime2);

        return hours.getHours();

    }

    /**
     * Get minutes diff
     * @param timeEnd
     * @param timeBegin
     * @return
     */
    public static int getDateMinuteDiff(Date timeEnd, Date timeBegin){
        DateTime dateTime2 = new DateTime(timeEnd);
        DateTime dateTime1 = new DateTime(timeBegin);

        Minutes minutes = Minutes.minutesBetween(dateTime1, dateTime2);

        return minutes.getMinutes();
    }

    /**
     * Before now string
     * @param date
     * @return
     */
    public static String getBeforeNowString(Date date){
        Date now = new Date();
        long diff = now.getTime() - date.getTime();

        long diffYear = diff / (24 * 60 * 60 * 1000  ) / 365  ;
        if(diffYear>0){
            return diffYear+"年前";
        }
        long diffMoth = diff / (24 * 60 * 60 * 1000 ) / 30;
        if(diffMoth>0){
            return diffMoth+"月前";
        }
        //Day
        long diffDays = diff / (24 * 60 * 60 * 1000);
        if(diffDays>0){
            return diffDays+"天前";
        }
        long diffHours = diff / (60 * 60 * 1000) % 24;
        if(diffHours>0){
            return diffHours+"小时前";
        }
        long diffMinutes = diff / (60 * 1000) % 60;
        if(diffMinutes>0){
            return diffMinutes+"分钟前";
        }
        return "刚刚";

    }

//    /**
//     * 获取当前日期字符串 yyyyMMdd
//     * @return
//     */
//    public static String getNowDateString(){
//        DateTime dateTime = new DateTime();
//        return dateTime.toString(DateTimeFormat.forPattern("yyyyMMdd"));
//    }
//
//    /**
//     * 获取当前数据的时间戳
//     * @return
//     */
//    public static long getCurrentTime(){
//        DateTime dateTime = new DateTime();
//        return dateTime.getMillis();
//    }
//    /**
//     * 获取前一天时间
//     *
//     * @param date
//     * @return
//     */
//    public static String getFormatDateTime(DateTime date) {
//        return date.toString(format_default_short);
//    }
//
//    public static String getFormatDateTime2(DateTime date) {
//        return date.toString(format_default);
//    }
//
//    public static String getFormatDateTime3(Date date) {
//        SimpleDateFormat format3 = new SimpleDateFormat(format_default);
//        return format3.format(date);
//    }
//    public static String getFormatDateTimeShort(Date date) {
//        SimpleDateFormat format3 = new SimpleDateFormat(format_default_short);
//        return format3.format(date);
//    }
//
//    /**
//     * 获取当日凌晨时间
//     * @return
//     */
//    public static Date getNowMoring(){
//
//        DateTime dt = new DateTime().withMillisOfDay(0);
//        return dt.toDate();
//    }
//
//    /**
//     * 获取前 几天 的凌晨时间
//     * @param day
//     * @return
//     */
//    public static Date getMoringDayBefore(int day){
//        DateTime dt = new DateTime().withMillisOfDay(0).minusDays(day);
//        return dt.toDate();
//
//
//    }
//
//    /**
//     * 获取前 几天 的凌晨傍晚
//     * @param day
//     * @return
//     */
//    public static Date getEveningDayBefore(int day){
//        DateTime dt=new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).minusDays(day);
//        return dt.toDate();
//    }
//
//
//    /**
//     * 通过字符串获取Date
//     *
//     * @param time
//     * @return
//     */
//    public static Date getDate(String time) {
//        DateTime dateTime = DateTime.parse(time, format);
//        return dateTime.toDate();
//    }
//
//    /**
//     * Date => String
//     * @param date
//     * @param format
//     * @return
//     */
//    public static String getFormatDateTime(Date date,String format){
//
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        return sdf.format(date);
//    }
//
//    /**
//     * 获取前几天 时间 字符串
//     * @param day
//     * @return
//     */
//    public static String getDateStringDayBefore(int day){
//        DateTime dt = new DateTime();
//        DateTime dateTime = dt.minusDays(day);
//        return dateTime.toString(DateTimeFormat.forPattern("yyyy-MM-dd"));
//    }
//
//    /**
//     * date 与当前时间 差几天
//     * @param date
//     * @return
//     */
//    public static int getDateDiff(Date date){
//
//        DateTime dt1 = new DateTime(getEveningDayBefore(0));
//        DateTime dt2 = new DateTime(date);
//        Days days = Days.daysBetween(dt2, dt1);
//        return days.getDays();
//    }
//
//
//    /**
//     * 获取当前时间后几分钟的时间戳
//     * @param minutes
//     * @return
//     */
//    public static long getOverTime(int minutes){
//        DateTime dateTime = new DateTime();
//        dateTime = dateTime.plusMinutes(minutes);
//        return dateTime.getMillis();
//    }
//
//    /**
//     * 时间戳是否大于系统当前时间
//     * @param time
//     * @return
//     */
//    public static boolean compare(long time){
//        if(time>new DateTime().getMillis()) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 获取时间戳与当前系统时间的差值
//     * @param time
//     * @return
//     */
//    public static long getDiff(long time){
//        return (time - new DateTime().getMillis());
//    }
//
//    public static Date getDate2(String time) {
//        DateTime dateTime = DateTime.parse(time, format_short);
//        return dateTime.toDate();
//    }
//
//    /**
//     * 获取几天前的Date
//     *
//     * @param day
//     * @return
//     */
//    public static Date getDateBefore(int day) {
//        DateTime dateTime = new DateTime();
//        return dateTime.minusDays(day).toDate();
//    }
//
//    public static String getDateBeforeDay(int day) {
//        DateTime dateTime = new DateTime();
//        return getFormatDateTime(dateTime.minusDays(day));
//    }
//
//    /**
//     * 获取当前年
//     *
//     * @return
//     */
//    public static String getYear() {
//        DateTime dateTime = new DateTime();
//        return dateTime.toString("yyyy");
//    }
//
//    /**
//     * 获取当前月
//     *
//     * @return
//     */
//    public static String getMonth() {
//        DateTime dateTime = new DateTime();
//        return dateTime.toString("MM");
//    }
//
//    /**
//     * 获取当前日
//     *
//     * @return
//     */
//    public static String getDay() {
//        DateTime dateTime = new DateTime();
//        return dateTime.toString("dd");
//    }
//
//
//    /**
//     * 获取当前时间 到晚上零点的 时间差 秒
//     * @author Xushd
//     * @return
//     */
//    public static int NowToEveningSeconds() {
//
//        DateTime dtNow = new DateTime();
//        DateTime dtEvening = new DateTime().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
//
//        return Seconds.secondsBetween(dtNow,dtEvening).getSeconds();
//
//    }
//
//    /**
//     * 获取当前时间 与 传入时间 相差 年
//     *
//     * @author Xushd
//     * @param registTime
//     * @return
//     */
//    public static int getYearDiff(String registTime) {
//
//        DateTime dtNow = new DateTime();
//        DateTime dtRegist = new DateTime(registTime);
//
//        return Years.yearsBetween(dtRegist,dtNow).getYears();
//
//    }
}
