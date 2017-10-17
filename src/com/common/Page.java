package com.common;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.avatar.gdk.util.PagerUtils;
import com.avatar.gdk.util.StringUtils;

public class Page extends PagerUtils{
	
	private Long flag;
	
	private Map<String,Object> map;

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * totalRows为0时不进行分页计算，只对值进行处理
	 * @param request
	 * @param totalRows
	 */
	public  void execute(HttpServletRequest request){;
		if(flag==null) { //export时置为1不执行
			//当前页
			if(!StringUtils.isEmptyString(request.getParameter("pageno"))){
				int pageNo = Integer.parseInt(request.getParameter("pageno"));
				setPageNo(pageNo);			
			}//默认为1
	
			//页大小
			if(!StringUtils.isEmptyString(request.getParameter("pagesize"))){
				int pageSize = Integer.parseInt(request.getParameter("pagesize"));
				setPageSize(pageSize);	
			}////默认大小为20
		}
		
		//排序字段名称
		if(!StringUtils.isEmptyString(request.getParameter("sortname"))){
			setSortname(request.getParameter("sortname"));
		}
		
		//排序顺序
		if(!StringUtils.isEmptyString(request.getParameter("sortorder"))){
			setSortorder(request.getParameter("sortorder").toUpperCase());
		}	
		//可以进行分页
		//startIndex endIndex根据前面几个设置进行计算
	}
	
	
	/**
	 * 通过此方法获得分页所需要的参数
	 * @param request
	 * @param defaultSortname
	 * @param defaultSortorder
	 * @param sqlCount
	 */
	public void execute(HttpServletRequest request, String defaultSortname, String defaultSortorder){
		if(flag==null) { //export时置为1不执行
			//当前页
			if(!StringUtils.isEmptyString(request.getParameter("pageno"))){
				int pageNo = Integer.parseInt(request.getParameter("pageno"));
				setPageNo(pageNo);			
			}//默认为1
	
			//页大小
			if(!StringUtils.isEmptyString(request.getParameter("pagesize"))){
				if(getPageSize()<1000) {
					int pageSize = Integer.parseInt(request.getParameter("pagesize"));
					setPageSize(pageSize);	
				}
			}////默认大小为20
		}	
		
		//排序字段名称
		if(!StringUtils.isEmptyString(request.getParameter("sortname"))){
			setSortname(request.getParameter("sortname"));
		}else{
			setSortname(defaultSortname);
		}
		
		//排序顺序
		if(!StringUtils.isEmptyString(request.getParameter("sortorder"))){
			setSortorder(request.getParameter("sortorder").toUpperCase());
		}else{
			setSortorder(defaultSortorder);
		}
	}
	/**
	 * 返回字符串
	 */
	public String toString(){
		String res = "pageNo:"+this.getPageNo()+"\n";
		res += "pageSize:"+this.getPageSize()+"\n";
		res += "sortname:"+this.getSortname()+"\n";
		res += "sortorder:"+this.getSortorder()+"\n";
		res += "startIndex:"+this.getStartIndex()+"\n";
		res += "endIndex:"+this.getEndIndex()+"\n";
		res += "totalRows:"+this.getTotalRows()+"\n";
		res += "totalPages:"+this.getTotalPages()+"\n";	
		return res;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PagerUtils p = new PagerUtils();
		p.setPageNo(1);
		p.setPageSize(10);
		p.setTotalRows(500);
		System.out.println(p.getStartIndex()+"->"+p.getEndIndex());//为0->9
		
		PagerUtils p1 = new PagerUtils();
		p1.setPageNo(0);
		p1.setPageSize(10);
		p1.setTotalRows(500);
		System.out.println(p1.getStartIndex()+"->"+p1.getEndIndex());//为0->0
		
		PagerUtils p2 = new PagerUtils();
		System.out.println(p2.getPageNo()); //0
		System.out.println(p2.getPageSize());//20
		System.out.println(p2.getPageSize());//20
	}

	public Long getFlag() {
		return flag;
	}


	public void setFlag(Long flag) {
		this.flag = flag;
	}


	public void setTotalRows(int total){
		super.setTotalRows(total);
		int no=super.getPageNo();
		if(no<=0){
			super.setPageNo(1);
		}else if(no>super.getTotalPages()){
			super.setPageNo(super.getTotalPages());
		}
	}


	public Map<String,Object> getMap() {
		return map;
	}


	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
}
