package com.pong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pong.db.util.Conn;
import com.pong.help.util.BeanHelper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	HelperTest();
    }
    
    public static void DBTest(){
    	 Connection conn = null;
         PreparedStatement ps = null;
         ResultSet rs = null;
          try {
          	 conn = Conn.getConn();
               String sql = "select * from bonus";
  			 ps = conn.prepareStatement(sql);
  			 rs = ps.executeQuery();
  			 while(rs.next()){
  				 System.out.println(rs.getString(1));
  			 }
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}finally{
  			try {
  				rs.close();
  				ps.close();
  				conn.close();
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		}
    }
    
    public static void HelperTest(){
    	BeanHelper bh = new BeanHelper("GOLDPRICE");
    	bh.getTableMetaData();
    }
}
