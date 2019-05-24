package com.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.device.dao.InstructionsDAO;

public class DownLoadAction extends BaseActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uploadPath1;
	 private InstructionsDAO dao = new InstructionsDAO();
	public void downLoad(){
    	String fileRealName=request.getParameter("fileName");
    	String type = request.getParameter("type");
    	Map<String,Object> map = dao.getInstructionFile(fileRealName);
    	String filer = (String) map.get("file_url");
    	String filer_en = (String) map.get("file_url_en"); 
    	try {
    		
    	response.setContentType("application/octet-stream; charset=utf-8");// 一下两行关键的设置
		response.setCharacterEncoding("UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename="+ java.net.URLEncoder.encode(("zh".equals(type)?filer:filer_en), "UTF-8"));
			BufferedOutputStream buff = new BufferedOutputStream(response.getOutputStream());
			FileInputStream fis = new FileInputStream(getUploadPath1()+File.separator+ ("zh".equals(type)?filer:filer_en));
			byte[] buffers = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffers)) != -1) {
				buff.write(buffers, 0, len);
			}
			buff.flush();
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	private String getUploadPath1(){
    	if(uploadPath1==null){
    		File file=new File(request.getSession().getServletContext().getRealPath(""));
    		uploadPath1=file.getParentFile().getAbsolutePath()+"/instruction/";
    	}
    	return uploadPath1;
    }
}
