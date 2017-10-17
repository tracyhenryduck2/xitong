package com.websocket;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.system.bean.SUserBean;

public class WebSocketMessageInboundPool {

	//保存连接的MAP容器
	private static final Map<Long,WebSocketMessageInbound > connections = new HashMap<Long,WebSocketMessageInbound>();
	
	//向连接池中添加连接
	public static void addMessageInbound(WebSocketMessageInbound inbound){
		//添加连接
		System.out.println("user : " + inbound.getUser().getId() + " join..");
		connections.put(inbound.getUser().getId(), inbound);
	}
	
	//获取所有的在线用户
	public static Set<Long> getOnlineUser(){
		return connections.keySet();
	}
	
	public static void removeMessageInbound(WebSocketMessageInbound inbound){
		//移除连接
		System.out.println("user : " + inbound.getUser().getId() + " exit..");
		connections.remove(inbound.getUser().getId());
	}
	
	public static void sendMessageToUser(SUserBean user,String message){
		try {
			//向特定的用户发送数据
			System.out.println("send message to user : " + user.getId() + " ,message content : " + message);
			WebSocketMessageInbound inbound = connections.get(user.getId());
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
			Set<Long> keySet = connections.keySet();
			for (Long key : keySet) {
				WebSocketMessageInbound inbound = connections.get(key);
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
