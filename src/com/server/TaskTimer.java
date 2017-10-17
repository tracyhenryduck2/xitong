package com.server;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @author Administrator
 *
 */
public class TaskTimer extends TimerTask {
	private String actTime;
	private Task pt;
	private Timer timer;
	public void setTime(String time){
		if(actTime==null||!actTime.equals(time)){
			this.actTime=time;
			if(timer!=null){
				timer.cancel();
				try{
				 Field field = TimerTask.class.getDeclaredField("state");
				 field.setAccessible(true);
				 field.set(this, 0);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			timer=new Timer();
			timer.scheduleAtFixedRate(this,getProofDate(),24*60*60*1000);
		}
	}
	
	private Date getProofDate(){
		String[] list=actTime.split(":");
		Date date=new Date();
		Calendar c=Calendar.getInstance();   
        c.setTime(date);
		if(list.length>=2){
			int hour=Integer.parseInt(list[0]);
			int minute=Integer.parseInt(list[1]);
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, minute);
			if(c.getTimeInMillis()<date.getTime()){
                 c.add(Calendar.DAY_OF_MONTH,1);
			}
		}
		System.out.println(c.getTime()+","+date+","+c.after(date));
		return c.getTime();
	}
	public TaskTimer(Task th,String time){
		this.setTime(time);
		this.pt=th;
	}
	public void run(){
		System.out.println("do task,time="+new Date());
		try{
			pt.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
