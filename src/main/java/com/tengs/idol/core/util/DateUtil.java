package com.tengs.idol.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 
 * @Description: 日期工具类
 * @Author 
 * @Version 1.0 2018年12月19日 上午10:56:29
 */
public class DateUtil {

	protected final static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
	
	public final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";

	public final static String DATE_FORMAT_SOLIDUS = "yyyy/MM/dd";

	public final static String DATE_FORMAT_COMPACT = "yyyyMMdd";

	public final static String DATE_FORMAT_UTC_DEFAULT = "MM-dd-yyyy";

	public final static String DATE_FORMAT_UTC_SOLIDUS = "MM/dd/yyyy";

	public final static String DATE_FORMAT_TAODA_DIRECTORY = "yyyyMMdd HHmmss";

	public final static String DATE_FORMAT_TAODA_DIRECTORY_T = "yyyyMMddHHmmss";
	
	public final static String DATE_FORMAT_TAODA_DIRECTORY_T2 = "yyyyMMddHHmmssSSS";

	public final static String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";

	public final static String DATE_TIME_FORMAT_COMPACT = "yyyyMMdd hhmmss";

	public final static String DATE_TIME_FORMAT_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";

	public final static String DATE_TIME_FORMAT_TRADET = "yyyy-MM-dd HH:mm";

	public final static String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	public final static String DATE_TIME_FORMAT_SOLIDUS = "yyyy/MM/dd HH:mm:ss";

	public final static String DATE_TIME_FORMAT_MIN = "yyyy-MM-dd HH:mm";

	public final static String DATE_TIME_FORMAT_UTC_DEFAULT = "MM-dd-yyyy HH:mm:ss";

	public final static String DATE_TIME_FORMAT_UTC_SOLIDUS = "MM/dd/yyyy HH:mm:ss";
	
	public final static String DATE_TIME_FORMAT_HOUR = "HH:mm:ss";
	
	public final static String DATE_TIME_FORMAT_MMdd = "MMdd";
	
	public final static String DATE_TIME_FORMAT_HOURMIN = "HH:mm";
	
	public final static String DATE_TIME_FORMAT_Y = "yyMMdd";
	
	public final static String DATE_TIME_FORMAT_YEAR = "yyyy";
	
	
	private static Map<String, String> dateFormatRegisterMap = new HashMap<String, String>();

