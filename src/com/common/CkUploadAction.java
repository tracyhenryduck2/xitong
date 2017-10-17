package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.common.*;
import com.avatar.gdk.util.DateUtils;

public class CkUploadAction  extends BaseActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File file;// 这里的"fileName"一定要与表单中的文件域名相同
	private String uploadContentType;// 格式同上"fileName"+ContentType
	private String filename;// 格式同上"fileName"+FileName
	
	private String returnPath = "";
	private String realPath;
	
	public void upload() {
		realPath = request.getSession().getServletContext().getRealPath("/");
		try {
			File file=new File(request.getSession().getServletContext().getRealPath(""));
			String savePath = file.getParentFile().getAbsolutePath()+"/weixin_images/uploadImages/";
			File f = new File(savePath);
			if(!f.exists()) {
				f.mkdirs();
			} 
        	String suffixName = filename.substring(filename.lastIndexOf(".")+1);
        	String name = DateUtils.getCurrentTime("yyyyMMddHHmmss")+"."+suffixName;
            FileOutputStream fos = new FileOutputStream(savePath+File.separator+name);  
            //System.out.println(savePath+File.separator+name);
            //returnPath  = "uploadImages/"+name;
            //System.out.println(returnPath);
            returnPath="weixin_images/uploadImages/"+name;
            FileInputStream fis=new FileInputStream(file);  
            byte []buffers=new byte[1024];  
            int len=0;  
            while((len=fis.read(buffers))!=-1){  
                fos.write(buffers,0,len);  
            }  
            fos.close();
            fis.close();
            String callback = request.getParameter("CKEditorFuncNum"); 
            out = response.getWriter();
            out.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("+callback+",'../../"+returnPath+"')</script>");   
        	//out.println("<script type=\"text/javascript\">");  
            //out.println("window.parent.CKEDITOR.tools.callFunction("  + callback + ",'" + path+"/"+returnPath+"',''" + ");");
        	//out.println("window.parent.CKEDITOR.tools.callFunction("  + callback + ",'" + "../../"+returnPath+"',''" + ");");
           // out.println("</script>");  
            System.out.println("服务器地址"+realPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getFileName() {
		return filename;
	}

	public void setFileName(String filename) {
		this.filename = filename;
	}
}
