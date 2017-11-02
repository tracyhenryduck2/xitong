package com.websocket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.memberManage.bean.ClientBean;
import com.system.bean.SUserBean;

public class WebSocketAppMessageInboundPool {

	//保存连接的MAP容器
	private static final Map<String,WebSocketAppMessageInbound > connections = new HashMap<String,WebSocketAppMessageInbound>();
	
	//向连接池中添加连接
	public static void addMessageInbound(WebSocketAppMessageInbound inbound){
		//添加连接
		System.out.println("user : " + inbound.getAccess_token() + " join..");
		connections.put(inbound.getAccess_token(), inbound);
	}
	
	//获取所有的在线用户
	public static Set<String> getOnlineUser(){
		return connections.keySet();
	}
	
	public static void removeMessageInbound(WebSocketAppMessageInbound inbound){
		//移除连接
		System.out.println("user : " + inbound.getAccess_token() + " exit..");
		connections.remove(inbound.getAccess_token());
	}
	
	public static void sendMessageToUser(String access_token,String message){
		try {
			//向特定的用户发送数据
			System.out.println("send message to user : " + access_token + " ,message content : " + message);
			WebSocketAppMessageInbound inbound = connections.get(access_token);
			if(inbound != null){
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//向所有的用户发送消息
	public static void sendMessage(String message){
		try {
			Set<String> keySet = connections.keySet();
			for (String key : keySet) {
				WebSocketAppMessageInbound inbound = connections.get(key);
				if(inbound != null){
					System.out.println("send message to user : " + key + " ,message content : " + message);
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
