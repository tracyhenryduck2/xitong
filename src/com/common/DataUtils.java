package com.common;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DataUtils {
	
	public static long getLongValue(Object obj){
		if (obj == null) {
			return 0;
		} else if (obj instanceof Long) {
			return (Long) obj;
		} else {
			if (obj.toString().equals("")) {
				return 0;
			}
			return new Long(obj.toString());
		}
	}

	public static int getIntegerValue(Object obj){
		if (obj == null) {
			return 0;
		} else if (obj instanceof Integer) {
			return (Integer) obj;
		} else {
			if (obj.toString().equals("")) {
				return 0;
			}
			return new Integer(obj.toString());
		}
	}

	
	public static Long getLong(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof Long) {
			return (Long) obj;
		} else {
			if (obj.toString().equals("")) {
				return null;
			}
			return new Long(obj.toString());
		}
	}
	
	public static Float getFloat(Object obj){
		if (obj == null) {
			return null;
		} else if (obj instanceof Float) {
			return (Float) obj;
		} else {
			if (obj.toString().equals("")) {
				return null;
			}
			return new Float(obj.toString());
		}
	}
	public static Double getDoubleValue(Object obj){
		if (obj == null) {
			return 0d;
		} else if (obj instanceof Double) {
			return (Double) obj;
		} else {
			if (obj.toString().equals("")) {
				return 0d;
			}
			return new Double(obj.toString());
		}
	}
	
	public static String getString(Object obj) {
		if (obj == null) {
			return null;
		} else if (obj instanceof String) {
			return (String) obj;
		} else if (obj instanceof java.sql.Date
				|| obj instanceof java.sql.Timestamp) {
			SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
			return sdf.format(obj);
		} else {
			return obj.toString();
		}
	}
	
	public static Boolean getBoolean(Object obj) {
		if (obj instanceof Boolean) {
			return (Boolean) obj;
		} else {
			if (obj == null) {
				return null;
			}
			return new Boolean(obj.toString());
		}
	}
	
	
	public static BigDecimal getBigDecimal(Object obj) {
		if(obj==null) {
			return null;
		}
		return new BigDecimal(obj.toString());
	}
	
	public static java.util.Date getUtilDate(Object obj) {
		if(obj==null) {
    		return null;
    	}
    	return (java.util.Date)((Timestamp)obj);
	}

	public static Float getFloatValue(Object obj) {
		if (obj == null) {
			return 0f;
		} else if (obj instanceof Float) {
			return (Float) obj;
		} else {
			if (obj.toString().equals("")) {
				return 0f;
			}
			return new Float(obj.toString());
		}
	}
	
	
}
