package com.server.socketserver;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class AttendSocetListener implements ServletContextListener{
   // private SocketThread socketThread;
    private MultiThreadServer multiThreadServer;
    
    
	public void contextDestroyed(ServletContextEvent arg) { 
		
		System.out.println("contextDestroyed");
		if(null!=multiThreadServer && !multiThreadServer.isInterrupted()) 
		{ 
			multiThreadServer.closeSocketServer(); 
			multiThreadServer.interrupt(); 
		} 
	}
	
	
	@Override
	public void contextInitialized(ServletContextEvent arg) { 
	// TODO Auto-generated method stub 
		System.out.println("contextInitialized");
		if(null==multiThreadServer) 
		{ 
			//新建线程类 
			try {
				multiThreadServer=new MultiThreadServer();
				multiThreadServer.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		} 
	} 
}