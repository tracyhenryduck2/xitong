package com.server.socketserver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class AttendSocetListener implements ServletContextListener{
    private SocketThread socketThread;
    
    
	public void contextDestroyed(ServletContextEvent arg) { 
		
		System.out.println("contextDestroyed");
		if(null!=socketThread && !socketThread.isInterrupted()) 
		{ 
			socketThread.closeSocketServer(); 
			socketThread.interrupt(); 
		} 
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent arg) { 
	// TODO Auto-generated method stub 
		System.out.println("contextInitialized");
		if(null==socketThread) 
		{ 
			//新建线程类 
			socketThread=new SocketThread(null); 
			//启动线程 
			socketThread.start(); 
		} 
	} 
}