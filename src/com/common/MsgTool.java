package com.common;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class MsgTool {
	public static String SendURL="http://gw.api.taobao.com/router/rest";
	public static String AppKey="23334143";
	public static String Secret="8cd95229cd4a108885b714e18eca61c9";
	public static void sendMsg(String recNum,String signName,String code,String para) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(SendURL, AppKey, Secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		req.setSmsParamString(para);
		req.setRecNum(recNum);
		req.setSmsTemplateCode(code);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
	}

	public static void main(String[] args) throws ApiException{
		
		
	}
	

}
