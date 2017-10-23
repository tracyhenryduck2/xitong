package com.siter.appaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.common.BaseActionSupport;
import com.common.BeanConverter;
import com.common.MD5;
import com.common.SendMail;
import com.memberManage.bean.ClientBean;
import com.memberManage.bean.ClientTokenBean;
import com.memberManage.bean.MemberBean;
import com.memberManage.dao.ClientTokenDAO;
import com.sun.security.ntlm.Client;
import com.bean.*;

public class SiterAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private SiterAppDao siterAppdao = new SiterAppDao();
    private ClientTokenDAO clienttokendao = new ClientTokenDAO();
	
	
	/**
	 * 发送邮箱给客户
	 * http://SERVER[:PORT]/PROJECTNAME/app/SiterAction!emailtoMember.action
	 * @return
	 */

	public void emailtoMember(){
//		String hekrid=request.getParameter("hekrid");
//		MemberBean m = siterAppdao.getMemberInfoByHekrid(hekrid);
//		if(m!=null){
			   boolean flag = SendMail.sendAndCc(StaticBean.STMP_SERVER,StaticBean.STMP_FROM, "546342804@qq.com", "", "您收到一条报警", "您收到一条报警", StaticBean.STMP_FROM, StaticBean.MM,null); 
			     
			    Map<String,Object> map=new HashMap<String,Object>();
		        if(flag)	map.put("result", 1);
		        else     map.put("result", 2);
				outPrintJSONObject(map);
//		}else{
//		        Map<String,Object> map=new HashMap<String,Object>();
//		        map.put("result", 3);
//				outPrintJSONObject(map);
//		}

	}
	
	
	/**
	 * 移动端客户登录
	 * http://SERVER[:PORT]/PROJECTNAME/app/SiterAction!login.action
	 * @return
	 */
	
	public void login(){
		Map<String,Object> loginInfo=new HashMap<String,Object>();
	    try {
		String username = null;
		String password = null;
		String client_type = null;

		if (isJsonType(request.getContentType())) {
		    // load JSON object
				String obj = deserialize(request.getReader());
				JSONObject js = new JSONObject(obj);
				username =js.getString("username");
				password =js.getString("password");
				client_type = js.getString("clientType");
		}else{
			username = request.getParameter("username");
			password = request.getParameter("password");
			client_type = request.getParameter("clientType"); 
		}
		

		
		if(username!=null&&password!=null&client_type!=null){
			if(username.length()>0&&password.length()>0&&client_type.length()>0){
				ClientBean clientbean = siterAppdao.selectClientBean(username, password);
				if(clientbean!=null){
					//获取ip
					String ip = getIpAddr(request);
					//获取浏览器信息
					int port =request.getRemotePort();
					//时间戳
					Long time = new Date().getTime();
					//得出token
					String tokensub1 =clientbean.getId()+":"+ip+":"+port+":"+time;
					String tokensub2 = getNewPwd();
					
					String access_token = MD5.md5(tokensub1);
					String refresh_token = MD5.md5(tokensub1)+"."+MD5.md5(tokensub2);
					
					
					ClientTokenBean clienttoken = new ClientTokenBean();
					clienttoken.setClientId(clientbean.getId());
					clienttoken.setTime(time/1000);
					clienttoken.setAccessToken(access_token);
					clienttoken.setRefreshToken(refresh_token);
					clienttoken.setExpiresIn(StaticBean.EXPIRES_IN);
					clienttoken.setExpiresInRefresh(StaticBean.EXPIRES_IN_REFRESH);	
					clienttoken.setClientType(client_type);
					boolean flag =  clienttokendao.insert(clienttoken);
					

					
					
					if(flag){
						Map<String , Object> d = BeanConverter.toMap(clienttoken);
						
						for (String key : d.keySet()) { 
							loginInfo.put(key, d.get(key));
							} 
						loginInfo.put("code", ErrCode.SUCCESS_CLIENT.getCode());
						loginInfo.put("desc", ErrCode.SUCCESS_CLIENT.getName());	
					}else{
						loginInfo.put("code", ErrCode.SERVER_INERNAL_ERROR.getCode());
						loginInfo.put("desc", ErrCode.SERVER_INERNAL_ERROR.getName());
						response.setStatus(ErrCode.SERVER_INERNAL_ERROR.getCode());
					}
					
				}else{
					loginInfo.put("code", ErrCode.CLIENT_NOT_EXIST_ERROR.getCode());
					loginInfo.put("desc", ErrCode.CLIENT_NOT_EXIST_ERROR.getName());
					response.setStatus(ErrCode.CLIENT_NOT_EXIST_ERROR.getCode());
				}
			}else{
				loginInfo.put("code", ErrCode.LOGIN_INFO_NOT_COMPLETE.getCode());
				loginInfo.put("desc", ErrCode.LOGIN_INFO_NOT_COMPLETE.getName());
				response.setStatus(ErrCode.LOGIN_INFO_NOT_COMPLETE.getCode());
			}
		}else{
			loginInfo.put("code", ErrCode.PARAMETER_EMPTY_ERROR.getCode());
			loginInfo.put("desc", ErrCode.PARAMETER_EMPTY_ERROR.getName());
			response.setStatus(ErrCode.PARAMETER_EMPTY_ERROR.getCode());
		}
		

		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loginInfo.put("code", ErrCode.JSON_FORMAT_ERROR.getCode());
			loginInfo.put("desc", ErrCode.JSON_FORMAT_ERROR.getName());
			response.setStatus(ErrCode.JSON_FORMAT_ERROR.getCode());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loginInfo.put("code", ErrCode.SERVER_INERNAL_ERROR.getCode());
			loginInfo.put("desc", ErrCode.SERVER_INERNAL_ERROR.getName());
			response.setStatus(ErrCode.SERVER_INERNAL_ERROR.getCode());
		}
	    finally{
			outPrintJSONObject(loginInfo);
	    }
		
	}
	
	
	public void refreshToken(){
		Map<String,Object> loginInfo=new HashMap<String,Object>();
		String refresh_token = null;
		try{
			if (isJsonType( request.getContentType())) {
			    // load JSON object
					String obj = deserialize(request.getReader());
					JSONObject js = new JSONObject(obj);
					refresh_token =js.getString("refresh_token");
			}else{
				refresh_token = request.getParameter("refresh_token");
			}
			
			if(refresh_token!=null&&refresh_token.length()>0){
	        	ClientTokenBean client = siterAppdao.getClientBeanByRefreshToken(refresh_token);
		        if(client!=null){
					//时间戳
					Long time = new Date().getTime();
					
					if((client.getTime()+StaticBean.EXPIRES_IN_REFRESH)*1000L >= time){
						//获取ip
						String ip = getIpAddr(request);
						//获取浏览器信息
						int port =request.getRemotePort();
						//得出token
						String tokensub1 =client.getClientId()+":"+ip+":"+port+":"+time;
						String tokensub2 = getNewPwd();
						
						String access_token = MD5.md5(tokensub1);
						String refresh_token_new = MD5.md5(tokensub1)+"."+MD5.md5(tokensub2);
						
						
						ClientTokenBean clienttoken = new ClientTokenBean();

						clienttoken.setTime(time/1000);
						clienttoken.setAccessToken(access_token);
						clienttoken.setRefreshToken(refresh_token_new);
						if(siterAppdao.refreshToken(refresh_token,clienttoken)){
												
							loginInfo.put("accessToken", access_token);
							loginInfo.put("refreshToken", refresh_token_new);
							loginInfo.put("expiresIn", StaticBean.EXPIRES_IN);
							loginInfo.put("code", ErrCode.SUCCESS_CLIENT.getCode());
							loginInfo.put("desc", ErrCode.SUCCESS_CLIENT.getName());	
						}else{
							loginInfo.put("code", ErrCode.SERVER_INERNAL_ERROR.getCode());
							loginInfo.put("desc", ErrCode.SERVER_INERNAL_ERROR.getName());
							response.setStatus(ErrCode.SERVER_INERNAL_ERROR.getCode());
						}
					}else{
						
						loginInfo.put("code", ErrCode.REFRESH_TOKEN_TIME_OUT_ERROR.getCode());
						loginInfo.put("desc", ErrCode.REFRESH_TOKEN_TIME_OUT_ERROR.getName());
						response.setStatus(ErrCode.REFRESH_TOKEN_TIME_OUT_ERROR.getCode());
						
					}
					

		        	
		        }else{
		        	
					loginInfo.put("code", ErrCode.REFRESH_TOKEN_NOT_EXIST_ERROR.getCode());
					loginInfo.put("desc", ErrCode.REFRESH_TOKEN_NOT_EXIST_ERROR.getName());
					response.setStatus(ErrCode.REFRESH_TOKEN_NOT_EXIST_ERROR.getCode());
		        }
				
			}else{
				loginInfo.put("code", ErrCode.PARAMETER_EMPTY_ERROR.getCode());
				loginInfo.put("desc", ErrCode.PARAMETER_EMPTY_ERROR.getName());
				response.setStatus(ErrCode.PARAMETER_EMPTY_ERROR.getCode());
			}
			
			
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loginInfo.put("code", ErrCode.JSON_FORMAT_ERROR.getCode());
			loginInfo.put("desc", ErrCode.JSON_FORMAT_ERROR.getName());
			response.setStatus(ErrCode.JSON_FORMAT_ERROR.getCode());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			loginInfo.put("code", ErrCode.SERVER_INERNAL_ERROR.getCode());
			loginInfo.put("desc", ErrCode.SERVER_INERNAL_ERROR.getName());
			response.setStatus(ErrCode.SERVER_INERNAL_ERROR.getCode());
		}finally{
			outPrintJSONObject(loginInfo);
		}
		
	}
	
	
	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) { 
	       String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip; 
	 }
	
	
	/**
	 * 移动端获取客户信息
	 * http://SERVER[:PORT]/PROJECTNAME/app/SiterAction!profile.action
	 * @return
	 */
	public void profile(){
		Map<String,Object> output=new HashMap<String,Object>();
		String token = request.getHeader("Authorization");
		
		  try{
				if(token.length()>0){
					if(token.startsWith("Bearer ")){
						
						String access_token = token.substring("Bearer ".length());
						boolean flag_exist = siterAppdao.isAccessTokenExist(access_token);
						if(flag_exist){
							
							if(!isTokenTimeOut(access_token)){
							ClientBean beana =	siterAppdao.getClientBeanByAccessToken(access_token);
							Map<String , Object> d = BeanConverter.toMap(beana);
							
							for (String key : d.keySet()) { 
								output.put(key, d.get(key));
								}
							
							output.put("code", ErrCode.SUCCESS_CLIENT.getCode());
							output.put("desc", ErrCode.SUCCESS_CLIENT.getName());
							}else{
								output.put("code", ErrCode.JWT_TIME_OUT_ERROR.getCode());
								output.put("desc", ErrCode.JWT_TIME_OUT_ERROR.getName());
								response.setStatus(ErrCode.JWT_TIME_OUT_ERROR.getCode());
							}
									
						}else{	
							output.put("code", ErrCode.JWT_NOT_EXIST_ERROR.getCode());
							output.put("desc", ErrCode.JWT_NOT_EXIST_ERROR.getName());
							response.setStatus(ErrCode.JWT_NOT_EXIST_ERROR.getCode());
						}
	                    
					}else{
						output.put("code", ErrCode.JWT_PARSE_ERROR.getCode());
						output.put("desc", ErrCode.JWT_PARSE_ERROR.getName());
						response.setStatus(ErrCode.JWT_PARSE_ERROR.getCode());
					}
					
					
				}else{
					output.put("code", ErrCode.JWT_PARSE_ERROR.getCode());
					output.put("desc", ErrCode.JWT_PARSE_ERROR.getName());
					response.setStatus(ErrCode.JWT_PARSE_ERROR.getCode());
				}
		  }catch(NullPointerException e){
				output.put("code", ErrCode.JWT_PARSE_ERROR.getCode());
				output.put("desc", ErrCode.JWT_PARSE_ERROR.getName());
				response.setStatus(ErrCode.JWT_PARSE_ERROR.getCode());
		  }finally{
				outPrintJSONObject(output);
		  }

		
	}
	
	private boolean isTokenTimeOut(String access_token){
		
		Long time = siterAppdao.getTokenTime(access_token);
		Long time_now = new Date().getTime();
		
		if(((time+StaticBean.EXPIRES_IN) * 1000l)<time_now)
		
		return true;
		
		else return false;
	}
	
	private boolean isJsonType(String jsonType){
		
		if ((jsonType != null) && jsonType.equalsIgnoreCase("application/json")){
			return true;
		}
		else return false;
		
	}
	
	/**
	 * 生成8位随机数字密码
	 * @return
	 */
	public String getNewPwd()
	{
		  Random random = new Random(); 
	      String result="";

	      for(int i=0;i<16;i++){
	         result+=random.nextInt(10);    
	      }
	      return result;
	}
	
	
	 public static String deserialize(Reader reader) throws JSONException {
		// read content
		BufferedReader bufferReader = new BufferedReader(reader);
		String line;
		StringBuilder buffer = new StringBuilder();

		try {
		while ((line = bufferReader.readLine()) != null) {
		   buffer.append(line);
		    }
		 } catch (IOException e) {
		 throw new JSONException(e);
		 }
		
		   return buffer.toString();
	 }
}
