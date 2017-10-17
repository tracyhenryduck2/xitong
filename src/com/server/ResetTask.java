package com.server;
import java.util.Date;


public class ResetTask implements Task{
	public void start() {
		boolean flag=true;
		while(flag){
			try{
			}catch(Exception e){
				e.printStackTrace();
				try {
					System.out.println("true");
					Thread.sleep(500);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
