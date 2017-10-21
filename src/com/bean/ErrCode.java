package com.bean;

public enum ErrCode {
     
	  
	  SUCCESS_DEVICE(8200000,"Success"), 
	  UNKNOWN_DEVICE_ERROR(8400000,"Error"), 
	  JSON_FORMAT_ERROR(8400001,"Json parse error"), 
	  JWT_PARSE_ERROR(8400002,"JWT parse error"),  
	  BEYOND_UPPER_LIMIT_ERROR(8400003,"The field contains a value that is too high"),  
	  BEYOND_LOWER_LIMIT_ERROR(8400004,"The field contains a value that is too low"),   
	  PROPERTY_NOT_EXIST_ERROR(8400006,"Field not exist"),  
	  DEVTID_NOT_MATCH_ERROR(8400008,"devTid not match"),   
	  CLIENT_NOT_EXIST_ERROR(8400010,"User does not exist"),
	  DEVICE_NOT_BELONG_TO_USER_ERROR(8400012,"Device does not belong to user"),
	  DEVICE_REPEAT_LOGIN_ERROR(8400013,"Device repeat login"),
	  DEVICE_TOKEN_ERROR(8400017,"Device token can not verification"),
	  DEVICE_NOT_ONLINE(8400018,"Device is not online"),
	  PRODUCT_KEY_NOT_AVAILABLE_ERROR(8400027,"Product key not available"),
	  PIN_SSID_NOT_AVAILABLE_ERROR(8400028,"PinCode or SSID not available"),
	  BIND_FAiL_TIME_OUT_ERROR(8400029,"Bind failed due to timeout error"),
      CAN_NOT_FORCE_BIND_ERROR(8400031,"Can not force bind device"),
      SERVER_INERNAL_ERROR(8500000,"Internal error"),
      LINK_ERROR(8500001,"Link error"),
      
	
	  SUCCESS_CLIENT(9200000,"Success"),
	  UNKNOWN_ERROR(9400000,"Error"),
	  APP_REPEAT_LOGIN_ERROR(9400002,"App repeat login error"),
	  APPTID_EMPTY_ERROR(9400003,"appTid can not be empty"),
	  AUTHORIZATION_ALREADY_EXIST_ERROR(9400004,"Authorization already exists"),
	  AUTHORIZATION_NOT_EXIST_ERROR(9400005,"Authorization does not exist"),
	  MODIFY_CLIENT_PROFILE_FAILED_ERROR(9400009,"Modified user profile failed"),
	  CHECK_VERIFY_CODE_ERROR(9400010,"Check verify code error"),
	  REPEAT_BIND_ERROR(9400013,"Bind failed due to repeat bind"),
	  DEVICE_NOT_BELONG_CLIENT_ERROR(9400014,"Device does not belong to user"),
	  DEVTID_EMPTY_ERROR(9400017,"devTid can not be empty"),
	  LOGIN_INFO_NOT_COMPLETE(9400018,"Login info not complete"),
	  PARAMETER_EMPTY_ERROR(9400019,"Parameter is empty");
	  
	  
	
	
	    // 成员变量  
	    private String name;  
	    private int code;  
	    // 构造方法  
	    private ErrCode( int code,String name) {  
	        this.name = name;  
	        this.code = code;  
	    }  
	    // 普通方法  
	    public static String getName(int index) {  
	        for (ErrCode c : ErrCode.values()) {  
	            if (c.getCode() == index) {  
	                return c.name;  
	            }  
	        }  
	        return null;  
	    }  
	    // get set 方法  
	    public String getName() {  
	        return name;  
	    }  
	    public void setName(String name) {  
	        this.name = name;  
	    }  
	    public int getCode() {  
	        return code;  
	    }  
	    public void setCode(int code) {  
	        this.code = code;  
	    } 
}
