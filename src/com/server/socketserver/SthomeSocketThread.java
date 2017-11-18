package com.server.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class SthomeSocketThread implements Runnable {
    
    public static final String CHARCODE = "gbk";
    
    private Socket socket;
    private String deviceToken;
	OutputStream os = null; 
    public SthomeSocketThread(Socket socket) {
        this.socket = socket;
    }


    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn,"gbk"));
    }

    public void run() {
        BufferedReader br = null;
        try {
            br = getReader(socket);
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println("接收到:" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null){
                    socket.close();
                    System.out.println("socket.close()");
                }

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


	public String getDeviceToken() {
		return deviceToken;
	}


	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}


	public void sendMessage(String Message) {
		try {
			os = socket.getOutputStream();
			os.write((Message + "\n").getBytes("gbk")); 
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				os.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} 

	}



    
    
}
