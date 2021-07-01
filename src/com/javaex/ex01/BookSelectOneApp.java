package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectOneApp {

	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdbs", "webdbs");
		    // 3. SQL문 준비 / 바인딩 / 실행
			
			
			 String query = "";
		        query+= " select   book_id, ";
		        query+=	" title, ";
		        query+=	" pubs, ";
		        query+=	" to_char(pub_date,'YYYY-MM-DD')pub_date, ";
		        query+=	" author_id ";
		        query+= " from book ";
		        query+= " where author_id = ? ";
		        
			
		        pstmt = conn.prepareStatement(query);
		        pstmt.setInt(1, 2);
			
		        rs = pstmt.executeQuery();
		    // 4.결과처리

		        while(rs.next()) {
		        	int bookid = rs.getInt("book_id");
		        	String booktitle = rs.getString("title");
			    	String bookpubs = rs.getString("pubs");
			    	String bookpub_date = rs.getString("pub_date");
			    	int bookauthor_id =rs.getInt("author_id");
			    	
			    	System.out.println(bookid + "," + booktitle +"," + bookpubs
			    			+","+bookpub_date+","+bookauthor_id);
		        }
		        
		        
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		        if (rs != null) {
		            rs.close();
		        }                
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