	private static Map<String, SimpleDateFormat> dateFormatMap = new HashMap<String, SimpleDateFormat>();
	private static TimeZone bjTimeZone;
	static {
		// 各种日期格式注册，有新需要请在此处添加 ，无需其它改动
		dateFormatRegisterMap.put(DATE_FORMAT_COMPACT, "^\\d{8}$");
		dateFormatRegisterMap.put(DATE_FORMAT_DEFAULT, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
		dateFormatRegisterMap.put(DATE_FORMAT_SOLIDUS, "^\\d{4}/\\d{1,2}/\\d{1,2}$");
		dateFormatRegisterMap.put(DATE_FORMAT_UTC_DEFAULT, "^\\d{1,2}-\\d{1,2}-\\d{4}$");
		dateFormatRegisterMap.put(DATE_FORMAT_UTC_SOLIDUS, "^\\d{1,2}/\\d{1,2}/\\d{4}$");
		dateFormatRegisterMap.put(DATE_TIME_FORMAT_DEFAULT, "^\\d{4}-\\d{1,2}-\\d{1,2}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
		dateFormatRegisterMap.put(DATE_TIME_FORMAT_SOLIDUS, "^\\d{4}/\\d{1,2}/\\d{1,2}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
		dateFormatRegisterMap.put(DATE_TIME_FORMAT_UTC_DEFAULT, "^\\d{1,2}-\\d{1,2}-\\d{4}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
		dateFormatRegisterMap.put(DATE_TIME_FORMAT_UTC_SOLIDUS, "^\\d{1,2}/\\d{1,2}/\\d{4}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");

		dateFormatMap.put(DATE_FORMAT_DEFAULT, new SimpleDateFormat(DATE_FORMAT_DEFAULT));
		dateFormatMap.put(DATE_FORMAT_SOLIDUS, new SimpleDateFormat(DATE_FORMAT_SOLIDUS));
		dateFormatMap.put(DATE_FORMAT_COMPACT, new SimpleDateFormat(DATE_FORMAT_COMPACT));
		dateFormatMap.put(DATE_FORMAT_UTC_DEFAULT, new SimpleDateFormat(DATE_FORMAT_UTC_DEFAULT));
		dateFormatMap.put(DATE_FORMAT_UTC_SOLIDUS, new SimpleDateFormat(DATE_FORMAT_UTC_SOLIDUS));
		dateFormatMap.put(DATE_TIME_FORMAT_DEFAULT, new SimpleDateFormat(DATE_TIME_FORMAT_DEFAULT));
		dateFormatMap.put(DATE_TIME_FORMAT_SOLIDUS, new SimpleDateFormat(DATE_TIME_FORMAT_SOLIDUS));
		dateFormatMap.put(DATE_TIME_FORMAT_UTC_DEFAULT, new SimpleDateFormat(DATE_TIME_FORMAT_UTC_DEFAULT));
		dateFormatMap.put(DATE_TIME_FORMAT_UTC_SOLIDUS, new SimpleDateFormat(DATE_TIME_FORMAT_UTC_SOLIDUS));
		
		
        String[] ids = TimeZone.getAvailableIDs(8 * 60 * 60 * 1000);
        if (ids.length == 0) {
            bjTimeZone = TimeZone.getDefault();
        } else {
            bjTimeZone = new SimpleTimeZone(8 * 60 * 60 * 1000, ids[0]);
        }		
	}

	/**
	 * 功能描述：根据给定的格式将str转化为Date类型
	 *
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static synchronized Date getDate(String dateStr, String format) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		DateFormat formater = new SimpleDateFormat(format);
		Date result;
		try {
			result = formater.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return result;
	}

	public static String getNowDate (String format) {
		return formatDate(new Date(), format);
	}

	public static String formatDate (Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	public static String format (String formatString, Date date) {
		return new SimpleDateFormat(formatString).format(date);
	}

	public static Date parse (String formatString, String dateString) {
		try {
			return new SimpleDateFormat(formatString).parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Date parse2 (String formatString, String dateString) {
		if (isBlank(dateString)) {
			return null;
		}
		try {
			return new SimpleDateFormat(formatString).parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

	public static Date parseStrToDate (String formatString, String dateString) {
		try {
			return new SimpleDateFormat(formatString).parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public static long parseDateToLong (String date) {
		date = date.replace("-", "");
		Long result = Long.valueOf(date);
		return result;
	}

	public static String formatDate (String date, String format, String newFormat) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date d = sf.parse(date);
		sf = new SimpleDateFormat(newFormat);
		return sf.format(d);
	}
	/**1则代表的是对年份操作，2是对月份操作，3是对星期操作，5是对日期操作，11是对小时操作，12是对分钟操作，13是对秒操作，14是对毫秒操作*/
	public static Date add (Date date, int n, int calendarField) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, n);

		return c.getTime();
	}


	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param sdate
	 *            较小的时间
	 * @param edate
	 *            较大的时间
	 * @return 相差天数
	 * @throws Exception
	 */
	public static int daysBetween (Date sdate, Date edate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DEFAULT);
		sdate = sdf.parse(sdf.format(sdate));
		edate = sdf.parse(sdf.format(edate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(edate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(betweenDays));
	}

	public static Date calMonth (Date date, String format, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return parseStrToDate(format, sdf.format(calendar.getTime()));
	}

	public static Date calWeek (Date date, String format, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEDNESDAY, n);
		return parseStrToDate(format, sdf.format(calendar.getTime()));
	}

	@SuppressWarnings("deprecation")
    public static Date getStartOfDate (Date date) {
		return new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
	}

	@SuppressWarnings("deprecation")
    public static Date getEndOfDate (Date date) {
		return new Date(date.getYear(), date.getMonth(), date.getDate(), 23, 59, 59);
	}

	public static String getCurrentDateStr (String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		return sdf.format(new Date());
	}
	/**
	 * 根据long值获取date
	 */
	public static String getDateStr (long time,String format) throws Exception {
		Date t=new Date(time);
		return format(format, t);
	}
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
	    List<Date> result = new ArrayList<Date>();
	    Calendar tempStart = Calendar.getInstance();
	    tempStart.setTime(start);
	    tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    
	    Calendar tempEnd = Calendar.getInstance();
	    tempEnd.setTime(end);
	    while (tempStart.before(tempEnd)) {
	        result.add(tempStart.getTime());
	        tempStart.add(Calendar.DAY_OF_YEAR, 1);
	    }
	    return result;
	}
	
	/**
	 * 获取昨天日期
	 * @return
	 */
	public static Date getLastDate(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,-1);
		return cal.getTime();
	}
	
