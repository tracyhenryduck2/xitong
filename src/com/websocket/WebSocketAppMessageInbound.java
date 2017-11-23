package com.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.commons.lang.StringUtils;

import com.bean.ActionType;
import com.bean.ErrCode;
import com.memberManage.bean.ClientBean;
import com.message.AppLoginMessageBean;
import com.message.AppLoginParamBean;
import com.message.AppSendParamBean;
import com.message.MessageBean;
import com.siter.appaction.SiterAppDao;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WebSocketAppMessageInbound extends MessageInbound {

	//当前连接的用户名称
	private SiterAppDao siterAppdao;
	private String ip;
	private String access_token;
    private ClientBean user;

	
	public WebSocketAppMessageInbound(String ip) {
		siterAppdao = new SiterAppDao();
		this.ip = ip;
	}
	
	

	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
		this.user = siterAppdao.getClientBeanByAccessToken(access_token);
	}


	//建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		// 触发连接事件，在连接池中添加连接
		System.out.println("onOpen");
//		JSONObject result = new JSONObject();
//		result.element("type", "user_join");
//		result.element("user", this.user);
//		//向所有在线用户推送当前用户上线的消息
//		WebSocketMessageInboundPool.sendMessage(result.toString());
//		
//		result = new JSONObject();
//		result.element("type", "get_online_user");
//		result.element("list", WebSocketMessageInboundPool.getOnlineUser());
//		//向连接池添加当前的连接对象
//		WebSocketAppMessageInboundPool.addMessageInbound(this);
//		//向当前连接发送当前在线用户的列表
//		WebSocketAppMessageInboundPool.sendMessageToUser(this.user, result.toString());
	}



	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		System.out.println("onClose:"+status);
		WebSocketAppMessageInboundPool.removeMessageInbound(this);
//		JSONObject result = new JSONObject();
//		result.element("type", "user_leave");
//		result.element("user", this.user);
//		//向在线用户发送当前用户退出的消息
//		WebSocketAppMessageInboundPool.sendMessage(result.toString());
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		System.out.println("void onBinaryMessage(ByteBuffer message)");
		throw new UnsupportedOperationException("Binary message not supported.");
	}

	//客户端发送消息到服务器时触发事件
	@SuppressWarnings("rawtypes")
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//向所有在线用户发送消息
		System.out.println("发上来的消息:"+message.toString());
		//WebSocketMessageInboundPool.sendMessage(message.toString());
		try {
			JSONObject j = JSONObject.fromObject(message.toString());
	         if(j.has("params")){
	        	 j.remove("params");
	         }
	        MessageBean workflow = (MessageBean) JSONObject.toBean(j,  
	        		MessageBean.class);
        if(ActionType.APP_LOGIN.getName().equals(workflow.getAction()) ){
    		JSONObject j2 = JSONObject.fromObject(message.toString());
			//appws登录
			Map<String, Class> classMap = new HashMap<String, Class>();  
	        classMap.put("params", AppLoginParamBean.class);  
	        //将json转换成workflowBean  
	        AppLoginMessageBean workflow2 = (AppLoginMessageBean) JSONObject.toBean(j2,  
	        		AppLoginMessageBean.class, classMap);
        	
        	String token = workflow2.getParams().getToken();
			if(StringUtils.isNotEmpty(token)){
				     siterAppdao.addLogin(token, ip, new Date().getTime()/1000l);
					 setAccess_token(token);
					 JSONObject ds = new JSONObject();
					 ds.put("msgId", workflow.getMsgid());
					 ds.put("action", "appLoginResp");
					 ds.put("code", ErrCode.SUCCESS.getCode());
					 ds.put("desc", ErrCode.SUCCESS.getName());
					 this.getWsOutbound().writeTextMessage(CharBuffer.wrap(ds.toString()));
					 setAccess_token(token);
				     WebSocketAppMessageInboundPool.addMessageInbound(this);
			}else{
				System.out.println(ErrCode.JWT_NOT_EXIST_ERROR.getName());
			}
        	
        }else if(ActionType.APP_SEND.getName().equals(workflow.getAction())){
        	//appws发送消息
        	
        	
        }else if(ActionType.HEARTBEAT.getName().equals(workflow.getAction())){
			 JSONObject ds = new JSONObject();
			 ds.put("msgId", workflow.getMsgid());
			 ds.put("action", ActionType.HEARTBEATRESP.getName());
			 ds.put("code", ErrCode.SUCCESS.getCode());
			 ds.put("desc", ErrCode.SUCCESS.getName());
			 this.getWsOutbound().writeTextMessage(CharBuffer.wrap(ds.toString()));
        }else{
        	System.out.println(ErrCode.JSON_PARSE_ERROR.getName());
        }
        
		}catch(JSONException e){
		   e.printStackTrace();
			System.out.println(ErrCode.JSON_FORMAT_ERROR.getName()); 
		}
        
	}
}
