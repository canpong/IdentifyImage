/**
 * 
 */
package com.pong.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.pong.dao.BaseDao;
import com.pong.db.util.Conn;

/**
 * @Description
 * @Author canpong
 * @CreateTime 2017年5月28日 下午4:42:36
 * @version
 * @param <T>
 */
public class BaseDaoImpl<T> implements BaseDao<T>{
	
	protected T Model;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl(){
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>)type.getActualTypeArguments()[0];
		try{
			Model = (T) clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void Add(String sql,Object[] values) throws Exception{
		System.out.println("========== "+sql+" ===========");
		Connection conn = getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		setParameters(ps,values);
		ps.execute();
		close(conn,ps);
	}

	public void AddBatch(String sql, List<Object[]> listValues) throws Exception {
		System.out.println("========== "+sql+" ===========");
		Connection conn = getConn();
		PreparedStatement ps = null;
		conn.setAutoCommit(false);//开启事务，批量插入出错时回滚
		/*int batchSize = 1000;//避免内存不足
		int count = 0;*/
		ps = conn.prepareStatement(sql);
		for(int i = 0,size = listValues.size();i<size;i++){
			setParameters(ps,listValues.get(i));
			ps.addBatch();
			ps.executeBatch();
			/*if(++count%batchSize == 0){//还要处理最后非1000倍数的记录 如5102条记录得处理最后102条记录
				ps.executeBatch();
			}*/	
		}
		conn.commit();
		close(conn,ps);
	}
	
	public void Delete(String sql, Object[] values) throws Exception {
		System.out.println("========== "+sql+" ===========");
		Connection conn = getConn();
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		setParameters(ps,values);
		ps.execute();
		close(conn,ps);
	
	}
	
	protected Connection getConn(){
		Connection conn = Conn.getConn();
		try{
			if(conn.isClosed()){
				conn = null;
				conn = Conn.getConn();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return conn;
	}

	public static void setParameters(PreparedStatement ps ,Object[] values){
		if(values != null && values.length > 0){
			try{
				for(int i =0,size = values.length;i<size;i++){
					ps.setObject(i+1, values[i]);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn,PreparedStatement ps){
		if(conn != null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(ps != null){
			try{
				ps.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}


}
