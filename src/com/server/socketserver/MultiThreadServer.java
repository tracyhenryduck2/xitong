package com.server.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    private int port = 6666;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 10;
    
    public MultiThreadServer() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_SIZE);
        System.out.println("服务已启动");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new MultiThreadServer().service();
    }

}

class Handler implements Runnable {
    
    public static final String CHARCODE = "gbk";
    
    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }


    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn,"gbk"));
    }

    public void run() {
        BufferedReader br = null;
		OutputStream os = null; 
        try {
            br = getReader(socket);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println("接收到:" + msg);

				os = socket.getOutputStream(); 
				os.write(("RES, OK,<年班,小明>, ,#" + "\n").getBytes("gbk")); 
				os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(os!=null){
            	try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
        }
    }

    
}