	/**
	 * 获取某一天的日期 0-当天
	 * @Title: getDateForDay  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param day
	 * @return
	 */
	public static String getDateForDay(int day,String format) {
		Date dNow = new Date(); 
		SimpleDateFormat sdf=new SimpleDateFormat(format); //设置时间格式
		if (day==0) {
			return sdf.format(dNow); //格式化当前时间
		}
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, day); //设置为某一天
		Date dBefore = calendar.getTime();   //得到前一天的时间
		return sdf.format(dBefore);    //格式化前一天
	}
	
	/**
	 * 获取指定时区的时间
	 * @param timeZoneOffset 时区 西：-12 12 :东
	 * @param format 返回什么格式
	 * @return
	 */
	public static String getCurrentTime(float timeZoneOffset,String format){
	    if(timeZoneOffset == 8)
	        return getCurrentBeiJingTime(format);
	    
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}
		int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(newTime);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(newTime, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(timeZone);
		return sdf.format(new Date());
	}
	
	/**
     * 获取北京时间
     * @param format 返回什么格式
     * @return
     */
    public static String getCurrentBeiJingTime(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(bjTimeZone);
        return sdf.format(new Date());
    }	
    
	/**
	 * 获取指定时区的时间
	 * @param timeZoneOffset 时区 西：-12 12 :东
	 * @return
	 */
	public static Date getCurrentTime(float timeZoneOffset){
	    if(timeZoneOffset == 8)
	        return getCurrentBeiJingTime();
	    
		if (timeZoneOffset > 13 || timeZoneOffset < -12) {
			timeZoneOffset = 0;
		}
		int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
		TimeZone timeZone;
		String[] ids = TimeZone.getAvailableIDs(newTime);
		if (ids.length == 0) {
			timeZone = TimeZone.getDefault();
		} else {
			timeZone = new SimpleTimeZone(newTime, ids[0]);
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_TAODA_DIRECTORY_T);
		sdf.setTimeZone(timeZone);
		return DateUtil.parseStrToDate(DateUtil.DATE_FORMAT_TAODA_DIRECTORY_T,sdf.format(new Date()));
	}
	
    /**
     * 获取指定时区的时间
     * @param
     * @return
     */
    public static Date getCurrentBeiJingTime(){
        
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_FORMAT_TAODA_DIRECTORY_T);
        sdf.setTimeZone(bjTimeZone);
        return DateUtil.parseStrToDate(DateUtil.DATE_FORMAT_TAODA_DIRECTORY_T,sdf.format(new Date()));
    }	

	/**
	 * 校验时间格式
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public Boolean checkDateFormat(String dateStr,String format){
		return Pattern.compile(format).matcher(dateStr).matches();
	}
	/**
	 * 计算2个日期之间相差的  以年、月、日为单位，各自计算结果是多少
	 * 比如：2011-02-02 到  2017-03-02
	 *  以年为单位相差为：6年
	 * 以月为单位相差为：73个月
	 * 以日为单位相差为：2220天
	 * @param sdate
	 * @param edate
	 * @param type s 秒 m 分 h 时 D 天 M 月 Y 年
	 * @return
	 */
	public static int twoDateBetween(Date sdate, Date edate,String type) {
		long result,timeUnit;
		Calendar from  =  Calendar.getInstance();
		from.setTime(sdate);
		Calendar to  =  Calendar.getInstance();
		to.setTime(edate);
		//只要年月
		int fromYear = from.get(Calendar.YEAR);
		int fromMonth = from.get(Calendar.MONTH);
		int toYear = to.get(Calendar.YEAR);
		int toMonth = to.get(Calendar.MONTH);
		if("s".equals(type)){
			timeUnit = 1000;
			result = (to.getTimeInMillis()  -  from.getTimeInMillis())  /  timeUnit;
		}else if("m".equals(type)){
			timeUnit = 1000 * 60;
			result = (to.getTimeInMillis()  -  from.getTimeInMillis())  /  timeUnit;
		}else if("h".equals(type)){
			timeUnit = 1000 * 60 * 60;
			result = (to.getTimeInMillis()  -  from.getTimeInMillis())  /  timeUnit;
		}else if("D".equals(type)){
			timeUnit = 1000 * 60 * 60 *24;
			result = (to.getTimeInMillis()  -  from.getTimeInMillis())  /  timeUnit;
		}else if("M".equals(type)){
			result = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
		}else if("Y".equals(type)){
			result = toYear  -  fromYear;
		}else{
			timeUnit = 1000 * 60 * 60;
			result = (to.getTimeInMillis()  -  from.getTimeInMillis())  /  timeUnit;
		}
		return Integer.parseInt(String.valueOf(result));
	}
	
	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00)
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return 
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
	    if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
	        throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
	    }
	    if (curTime == null || !curTime.contains(":")) {
	        throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
	    }
	    String[] args = sourceTime.split("-");
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    try {
	        long now = sdf.parse(curTime).getTime();
	        long start = sdf.parse(args[0]).getTime();
	        long end = sdf.parse(args[1]).getTime();
	        if (args[1].equals("00:00")) {
	            args[1] = "24:00";
	        }
	        if (end < start) {
                return !(now >= end && now < start);
	        } 
	        else {
                return now >= start && now < end;
	        }
	    } catch (ParseException e) {
	    	LOGGER.info(">>>时间转换异常",e);
	        throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
	    }
	}

	/**
	 * 转换获取一天的开始与结束时间
	 * @param dayStr
	 * @param type
	 * @return
	 */
	public static Date getDaySETime(String dayStr,String type){
		if(StringUtils.isEmpty(dayStr)){
			return null;
		}
		if("s".equals(type.toLowerCase())){
			dayStr=dayStr.trim().concat(" 00:00:00");
		}else{
			dayStr=dayStr.trim().concat(" 23:59:59");
		}

		Date date = null;
		try {
			date = new SimpleDateFormat(DATE_TIME_FORMAT_DEFAULT).parse(dayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取指定日期的前后时间
	 * @param specifiedDay 指定日期,格式固定yyyy-MM-dd
	 * @param day 0:返回入参日期, 正数:指定日期之后, 负数:指定日期之前
	 * @return 返回目标日期yyyy-MM-dd
	 */
	public static String getAppointDateBeforeOrAfter(String specifiedDay, int day) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			LOGGER.info(">>>日期转换异常", e);
			date = new Date();
		}
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, day); //设置为某一天
		
		String dayBeforeOrAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return dayBeforeOrAfter;
	}

	//得到本月的第一天
	public static String getMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat firstDay= new SimpleDateFormat("yyyy-MM-dd");
		return firstDay.format(calendar.getTime());
	}

	//得到本月的最后一天
	public static String getMonthLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat lastDay= new SimpleDateFormat("yyyy-MM-dd");
		return lastDay.format(calendar.getTime());
	}

	/**
	 * @return String 时间字符串
	 * @throws
	 * @Description: 获取当前系统时间戳 格式:yyyy-mm-dd HH:mm:ss
	 */
	public static String getCurrentTimeStamp() {
		return getCurrentDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param format 日期格式
	 * @return String 日期字符串
	 * @throws
	 * @Description: 获取当前系统日期
	 */
	public static String getCurrentDate(String format) {
		return getCurrentDateTimeStr(format);
	}

	/**
	 * @param format 时间格式
	 * @return String 时间字符串
	 * @throws
	 * @Description: 获取当前系统时间戳
	 */
	public static String getCurrentTimeStamp(String format) {
		return getCurrentDateTimeStr(format);
	}

	/**
	 * @param format 日期时间格式
	 * @return String 日期时间字符串
	 * @throws 1
	 * @Description: 获取当前日期时间字符串
	 */
	public static String getCurrentDateTimeStr(String format) {
		DateFormat formater = new SimpleDateFormat(format);
		return formater.format(new Date());
	}

	/**
	 * @param date   日期
	 * @param format 日期格式
	 * @return 日期字符串
	 * @Description:日期转化为字符串
	 */
	public static String getDateStr(Date date, String format) {
		DateFormat dateformat = new SimpleDateFormat(format);
		return dateformat.format(date);
	}


	
	
}
