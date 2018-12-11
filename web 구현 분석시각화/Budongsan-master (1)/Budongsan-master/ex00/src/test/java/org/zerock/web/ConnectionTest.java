package org.zerock.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class ConnectionTest {

	// 오라클에 접속할 때 필요한 정보들
	// 드라이버는 오라클사에서 제공한다 -> 찾아서 인식 시킨다:ojdbc6.jar
	  String driver = "oracle.jdbc.driver.OracleDriver"; //오라클사에서 제공
	  // server 정보
	  String server = "jdbc:oracle:thin:@192.168.137.201:1521:orcl";
	  String uid="java00"; 
	  String upw = "java00";
	  
	  // test하는 메서드 만들기
	  @Test
	  public void testCon() throws Exception{
		  Class.forName(driver);
		  try(Connection con 
				  = DriverManager.getConnection(server, uid, upw)){
			  System.out.println(con);
		  }catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
		}
	  }
}
