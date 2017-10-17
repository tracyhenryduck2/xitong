package com.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.avatar.gdk.util.DateUtils;


import com.avatar.gdk.util.StringUtils;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExportExcel {
	public  String TYPE_NORMAL = "1";//普通数据
	public  String TYPE_DATE = "2";//日期
	public  String TYPE_IP = "3";//IP
	public  String TYPE_DATE_1 = "4"; //yyyy-MM-dd;
	private int columns = 0;//列数
	private String[] headArray = null;
	private String[] fieldArray = null;
	private String[] dataTypeArray = null;
	private String[] widthArray = null;
	private WritableWorkbook book = null;
	private WritableSheet worksheet = null;
	private int sheetNo =0;
	WritableCellFormat wcfdc = new WritableCellFormat();
	private  int maxRows = 50000;
	//当前行
	private  int currentRow = 0;

	/**
	 * 构造函数
	 * @param response
	 * @param fileName 导出文件名
	 */
	public ExportExcel(String fileName, HttpServletResponse	response){
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition","inline;filename="+fileName+DateUtils.getCurrentTime()+".xls");//定义文件名
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream(); //new FileOutputStream("c:\\1.xls");
			// 打开文件
			book = Workbook.createWorkbook(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建下一个
	 * @return
	 */
	public  void createNextSheet(String name){
		if(null == name || "".equals(name)){
			name = "sheet"+sheetNo;
		}
		worksheet = book.createSheet(name,sheetNo);//创建第一个工作表，name:工作表名称
		sheetNo++;	
	}
	
	/**
	 * 设置参数
	 * 格式：
	 * paramArray = {
				{"head1","fied1","type","width1"},//excel字段名，数据库字段名，类型（1=正常，2=日期，3=IP）
				{"head2","fied2","type","width2"},
				{"head3","fied3","type","width3"},
				{"head4","fied4","type","width4"},
				{"head5","fied5","type","width5"}
		}
	 * @param paramArray
	 */
	public void setParam(String[][] paramArray){
		columns = paramArray.length;
		headArray = new String[columns];
		fieldArray = new String[columns];
		dataTypeArray = new String[columns];
		widthArray =  new String[columns];
		int i=0;
		for(String[] arrs : paramArray){
			headArray[i] = arrs[0];
			fieldArray[i]= arrs[1];
			if(arrs.length>=3){
				dataTypeArray[i] = arrs[2];
			}		
			if(arrs.length>=4){
				widthArray[i] = arrs[3];
				//System.out.println(widthArray[i]);
			}
			i++;
		}	
	}

	/**
	 * 创建头部
	 */
	public void createHead(){
		Label label = null;
		 
        try {
			wcfdc.setBorder(Border.ALL, BorderLineStyle.THIN);
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(getHeadArray()!=null){
			for(int i=0;i<getHeadArray().length;i++){ 
				  label=new Label(i,0,getHeadArray()[i],wcfdc);//参数依次代表列数、行数、内容 
				 try{
					 if(widthArray!=null){
						 //System.out.println(widthArray[i]);
						 worksheet.setColumnView(i,Integer.parseInt(widthArray[i]));
					 }
					 worksheet.addCell(label);
					 //sheet.setColumnView(w, arrColumnWidth[w]);
				 }catch(Exception e){
					 System.out.println("error");
				 }
	         }
		}
	}
	
	/**
	 * 
	 * @param dateTime
	 * @param dateFormat
	 * @return
	 */
	private static String convertTimeNumToDate( String dateTimeString , String dateFormat ) {
			//System.out.println("dateTimeString:"+dateTimeString);
	        String timeStr = "";
	        int dateTime = 0;
	        try{
	        	 dateTime = Integer.parseInt(dateTimeString);
	        }catch(Exception e){
	        	
	        }
	        
	        if ( dateTime == 0 ) {
	            timeStr = "";
	        } else {
	            if ( dateFormat == null ) {
	                dateFormat = "yyyy-MM-dd HH:mm:ss";
	            }
	            Calendar time = Calendar.getInstance();
	            time.setTimeInMillis( dateTime * 1000 );
	            SimpleDateFormat sdf = new SimpleDateFormat( dateFormat );
	            timeStr = sdf.format( time.getTime() );
	        }

	        return timeStr;
	    }
	
	 private static String convertIPNumToDot( String ipValue ) {
		 //System.out.println("ipValue:"+ipValue);
		    try{
		        String[] sIp = new String[4];
		        Long ipLong = Long.parseLong( ipValue );
		        for ( int i = 0 ; i < 4 ; i++ ) {
		            long temp = ipLong >> ( 8 * i );
		            sIp[i] = Long.toString( temp & 0xff );
		        }
		        return sIp[3] + "." + sIp[2] + "." + sIp[1] + "." + sIp[0];
		    }catch(Exception e){
		    	return "";
		    }
	    }
	 
	 /**
	  * 添加数据
	  * @param maps
	  * @param mapInterface
	  */
	 public void addData(List<Map<String,Object>> maps, MapInterface mapInterface){
		 if(maps==null || maps.size()==0) {
				//创建标签
				createNextSheet("");
				//写入头部
				createHead();
				return;
			}
			Label label = null;
	        try {
				wcfdc.setBorder(Border.ALL, BorderLineStyle.THIN);
			} catch (WriteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(Map<String,Object> map :maps){
				if(mapInterface!=null) {
					mapInterface.deal(map);
				}
				//System.out.println("currentRow:"+currentRow);
				if(currentRow==0){//创建头
					//创建标签
					createNextSheet("");
					//写入头部
					createHead();			
				}
				currentRow++;
				
				for(int i=0; i<headArray.length; i++){
					//System.out.println(dataTypeArray[i]);
					Object obj = map.get(fieldArray[i]);
					String str = "";
					if(obj!=null) {
						str = obj+"";
					}
					if(dataTypeArray[i].equals(TYPE_NORMAL) || StringUtils.isEmptyString(str)){
						label = new Label(i,currentRow,str,wcfdc);
					}else if(dataTypeArray[i].equals(TYPE_DATE)){
						label = new Label(i,currentRow,convertTimeNumToDate(str,null),wcfdc);
					} else if(dataTypeArray[i].equals(TYPE_DATE_1)) {
						label = new Label(i,currentRow,DateUtils.convertTimeToString((DataUtils.getLong(str)),"yyyy-MM-dd"),wcfdc);
					} else if(dataTypeArray[i].equals(TYPE_IP)){
						label = new Label(i,currentRow,convertIPNumToDot(str),wcfdc);
					}
					
					try {
						
						worksheet.addCell(label);
					} catch (RowsExceededException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}//写入单元格
				}				
				if(currentRow==maxRows){//创建下一个sheet
					currentRow = 0;			
				}		
			}
	 }
	 
	/**
	 * 添加数据
	 * @param maps
	 */
	public void addData(List<Map<String,Object>> maps){
		addData(maps, null);
	}
	/**
	 * 导出excel
	 */
	public void writeExcel(){
		try {
			if(book!=null){
				book.write();
			}
			if(book!=null){
				book.close();
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		 String sIp[] = new String[4];
        System.out.println(convertIPNumToDot("167778367"));
        
		// TODO Auto-generated method stub
		/*
		ExportExcel excel = new ExportExcel("文件名",response);
		String[][] arrs = {
				{"ID","ID","1","10"},//excel列名，数据库字段名，类型，宽度
				{"内容","OPERATION","1","50"},
				{"时间","OPERATIONTIME","2","20"},
				{"IP","IP","3","20"}
		};
		excel.setParam(arrs);
		List<Map<String,String>> maps = LogDAO.getAll();
		excel.addData(maps);
		excel.writeExcel();
		*/
	}

	public String[] getHeadArray() {
		return headArray;
	}

	public void setHeadArray(String[] headArray) {
		this.headArray = headArray;
	}

	/**
	 * @return the widthArray
	 */
	public String[] getWidthArray() {
		return widthArray;
	}

	/**
	 * @param widthArray the widthArray to set
	 */
	public void setWidthArray(String[] widthArray) {
		this.widthArray = widthArray;
	}

}
