package com.siter.appaction;

import com.common.BaseDAO;
import com.memberManage.bean.ClientBean;

public class SiterAppDao extends BaseDAO{
	
	
	/**
	 * 获取登录用户
	 * 
	 * @return
	 */
	public ClientBean selectClientBean(String username, String password) {
		Object[] params = { username, password };
		return j.queryForBean(
				ClientBean.class,
						"SELECT * from client WHERE username=? and password=? ",
						params);
	}
     
}
