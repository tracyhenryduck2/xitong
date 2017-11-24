package com.siter.appaction;

import com.common.BaseDAO;
import com.memberManage.bean.ClientBean;
import com.memberManage.bean.ClientTokenBean;

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
     
	
	/**
	 * 修改token
	 * 
	 * @return
	 */
	public boolean refreshToken(String oldrefreshtoken,ClientTokenBean clienttokenbean)
	{
		
		String sql = " update client_token set access_token=?,refresh_token=?,time=? where refresh_token=?";
		Object[] params = {clienttokenbean.getAccessToken(),clienttokenbean.getRefreshToken(),clienttokenbean.getTime(),oldrefreshtoken };		
		return j.execute(sql, params);
		
	}
	
	
	/**
	 * 判断refresh_token是否存在
	 * 
	 * @return
	 */
	public boolean isRefreshTokenExist(String refreshtoken)

	{
		String sql = "select count(*) from client_token where refresh_token = ?";
		Object[] params = {refreshtoken};		
		return j.queryForInteger(sql, params)>0?true:false;
		
	}
	
	/**
	 * 判断access_token是否存在
	 * 
	 * @return
	 */
	public boolean isAccessTokenExist(String accesstoken)

	{
		String sql = "select count(*) from client_token where access_token = ?";
		Object[] params = {accesstoken};		
		return j.queryForInteger(sql, params)>0?true:false;
		
	}
	
	/**
	 * 获取ClientTokenBean
	 * 
	 * @return
	 */
	public ClientTokenBean getClientBeanByRefreshToken(String refreshtoken)

	{
		String sql = "select * from client_token where refresh_token = ?";
		Object[] params = {refreshtoken};		
		return j.queryForBean(ClientTokenBean.class, sql, params);
		
	}
	
	/**
	 * 获取ClientBean
	 * 
	 * @return
	 */
	public ClientBean getClientBeanByAccessToken(String accesstoken)

	{
		String sql = "select a.* from client a left join client_token b on a.id=b.client_id where b.access_token = ?";
		Object[] params = {accesstoken};		
		return j.queryForBean(ClientBean.class, sql, params);
		
	}
	
	
	/**
	 * 获取clientid
	 * 
	 * @return
	 */
	public Long getTokenTime(String accessToken)

	{
		String sql = "select time from client_token where access_token = ?";
		Object[] params = {accessToken};		
		return j.queryForLong(sql, params);
		
	}

	/**
	 * 增加用户登录记录
	 * @param token
	 * @param ip
	 * @param time
	 * @return
	 */
	public boolean addLogin(String token,String ip,Long time){
		
		
		String sql = "insert client_login_log (access_token,ip,time) values (?,?,?)";
		Object[] params = {token,ip,time};		
		
		return j.execute(sql, params);
	}
	
	/**
	 * 增加设备登录登出记录
	 * @param did
	 * @param type
	 * @param time
	 * @return
	 */
	public boolean addDevicelogin(Long did,Long type,Long time){
		
		String sql  = "insert device_login_log (did,action_type,atime) values (?,?,?)";
		
		Object[] params = {did,type,time};		
		
		return j.execute(sql, params);
	}
	
	public Long getDidFromToken(String token){
		String sql = "select id from device where token = ?";
		Object[] params = {token};
		
		return j.queryForLong(sql, params);
		
	}
	
	public Long getpidFromKey(String key){
		String sql = "select id from product where prod_key = ?";
		Object[] params = {key};
		
		return j.queryForLong(sql, params);
		
	}
}
