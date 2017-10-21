package com.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

import com.memberManage.bean.ClientBean;
import com.siter.appaction.SiterAppDao;
import com.system.bean.SUserBean;

public class WebSocketAppMessageInbound extends MessageInbound {

	//当前连接的用户名称
	private ClientBean user;
	private SiterAppDao siterAppdao;

	public WebSocketAppMessageInbound(ClientBean user) {
		System.out.println("WebSocketAppMessageInbound:"+user);
		this.user = user;
	}

	
	public WebSocketAppMessageInbound() {
		siterAppdao = new SiterAppDao();
	}
	
	public ClientBean getUser() {
		return this.user;
	}

	
	
	
	public void setUser(String token) {
       
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
	}
}