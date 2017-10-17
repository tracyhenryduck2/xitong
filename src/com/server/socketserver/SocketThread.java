package com.server.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class SocketThread extends Thread
{
	private ServerSocket serverSocket = null; 
	
	public SocketThread(ServerSocket serverScoket){ 
		try { 
				if(null == serverSocket){ 
					this.serverSocket = new ServerSocket(8087); 
					
					System.out.println("socket start"); 
				} 
			} catch (Exception e) { 
				System.out.println("SocketThread创建socket服务出错"); 
				e.printStackTrace(); 
			} 
	} 
	
	public void run(){ 
		while(true){ 
			try { 
				if(serverSocket==null)
				{
					break;
				}
				else if(serverSocket.isClosed())
				{
					break;
			    }
				Socket socket = serverSocket.accept(); 
				if(null != socket && !socket.isClosed()){
					System.out.println("SocketThread创建了一个socket"); 
					//处理接受的数据 
					Thread t = new Thread(new SocketOperate(socket)); 
					t.start(); 
				}else{
				  break;
				}
			}catch (Exception e) { 
				System.out.println("SocketThread创建socket服务出错"); 
				e.printStackTrace(); 
			} 
		} 
	}
	
	public void closeSocketServer(){ 
		try { 
		if(null!=serverSocket && !serverSocket.isClosed()) 
			{ 
			 serverSocket.close(); 
			} 
		} catch (IOException e) { 
		// TODO Auto-generated catch block 
		  e.printStackTrace(); 
		} 
	} 
}