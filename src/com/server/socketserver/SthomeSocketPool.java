package com.server.socketserver;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.device.bean.DeviceBean;
import com.websocket.WebSocketAppMessageInbound;


public class SthomeSocketPool {
	//保存连接的Socket连接
	private static final Map<String,SthomeSocketThread > connections = new HashMap<String,SthomeSocketThread>();
	
	
	//获取所有的设备token
	public static Set<String> getOnlineDevice(){
		return connections.keySet();
	}
	
	/**
	 * @return 
	 * 
	 */
	public static void addSocketDevice(SthomeSocketThread st){
		//添加连接的设备
		System.out.println("add device : " + st.getDeviceToken() + " join..");
		connections.put(st.getDeviceToken(), st);
	}
	
	
	public static void removeSocketDevice(SthomeSocketThread st){
		//移除连接
		System.out.println("remove device : " + st.getDeviceToken() + " exit..");
		connections.remove(st.getDeviceToken());
	}
	
	
	public static void sendMessageToDevice(String access_token,String message){
		//向特定的用户发送数据
		System.out.println("send message to device : " + access_token + " ,message content : " + message);
		SthomeSocketThread inbound = connections.get(access_token);
		if(inbound != null){
			inbound.sendMessage(message);
		}
	}
	
}
