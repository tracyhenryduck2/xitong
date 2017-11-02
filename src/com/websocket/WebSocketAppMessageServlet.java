package com.websocket;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;

import com.memberManage.bean.ClientBean;
import com.system.bean.SUserBean;


//如果要接收浏览器的ws://协议的请求就必须实现WebSocketServlet这个类
public class WebSocketAppMessageServlet extends org.apache.catalina.websocket.WebSocketServlet {

	private static final long serialVersionUID = 1L;
	
	public ClientBean getUser(HttpServletRequest request){
		return (ClientBean) request.getSession().getAttribute("user");
	}

	//跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol,HttpServletRequest request) {
    	String addr = request.getRemoteAddr();
    	System.out.println("addr:"+addr);
        return new WebSocketAppMessageInbound(request.getRemoteAddr());
    }
}
