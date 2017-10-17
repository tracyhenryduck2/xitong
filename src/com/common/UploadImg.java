package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class UploadImg extends BaseActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String dir="upload";
	File file;
	String fileFileName;
	public UploadImg(File file,String fileFileName,String dir,HttpServletRequest request,HttpServletResponse response){
		this.dir=dir;
		this.file=file;
		this.fileFileName=fileFileName;
		this.request=request;
		this.response=response;
	}
	public void importFile() throws Exception {
		response.setContentType("text/html;charset=utf-8");
		out = response.getWriter();
		InputStream in = new FileInputStream(file);
		String dateStr = new Date().getTime()+"";
		String fileName=dateStr+fileFileName.substring(fileFileName.lastIndexOf("."));
    	String minFileName="S_"+dateStr+fileFileName.substring(fileFileName.lastIndexOf("."));
		FileOutputStream fs = new FileOutputStream(getUploadPath() + fileName);
		int byteread=0;
    	int bytesum=0;
    	byte[] buffer=new byte[1024];
    	while((byteread=in.read(buffer))!=-1){
    		bytesum+=byteread;
    		fs.write(buffer, 0, byteread);
    	}
    	fs.close();
    	in.close();
    	String uploadPath = getUploadPath();
    	Picture.resizePNG(uploadPath+fileName, uploadPath+minFileName, 400, 400,true);
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("fileName", minFileName);
    	JSONObject jsonObject = JSONObject.fromObject(result); 
    	System.out.println(jsonObject.toString());
    	out.print(jsonObject);
    	out.close();
	}

	private String getUploadPath() {
		File file=new File(request.getSession().getServletContext().getRealPath(""));
    	File tmpFile = new File(file.getParentFile().getAbsolutePath()+"/"+dir+"/");
    	if(!tmpFile.exists()) {
    		tmpFile.mkdirs();
    	}
		return file.getParentFile().getAbsolutePath()+"/"+dir+"/";
	}

	public void removeFile() throws IOException {
		out=response.getWriter();
    	String fileName=request.getParameter("fileName");
    	File file=new File(getUploadPath()+fileName);
    	boolean flag=true;
    	if(file.isFile()&&file.exists()){
    		flag=file.delete();
    	}
    	out.print(flag);
    	out.close();
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
