package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDeleteApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    // 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdbs", "webdbs");
			
			
		    // 3. SQL문 준비 / 바인딩 / 실행
			
			String query ="";             //쿼리문 문자열로 만들기 --> "?" 주의 
			query += " DELETE from author "; 
			query += " where author_name = ? "; // 데이터 바로 넣으면 안되고 ?,?로 만들것
			
			System.out.println(query);
		    
			// *********바인딩************ 
			pstmt = conn.prepareStatement(query);//쿼리문으로 만들기 
			pstmt.setString(1,"유시민");         //?(물음표) 중 1번째
			      
			
			
			int count = pstmt.executeUpdate();             //쿼리문 실행 --> 리턴값으로 성공여부 판단
		
			
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


