package com.server.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer extends Thread{
    private int port = 6666;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 100000000;
    
    public MultiThreadServer() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_SIZE);
        System.out.println("服务已启动");
    }

    public void service() {

    }

    public static void main(String[] args) throws IOException {
        new MultiThreadServer().service();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                executorService.execute(new SthomeSocketThread(socket));

            } catch (Exception e) {
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

