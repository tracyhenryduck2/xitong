package com.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.system.dao.SUserDAO;
import com.google.gson.JsonObject;
import com.sun.xml.internal.bind.v2.runtime.Name;
import com.system.action.SFunctionGroupAction;
import com.system.bean.SUserBean;




public class IndexAction  extends BaseActionSupport{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	
	public String index(){
		String result="input";
		if(session.get("user")!=null){
			return "success";
		}
		if(username!=null&&password!=null){
			if(username.length()>0&&password.length()>0){
				SUserDAO dao = new SUserDAO();
				SUserBean user=dao.selectUser(username,password);
				if(user!=null){
					session.put("user", user);
					List<Map<String,Object>> Menulist = SFunctionGroupAction.getMenulist(user.getType());
					JSONArray rootObject = JSONArray.fromObject(Menulist);
					session.put("menu",rootObject);
					result="success";
				}else{
					showMessage="用户名密码错误或该账户被停用！";
				}
			}else{
				showMessage="请正确填写用户名和密码！";
			}
		}else{
			showMessage=null;
		}
		return result;
	}
	
	/**
	 * 手机接口
	 */
	public void pindex(){
		String result="input";
//		if(session.get("user")!=null){
//			SUserDAO dao = new SUserDAO();
//			SUserBean user=dao.selectUser(username,password);
//			return "success";
//		}
		Map<String,Object> map=new HashMap<String,Object>();
		if(username!=null&&password!=null){
			
			if(username.length()>0&&password.length()>0){
				SUserDAO dao = new SUserDAO();
				SUserBean user=dao.selectUser(username,password);
				if(user!=null){
//					session.put("user", user);
//					List<Map<String,Object>> Menulist = SFunctionGroupAction.getMenulist(user.getType());
//					JSONArray rootObject = JSONArray.fromObject(Menulist);
//					session.put("menu",rootObject);
//					JSONArray rootObject = JSONArray.fromObject(user);
					map.put("result", 200);
					map.put("content",JSONObject.fromObject(user));
				}else{
					showMessage="用户名密码错误或该账户被停用！";
					map.put("result",1000);
				}
			}else{
				showMessage="请正确填写用户名和密码！";
				map.put("result",1001);
			}
		}else{
			showMessage=null;
			map.put("result",1002);
		}
		outPrintJSONObject(map);
	}
	
	
	public String logout() {
		if(session.get("user")!=null){
			session.remove("user");
		}
		return "input";
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
	
}
