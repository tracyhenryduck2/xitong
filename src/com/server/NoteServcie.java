package com.server;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

public class NoteServcie {
	
	public static String masterSecret="0cf4b44eea93f219d77b3b29"; //Master Secret 
	public static String appKey="0df279071e562ffd2de19f08"; //AppKey
	
	public static final int Recommending = 1;//信息推荐
	public static final int News = 2;//新闻公告
	public static final int Warning=3;//预警发布
	public static final int Consumption=4;//消费提示
	private int userId;
	private int type;
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public NoteServcie(int userId, int type) {
		this.userId=userId;
		this.type=type;
	}
	
	public NoteServcie(int type) {
		this.type=type;
	}


//	/**
//	 * 即时通知，请使用非静态方法，避免线程阻塞
//	 * 
//	 *  */
//
//	private static void note(int userId, int type, String tip) {
//		String msg=tip;
//		int groupId=0,id=0;
//		switch(type){
//		case N_TASK:
//			groupId=1;
//			id=2;
//			break;
//		case N_APPLY:
//			groupId=2;
//			id=7;
//			break;
//		case N_COMPANY_NOTICE:
//			groupId=1;
//			id=36;
//			break;
//		}
//		note(userId,msg,groupId,id);
//	}
	
	private static int count=11;

	private static void note(int userId,int type) {
		JPushClient jpush = new JPushClient(masterSecret,appKey);
		Map<String, Object> extra = new HashMap<String, Object>();
		String tag = userId+"";
		Map<String, Object> ios = new HashMap<String, Object>();
		ios.put("badge", 1);
		ios.put("sound", "default");
		ios.put("content-available", 1);
		extra.put("ios", ios);
		extra.put("type", type);
		MessageResult msgResult = jpush.sendNotificationWithTag(count++, tag,
				"", "", 0, extra);
		if (null != msgResult) {
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				System.out.println("发送成功， sendNo=" + msgResult.getSendno());
			} else {
				System.out.println("发送失败， 错误代码=" + msgResult.getErrcode()
						+ ", 错误消息=" + msgResult.getErrmsg());
				jpush = new JPushClient(masterSecret,appKey);
			}
		} else {
			jpush = new JPushClient(masterSecret,appKey);
		}
	}
	
	private static void noteAll(int type) {
		JPushClient jpush = new JPushClient(masterSecret,appKey);
		Map<String, Object> extra = new HashMap<String, Object>();
		Map<String, Object> ios = new HashMap<String, Object>();
		ios.put("badge", 1);
		ios.put("sound", "default");
		ios.put("content-available", 1);
		extra.put("ios", ios);
		extra.put("type", type);
		MessageResult msgResult = jpush.sendNotificationWithAppKey(count++, "八爪鱼", null);
		if (null != msgResult) {
			if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
				System.out.println("发送成功， sendNo=" + msgResult.getSendno());
			} else {
				System.out.println("发送失败， 错误代码=" + msgResult.getErrcode()
						+ ", 错误消息=" + msgResult.getErrmsg());
				jpush = new JPushClient(masterSecret,appKey);
			}
		} else {
			jpush = new JPushClient(masterSecret,appKey);
		}
	}
	
	
	public static void main(String[] args){
		
	}

	/**
	 * 即时消息的调用方式，目前采用线程式
	 */
	public void note() {
		new Thread(){
			public void run(){
				note(userId, type);
			}
		}.start();
	}
	
	
	/**
	 * 即时消息的调用方式，目前采用线程式
	 */
	public void noteAll() {
		new Thread(){
			public void run(){
				noteAll(1);
			}
		}.start();
	}
}
