package com.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Date;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.memberManage.bean.ClientBean;
import com.siter.appaction.SiterAppDao;

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
		System.out.println("onClose");
//		WebSocketAppMessageInboundPool.removeMessageInbound(this);
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
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		//向所有在线用户发送消息
		System.out.println("发上来的消息:"+message.toString());
		//WebSocketMessageInboundPool.sendMessage(message.toString());
		
		try {
			JSONObject js = new JSONObject(message.toString());
			if(js.has("action") && "appLogin".equals(js.get("action"))){
				JSONObject js2 = js.getJSONObject("params");
				if(js2.has("token")){
					String token = js2.getString("token");
					if(StringUtils.isNotEmpty(token)){

						     siterAppdao.addLogin(token, ip, new Date().getTime()/1000l);
							 setAccess_token(token);
							 JSONObject ds = new JSONObject();
							
							 ds.put("msgId", 0);
							 ds.put("action", "appLoginResp");
							 ds.put("code", 200);
							 ds.put("desc", "success");
							 this.getWsOutbound().writeTextMessage(CharBuffer.wrap(ds.toString()));
						 
					}
					
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
