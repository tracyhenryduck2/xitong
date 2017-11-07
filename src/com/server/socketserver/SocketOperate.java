package com.server.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
public class SocketOperate implements Runnable { 
	private Socket socket; 
	//该线程所处理的Socket所对应的输入流 
	BufferedReader br = null; 
	String str = null; 
	String content = null; 
	InputStreamReader reader=null; 
	
	public SocketOperate(Socket socket) throws IOException 
	{ 
		this.socket = socket; 
		reader = new InputStreamReader(this.socket.getInputStream(),"gbk"); 
		br = new BufferedReader(reader); 
	}

	@Override
	public void run() 
	{ 
		try
		{ 
			// 采用循环不断从Socket中读取客户端发送过来的数据 
			while (true) 
			{ 

				content = readFromClient(); 
				System.out.println(content);
				
				if (content == null) 
					{ 
						break; 
					} 
				OutputStream os = socket.getOutputStream(); 
				os.write(("RES, OK,<年班,小明>, ,#" + "\n").getBytes("gbk")); 
				os.flush();
			} 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
	}

	//定义读取客户端数据的方法 
	private String readFromClient() 
	{ 
		try
		{   

			str = br.readLine(); 

			return str; 
		} 
		//如果捕捉到异常，表明该Socket对应的客户端已经关闭 
		catch (IOException e) 
		{ 
			e.printStackTrace();
			try {
				br.close();
				reader.close();
				socket.close();
			} catch (IOException ex) {
			// TODO Auto-generated catch block
				ex.printStackTrace();
			} 
		} 
		return null; 
	} 
}
