package com.message;

import java.io.Serializable;

public class MessageBean implements Serializable{

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   private	int msgid;   
   private String action;
   private int  code;
   private String desc;
   
   
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

   
}
