/**
 * 
 */
package com.pong.help.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.pong.db.util.Conn;

/**
 * @Description class {{@link #BeanHelper()} help to generate entity bean by table name you give 
 * @author canpong wu
 * @date 2017年5月26日 下午8:59:46 
 */
public class BeanHelper {
	
	private String tableName;
	
	public BeanHelper(String tableName){
		this.tableName = tableName;
	}
	
	public void go(){
		
	}
	
	public void getTableMetaData(){
		String sql = "select * from "+tableName+" where 1 = 1 ";
		Connection conn = Conn.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSetMetaData  rsmd = ps.getMetaData();
			for(int i = 0;i<rsmd.getColumnCount();i++){
				System.out.println(rsmd.getColumnName(i+1)+"  "+rsmd.getColumnTypeName(i+1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private String OracleType2JavaType(String columnType){
		String javaType = null;
		if("VARCHAR2".equalsIgnoreCase(columnType)){
			javaType = "String";
		}else if("DATE".equalsIgnoreCase(columnType)){
			javaType = "String";
		}else if("NUMBER".equalsIgnoreCase(columnType)){
			javaType = "Double";
		}
		return javaType;
	}
}
