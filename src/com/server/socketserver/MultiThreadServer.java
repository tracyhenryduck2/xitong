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

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public void run() {
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            br = getReader(socket);

            out = getWriter(socket);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println("s1:" + msg);
                msg = Util.decode(msg,CHARCODE);
                System.out.println("s2:" + msg);

                String res = "wwwwwwwwwwww哈哈w1241243123";
                res = Util.encode(res.getBytes(CHARCODE));
                System.out.println("s1:" + res);
                System.out.println("s2:" + Util.decode(res,CHARCODE));

                out.println(res);
                out.flush();
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
            if (out != null) {
                out.close();
            }
        }
    }

    
}