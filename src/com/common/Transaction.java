package com.common;

import java.lang.reflect.Method;

import com.avatar.db.jdbc.JdbcHandler;
import com.avatar.db.transaction.TransactionController;
import com.avatar.db.transaction.TransactionException;
/**
 * 单数据源事务
 * @author Administrator
 *
 */
public abstract class Transaction {
	
	public static boolean batchSql(JdbcHandler j,String[] sqls,Object[][] parms){
		boolean result=false;
		try {
			TransactionController.beginTransaction();
			for(int i=0;i<sqls.length;i++){
				if(parms[i]!=null){
					j.execute(sqls[i],parms[i]);
				}else{
					j.execute(sqls[i]);
				}
			}
			TransactionController.commitTransaction();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				TransactionController.closeTransaction();
			} catch (TransactionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static boolean batchSql(JdbcHandler j,String... sql){
		boolean result=false;
		try {
			TransactionController.beginTransaction();
			for(String str:sql){
				j.execute(str);
			}
			TransactionController.commitTransaction();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				TransactionController.closeTransaction();
			} catch (TransactionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 快捷调用,可接收多个方法,方法不带参数
	 * @param obj
	 * @param method
	 * @return
	 */
	public static boolean batchMethod(Object obj,String... method) {
		boolean result=false;
		try {
			TransactionController.beginTransaction();
			Class<?> c = obj.getClass(); 
			for(String str:method){
				Method m = c.getDeclaredMethod(str);
				m.invoke(obj);
			}
			TransactionController.commitTransaction();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				TransactionController.closeTransaction();
			} catch (TransactionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 多方法多参数的复杂情况，此方法使用，需要重载实例化run，再调用
	 * @return
	 */
	public boolean start(){
		boolean result=false;
		try {
			TransactionController.beginTransaction();
			run();
			TransactionController.commitTransaction();
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				TransactionController.closeTransaction();
			} catch (TransactionException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public abstract void run() throws Exception;
}
