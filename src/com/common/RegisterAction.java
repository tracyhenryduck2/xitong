package com.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.dao.SUserDAO;
import com.system.bean.SUserBean;




public class RegisterAction  extends BaseActionSupport{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String password_confirm;
	private String phone;
	private Long type;
	private SUserBean suserbean;
	
	
	public String register(){
		String result="input";

		if(username!=null&&password!=null&&password_confirm!=null&&phone!=null && type != null){
			if(password.equals(password_confirm)){
				SUserDAO dao = new SUserDAO();
				SUserBean user=dao.hasRegisterUser(username);
				if(user ==null){
					SUserBean user2=dao.hasRegisterPhone(phone);
					
					if(user2==null){
						suserbean = new SUserBean();
						suserbean.setPassword(password);
						suserbean.setPhone(phone);
						suserbean.setType(type);
						suserbean.setUsername(username);
						long time = new Date().getTime()/1000;	   
						suserbean.setCreatetime(time);
						boolean result2 = dao.insert(suserbean); 
						if(result2){
							showMessage="注册成功";	
						}else{
							showMessage="注册失败";	
						}
						result="success";
					}else{
						showMessage="手机号已被注册";
					}

				}else{
					showMessage="用户名已被注册";
				}
			}else{
				showMessage="两次密码不一致";
			}
		}else{
			showMessage="填写的内容为空";
		}
		return result;
	}
	
	/**
	 * phone interface
	 * @return
	 */
	public void pRegister(){
		Map<String,Object> map=new HashMap<String,Object>();
		int errorDode;
		if(username!=null&&password!=null&&password_confirm!=null&&phone!=null && type != null){
			if(password.equals(password_confirm)){
				SUserDAO dao = new SUserDAO();
				SUserBean user=dao.hasRegisterUser(username);
				if(user ==null){
					SUserBean user2=dao.hasRegisterPhone(phone);
					
					if(user2==null){
						suserbean = new SUserBean();
						suserbean.setPassword(password);
						suserbean.setPhone(phone);
						suserbean.setType(type);
						suserbean.setUsername(username);
						long time = new Date().getTime()/1000;	   
						suserbean.setCreatetime(time);
						boolean result2 = dao.insert(suserbean); 
						if(result2){
//							String rootObject = JSONArray.fromObject(suserbean).toString();
//							showMessage=rootObject;	
//							map.put("content", JSONObject.fromObject(object));
							errorDode=200;
						}else{
//							showMessage="注册失败";	
							errorDode=2001;	
						}
					}else{
//						showMessage="手机号已被注册";
						errorDode=2002;
					}

				}else{
//					showMessage="用户名已被注册";
					errorDode=2003;
				}
			}else{
//				showMessage="两次密码不一致";
				errorDode=2004;
			}
		}else{
//			showMessage="填写的内容为空";
			errorDode=2005;
		}
		
		
		map.put("result", errorDode);
		outPrintJSONObject(map);
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getPassword_confirm() {
		return password_confirm;
	}



	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
}
