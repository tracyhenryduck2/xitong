package com.common;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import jxl.Cell;
import jxl.DateCell;

public class Common {

	public static final int PRECISION = 2;//全局字符精度
	public static SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat MONTH_FORMAT=new SimpleDateFormat("yyyy-MM");
	
	public static Long DateStrToLong(String date){
		try {
			return DATE_FORMAT.parse(date).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String DateLongToStr(Long date){
		if(date!=null){
			return DATE_FORMAT.format(date*1000);
		}else{
			return null;
		}
	}
	
	public static Long MoneyStrToLong(String money){
		return toIntSum(money);
	}
	
	public static String MoneyLongToStr(Long money){
		return toFloatSum(money);
	}
	
	public static String array2DBString(String[] arr) {
		if (arr == null) {
			return "";
		}
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				str += ",";
			}
			str += "'"+arr[i]+"'";
		}
		return str;
	}
	
	public static String array2String(String[] arr) {
		if (arr == null) {
			return "";
		}
		String str = "";
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				str += ",";
			}
			str += arr[i];
		}
		return str;
	}
	
	/**
	 * 将小数点右移N位 *10的N次方
	 * @param floatStr 数值
	 * @return
	 */
	public static Long toIntSum(String floatStr) {
		try {
			if(floatStr==null || floatStr.trim().length()==0) {
				return null;
			}
            BigDecimal bd = new BigDecimal(floatStr);
            bd = bd.movePointRight(PRECISION);
            return new Long(bd.longValue());
        }
        catch (NumberFormatException ex) {
        	return null;
        }
	}
	
	/**
	 * 将小数点左移N位 
	 * @param value 数值
	 * @return
	 */
	public static String toFloatSum(Long value) {
		try {
			if(value==null) {
				return "";
			}
            BigDecimal bd = new BigDecimal(value);
            bd = bd.movePointLeft(PRECISION);
            return bd.toPlainString();
        }
        catch (NumberFormatException ex) {
        	return null;
           //throw new BaseAppException("The float string can not be parsed into a number.", ex);
        }
	}

	/**
	 * 将小数点左移N位
	 * @param value 值
	 * @param num 左移位数
	 * @param showPrecision 显示精度
	 * @return
	 */
	public static String toFloatSum(Long value, int precision, int showPrecision) {
		try {
			if(value==null) {
				return "";
			}
            BigDecimal bd = new BigDecimal(value);
            bd = bd.movePointLeft(precision);
            String str = bd.toPlainString();
            if(showPrecision < precision ) {
            	String[] arr = str.split("\\.");
				if(arr!=null && arr.length==2) {
					if(arr[1].length()>showPrecision) {
						str = arr[0]+"."+arr[1].substring(0, showPrecision);
						return str;
					}
				}
            }
            return str;
        }
        catch (NumberFormatException ex) {
        	return null;
           //throw new BaseAppException("The float string can not be parsed into a number.", ex);
        }
	}
	
	/**
	 * 将小数点左移N位 
	 * @param value 数值
	 * @return
	 */
	public static String toFloatSum(Long value, int num) {
		try {
			if(value==null) {
				return "";
			}
            BigDecimal bd = new BigDecimal(value);
            bd = bd.movePointLeft(num);
            return bd.toPlainString();
        }
        catch (NumberFormatException ex) {
        	return null;
           //throw new BaseAppException("The float string can not be parsed into a number.", ex);
        }
	}
	
	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	public static String getFilePrefix(String fileName) {
		return fileName.substring(0,fileName.lastIndexOf("."));
	}
	
	
	public static String delNull(Object obj) {
		if(obj==null) {
			return "";
		} else {
			return obj.toString();
		}
	}
	
	/**
	 * excel c 为date类型时转化
	 * @param c
	 * @return
	 */
	public static String getExcelDateStr(Cell c) {
		DateCell dc = (DateCell) c;
        Date date = dc.getDate();
        TimeZone zone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // HH:mm:ss
        sdf.setTimeZone(zone);
        String sDate = sdf.format(date);
        return sDate;
	}

	public static String MonthLongToStr(Long date) {
		
		if(date!=null){
			return MONTH_FORMAT.format(date*1000);
		}else{
			return null;
		}
	}

	public static Long MonthStrToLong(String date) {
		try {
			return MONTH_FORMAT.parse(date).getTime()/1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获得某个的天总数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {  
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.YEAR, year);  
        cal.set(Calendar.MONTH, month-1);  
        // 某年某月的最后一天  
        return cal.getActualMaximum(Calendar.DATE);  
    }
	
	/**
	 * 获得某天是星期几
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 */
	public static int getWeek(int year, int month, int date) {
		 Calendar cal = Calendar.getInstance();  
         cal.set(Calendar.YEAR, year);  
         cal.set(Calendar.MONTH, month-1);  
         cal.set(Calendar.DATE, 1);
         return cal.get(Calendar.DAY_OF_WEEK);
	}
}
