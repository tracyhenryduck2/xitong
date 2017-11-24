package com.server.socketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.bean.ActionType;
import com.bean.ErrCode;
import com.common.MD5;
import com.common.Transaction;
import com.device.bean.DeviceBean;
import com.device.dao.DeviceDAO;
import com.message.AppLoginMessageBean;
import com.message.AppLoginParamBean;
import com.message.DeviceLoginMessageBean;
import com.message.DeviceLoginParamBean;
import com.message.MessageBean;
import com.siter.appaction.SiterAppDao;
import com.websocket.WebSocketAppMessageInboundPool;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class SthomeSocketThread implements Runnable {
    
    public static final String CHARCODE = "gbk";
    private Socket socket;
    private String deviceToken;
    private DeviceDAO devicedao;
    private SiterAppDao siterAppdao;
    private OutputStream os = null; 
    
    
    public SthomeSocketThread(Socket socket) {
        this.socket = socket;
        devicedao = new DeviceDAO();
		siterAppdao = new SiterAppDao();
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
            while (!socket.isClosed() && (msg = br.readLine()) != null) {
                System.out.println("接收到:" + msg);
                resolvedata(msg);
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

	@SuppressWarnings("rawtypes")
	private void resolvedata(String data){
		  
		try {
			JSONObject j = JSONObject.fromObject(data);
	         if(j.has("params")){
	        	 j.remove("params");
	         }
	        MessageBean workflow = (MessageBean) JSONObject.toBean(j,  
	        		MessageBean.class);
        if(ActionType.DEVICE_LOGIN.getName().equals(workflow.getAction()) ){
    		JSONObject j2 = JSONObject.fromObject(data);
			//设备登录
			Map<String, Class> classMap = new HashMap<String, Class>();  
	        classMap.put("params", DeviceLoginParamBean.class);  
	        //将json转换成workflowBean  
	        DeviceLoginMessageBean workflow2 = (DeviceLoginMessageBean) JSONObject.toBean(j2,  
	        		DeviceLoginMessageBean.class, classMap);
        	
	        String devTid = workflow2.getParams().getDevTid();
	        String prodkey = workflow2.getParams().getProdKey();
	        
	        if(StringUtils.isNotEmpty(devTid)&&StringUtils.isNotEmpty(prodkey)){
	        	Long pid = siterAppdao.getpidFromKey(prodkey);
                 if(pid!=0){
     	        	String token = workflow2.getParams().getToken();
			
    				if(StringUtils.isNotEmpty(token)){

    					final  Long did = siterAppdao.getDidFromToken(token);
    					   if(did!=0){
    					         setDeviceToken(token);
    						     siterAppdao.addDevicelogin(did, 1l, new Date().getTime()/1000l);
    							 JSONObject ds = new JSONObject();
    							 ds.put("msgId", workflow.getMsgId());
    							 ds.put("action",ActionType.DEVICE_LOGINRESP.getName() );
    							 ds.put("code", ErrCode.SUCCESS.getCode());
    							 ds.put("desc", ErrCode.SUCCESS.getName());
    							 sendMessage(ds.toString());
    						     SthomeSocketPool.addSocketDevice(this);
    					   }else{
    							 JSONObject ds = new JSONObject();
    							 ds.put("msgId", workflow.getMsgId());
    							 ds.put("action", ActionType.DEVICE_LOGINRESP.getName());
    							 ds.put("code", ErrCode.DEVICE_TOKEN_ERROR.getCode());
    							 ds.put("desc", ErrCode.DEVICE_TOKEN_ERROR.getName());
    							 sendMessage(ds.toString());
    							 socket.close();
    					   }
    					   

    				}else if("".equals(token)){
    					 Long time = new Date().getTime();
    					 String new_token = MD5.get32MD5(devTid+prodkey+String.valueOf(time));
    					 String new_bindkey =  MD5.get32MD5(prodkey+devTid+String.valueOf(time));
    					 String new_ctrlkey =  MD5.get32MD5(devTid+String.valueOf(time)+prodkey);
    					 String ip = socket.getInetAddress().toString();
    					final DeviceBean device = new DeviceBean();
    					 device.setBindKey(new_bindkey);
    					 device.setCtime(time/1000);
    					 device.setCtrlKey(new_ctrlkey);
    					 device.setDevTid(devTid);
    					 device.setOnline(1l);
    					 device.setPid(pid);
    					 device.setToken(new_token);
    					 device.setIp(ip);

		    			Long id = devicedao.save(device);
						siterAppdao.addDevicelogin(id, 1l, new Date().getTime()/1000l);
						 JSONObject ds = new JSONObject();
						 ds.put("msgId", workflow.getMsgId());
						 ds.put("action",ActionType.DEVICE_LOGINRESP.getName() );
						 ds.put("code", ErrCode.SUCCESS.getCode());
						 ds.put("desc", ErrCode.SUCCESS.getName());
						 sendMessage(ds.toString());
						 setDeviceToken(new_token);
					     SthomeSocketPool.addSocketDevice(this);
    					 
    				}else{
    					 JSONObject ds = new JSONObject();
    					 ds.put("msgId", workflow.getMsgId());
    					 ds.put("action", ActionType.DEVICE_LOGINRESP.getName());
    					 ds.put("code", ErrCode.PROPERTY_NOT_EXIST_ERROR.getCode());
    					 ds.put("desc", ErrCode.PROPERTY_NOT_EXIST_ERROR.getName());
    					 sendMessage(ds.toString());
    					 socket.close();
    				}
                 }else{
                	 
					 JSONObject ds = new JSONObject();
					 ds.put("msgId", workflow.getMsgId());
					 ds.put("action", ActionType.DEVICE_LOGINRESP.getName());
					 ds.put("code", ErrCode.PRODUCT_KEY_NOT_AVAILABLE_ERROR.getCode());
					 ds.put("desc", ErrCode.PRODUCT_KEY_NOT_AVAILABLE_ERROR.getName());
					 sendMessage(ds.toString());
					 socket.close();
                 }
	        	
	        	

	        }else{
				 JSONObject ds = new JSONObject();
				 ds.put("msgId", workflow.getMsgId());
				 ds.put("action", ActionType.DEVICE_LOGINRESP.getName());
				 ds.put("code", ErrCode.PROPERTY_NOT_EXIST_ERROR.getCode());
				 ds.put("desc", ErrCode.PROPERTY_NOT_EXIST_ERROR.getName());
				 sendMessage(ds.toString());
				 socket.close();
	        }
	        
	        

        	
        }else if(ActionType.DEVICE_SEND.getName().equals(workflow.getAction())){
        	//device tcp主动发送消息
        	
        	
        }else if(ActionType.APP_SENDRESP.getName().equals(workflow.getAction())){
        	//device tcp发送应答消息
        	
        	
        }else if(ActionType.HEARTBEAT.getName().equals(workflow.getAction())){
			 JSONObject ds = new JSONObject();
			 ds.put("msgId", workflow.getMsgId());
			 ds.put("action", ActionType.HEARTBEATRESP.getName());
			 ds.put("code", ErrCode.SUCCESS.getCode());
			 ds.put("desc", ErrCode.SUCCESS.getName());
			 sendMessage(ds.toString());
        }else{
        	System.out.println(ErrCode.JSON_PARSE_ERROR.getName());
        }
        
		}catch(JSONException e){
		   e.printStackTrace();
			System.out.println(ErrCode.JSON_FORMAT_ERROR.getName()); 
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
	}

    
    
}
