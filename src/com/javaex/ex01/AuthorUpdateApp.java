package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorUpdateApp {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdbs", "webdbs");
		    // 3. SQL문 준비 / 바인딩 / 실행
		    String query = "";
		    query += " update author ";
		    query += " set author_desc =? ";  //1번
		    query += " where author_name =? "; //2번
		    
		    System.out.println(query); // 확인 
		    
		    
		    //바인딩
		    
		    pstmt = conn.prepareStatement(query);
		    pstmt.setString(1,"신대방의 정의");         //?(물음표) 중 1번째
			pstmt.setString(2,"주형준");       //?(물음표) 중 2번째 -->순서중요
		    
			
			int count = pstmt.executeUpdate();
		    
		    // 4.결과처리
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		                     
		        if (pstmt != null) {
		            pstmt.close();
		        }
		        if (conn != null) {
		            conn.close();
		        }
		    } catch (SQLException e) {
		        System.out.println("error:" + e);
		    }

		}


	}

}
