package com.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.system.bean.SUserBean;

public class BaseActionSupport extends ActionSupport implements
		ServletResponseAware, ServletRequestAware, SessionAware,UserAware {
	private static final long serialVersionUID = 1L;

	/**  跳转常量定义 */
	public final static String success = "common_success";//成功
	public final static String error = "error";//失败
	public final static String exception = "common__exception";//数据异常，操作失败
	public final static String reload_success = "reload_success";//成功之后reload主界面
	public final static String href_success = "href_success";//成功之后转向action
	public final static String self_error = "self_error";//失败
	public final static String no_result="no_result";//无结果
	/** Aware */
	protected HttpServletRequest request = null;
	protected HttpServletResponse response = null;
	protected Map<String, Object> session = null;
	protected SUserBean user = null;

	/** 公共action变量 */
	protected String oper="0";//1 =编辑 ，0=新增
	public Page page = new Page();
	public PrintWriter out = null;
	protected String showMessage;//提示消息

	private String read = "0";
	
	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public void export() {
		 
	}
	
	public void outPrint(String str) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			System.out.println(str);
			out = response.getWriter();
			out.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	public void outPrintJSON(Object obj) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			out = response.getWriter();
			JSONArray rootObject = JSONArray.fromObject(obj);
			out.print(rootObject);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public void outPrintJSONObject(Object obj) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		try {
			out = response.getWriter();
			JSONObject rootObject = JSONObject.fromObject(obj);
			out.print(rootObject);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	public void initExportAll(ExportExcel export) {
		page.setFlag(1L);
		page.setPageSize(10000000);
		String data = request.getParameter("tableHeadData");
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject("["+data+"]");
		System.out.println("jsonArray:"+jsonArray);
		if(jsonArray==null || jsonArray.size()<2) {
			return;
		}
		//net.sf.json.JSONObject titleObject = jsonArray.getJSONObject(0);
		net.sf.json.JSONObject titleObject = jsonArray.getJSONObject(0);
		net.sf.json.JSONObject fieldObject = jsonArray.getJSONObject(1);
		String[] titleArray = new String[titleObject.size()];
		String[] fieldArr = new String[fieldObject.size()];
		
		for(int i=0;i<titleObject.size();i++) {
			titleArray[i] = titleObject.getString(i+"").replace("<br>", "");
			fieldArr[i] = fieldObject.getString(i+"");
		}
		String fieldNames = jsonArray.getString(1);
		fieldNames.split(",");
		String[][] arrs = new String[fieldArr.length][4];
		for(int i=0;i<fieldArr.length;i++) {
			arrs[i][0] = titleArray[i];
			System.out.println(fieldArr[i]);
			if(fieldArr[i]!=null && fieldArr[i].length()>5) {
				arrs[i][1] = fieldArr[i].substring(5);
			}
			arrs[i][2] = "1";
			arrs[i][3] = "20";
		}
		
		export.setParam(arrs);
	}
	
	public String getShowMessage() {
		return showMessage;
	}

	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}


	public SUserBean getUser() {
		return user;
	}

	public void setUser(SUserBean user) {
		this.user = user;
	}

	
}
