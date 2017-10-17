package com.siter.appaction;

import java.util.HashMap;
import java.util.Map;

import com.common.BaseActionSupport;
import com.common.SendMail;
import com.memberManage.bean.MemberBean;
import com.bean.*;

public class SiterAction extends BaseActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private SiterAppDao siterAppdao = new SiterAppDao();
	
	
	/**
	 * 发送邮箱给客户
	 * http://SERVER[:PORT]/PROJECTNAME/app/SiterAction!emailtoMember.action
	 * @return
	 */

	public void emailtoMember(){
//		String hekrid=request.getParameter("hekrid");
//		MemberBean m = siterAppdao.getMemberInfoByHekrid(hekrid);
//		if(m!=null){
			   boolean flag = SendMail.sendAndCc(StaticBean.STMP_SERVER,StaticBean.STMP_FROM, "546342804@qq.com", "", "您收到一条报警", "您收到一条报警", StaticBean.STMP_FROM, StaticBean.MM,null); 
			     
			    Map<String,Object> map=new HashMap<String,Object>();
		        if(flag)	map.put("result", 1);
		        else     map.put("result", 2);
				outPrintJSONObject(map);
//		}else{
//		        Map<String,Object> map=new HashMap<String,Object>();
//		        map.put("result", 3);
//				outPrintJSONObject(map);
//		}

	}
}
