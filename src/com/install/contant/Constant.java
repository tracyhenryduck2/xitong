package com.install.contant;

public class Constant {
//	图片路径
	public static final String PIC_PAGE = "market_avatar"; 

//	用户名密码错误或该账户被停用！
	public static final int USER_ERROR = 1000;
//	请正确填写用户名和密码！
	public static final int USER_FORMAT = 1001;
//	请填写用户名和密码！
	public static final int USER_NULL = 1002;
//	注册失败
	public static final int REGISTER_FAIL = 2001;
//	手机号已被注册
	public static final int REGISTER_RE = 2002;
//	用户名已被注册
	public static final int REGISTER_EXIST = 2003;
//	两次密码不一致
	public static final int REGISTER_PASSWORD_RE = 2004;
//	填写的内容为空
	public static final int REGISTER_NULL = 2004;
//	没有权限查看
	public static final int INSPECTOR_R = 3001;
//	没有查找到改用户
	public static final int INSPECTOR_NULL = 3002;
	
//	成功
	public static final int SUCCESS = 200;
//	图片上传失败
	public static final int PICTURE_UPE = 4001;
//	图片上传参数不对
	public static final int PICTURE_PAR = 4002;
//	安装信息上传数据出错
	public static final int INSTALL_INFO_PAR = 4003;
//	信息修改失败
	public static final int INSTALL_INFO_UPDATE = 4004;
//	信息保存失败
	public static final int INSTALL_INFO_INSERT = 4005;
//	没有查找到相关设备信息
	public static final int NO_PRODUCT = 1003;
	
	public static String errorShow (int error) {
		String showLog = "";
		switch (error) {
		case 1000:
			showLog = "用户名密码错误或该账户被停用！";
			break;
		case 1001:
			showLog = "请正确填写用户名和密码！";
			break;
		case 1002:
			showLog = "请填写用户名和密码！";
			break;
		case 1003:
			showLog = "没有查找到相关设备信息";
			break;
		case 2001:
			showLog = "注册失败";
			break;
		case 2002:
			showLog = "手机号已被注册";
			break;
		case 2003:
			showLog = "用户名已被注册";
			break;
		case 2004:
			showLog = "两次密码不一致";
			break;
		case 2005:
			showLog = "填写的内容为空";
			break;
		case 3001:
			showLog = "没有权限查看";
			break;
		case 3002:
			showLog = "没有找到该用户";
			break;
		case 200:
			showLog = "成功";
			break;
		case 4001:
			showLog = "图片上传失败";
			break;			
		default:
			showLog = "";
			break;
		}
		return showLog;
	}
	
}
