package com.siter.appaction;

import com.common.BaseDAO;
import com.memberManage.bean.MemberBean;

public class SiterAppDao extends BaseDAO{
	
	
	public MemberBean getMemberInfoByHekrid(String hekrid){
	
		Object[] params={hekrid};
		
		
		return j.queryForBean(MemberBean.class, "select * from member where hekrid = ?",params);
	}
     
}
