/**
 * 
 */
package com.pong.help.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.pong.db.util.Conn;

/**
 * @Description class {{@link #BeanHelper()} help to generate entity bean by table name you give 
 * @author canpong wu
 * @date 2017年5月26日 下午8:59:46 
 */
public class BeanHelper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8324428508918960163L;
	private String tableName;
	private String beanName;
	private String packagePath;
	private String author;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:SSS");
	private static String TAB = "/t";
	private static String NEXT_LINE = "/r/n";
	private Map<String,String> map = new HashMap<String,String>();
	public BeanHelper(String tableName,String beanName,String packagePath,String author){
		this.tableName = tableName;
		this.beanName = beanName;
		this.packagePath = packagePath;
		this.author = author;
	}
	
	public void go(){
		
	}
	
	public void generateCode(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("package "+packagePath+NEXT_LINE+NEXT_LINE);
		stringBuilder.append("import java.io.Serializable;"+NEXT_LINE+NEXT_LINE);
		stringBuilder.append("/**"+NEXT_LINE);
		stringBuilder.append(" * @Description"+NEXT_LINE);
		stringBuilder.append(" * @author "+author+NEXT_LINE);
		stringBuilder.append(" * @@date "+sdf.format(new Date())+NEXT_LINE);
		stringBuilder.append(" */");
		stringBuilder.append("public class "+beanName+" implements Serializable{"+NEXT_LINE+NEXT_LINE);
		stringBuilder.append(TAB+"private static final long serialVersionUID = - 1L"+NEXT_LINE);
		getTableMetaData();
		
	}
	
	public void getTableMetaData(){
		String sql = "select * from "+tableName+" where 1 = 1 ";
		Connection conn = Conn.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSetMetaData  rsmd = ps.getMetaData();
			for(int i = 0;i<rsmd.getColumnCount();i++){
				map.put(toLowCase(rsmd.getColumnName(i+1)), OracleType2JavaType(rsmd.getColumnTypeName(i+1)));
//				System.out.println(rsmd.getColumnName(i+1)+"  "+rsmd.getColumnTypeName(i+1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String toLowCase(String columnName){
		char[] chars = columnName.toCharArray();
		for(int i = 0;i<chars.length;i++){
			chars[i] = Character.toLowerCase(chars[i]);
		}
		return new String(chars);
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
