/**
 * 
 */
package com.pong.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Description
 * @author canpong wu
 * @date 2017年5月26日 下午8:53:44 
 */
public class DBUtil {

	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	public static void insert(String tableName,Object[] values){
		String sql = "insert into ";
		
	}
}
