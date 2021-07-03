//메소드 파라미를 Vo사용 

package com.javaex.ex02_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	
	
	//필드 
	
	
	//생성자
	
	
	//겟셋 메소드
	
	
	
	//메소드 일반
	
	//작가 삭제하기
	public int authorDelet(int authorId) {
		int count = -1;
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
			query += " delete from author ";
			query += " where author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			
			 count = pstmt.executeUpdate();
			
			
		// 4.결과처리
			 
			 System.out.println(count + "건이 삭제되었습니다.");
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
		return count;
	}
	
	
	
	//작가수정하기
	public int authorUpdate(int authorId,String authorName,String authorDesc) {
		int count = -1;
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
		query += " update author ";
		query += " set author_name = ?, ";
		query += "     author_desc = ? ";
		query += " where author_id = ? ";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, authorName);
		pstmt.setString(2, authorDesc);
		pstmt.setInt(3, authorId);
		
		 count = pstmt.executeUpdate();
		
		
	// 4.결과처리
		 
		 System.out.println(count + "건 수정");
		 
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
	return count;
	}
	
	
	//작가 등록하기
	public int authorInsert(AuthorVo authorVo) {
		// 0. import java.sql.*;
		int count = -1;
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
			String query= "";
			query += " insert into author ";
			query += " values (seq_author_id.nextval, ?,?) ";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			
			 count =pstmt.executeUpdate();
			
			
			
		// 4.결과처리
			
			System.out.println(count + "건 등록");
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
		
		
		
		return count; //성공 갯수 리턴
	}
	
	//작가리스트 가져오기
	public List<AuthorVo> getAuthorList() {
		//DB값을 가져와서 ArrayList로 전달
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
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
			String query ="";
			query+= " select author_id, ";
			query+= "        author_name, ";
			query+= "        author_desc ";
			query+= " from author ";
			query+= " order by author_id asc ";
			
			pstmt =conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			
			
		// 4.결과처리
			
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName =rs.getString("author_name");
				String authorDesc =rs.getString("author_desc");
				
				AuthorVo authorvo = new AuthorVo(authorId,authorName,authorDesc);
				
				authorList.add(authorvo);
				
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
		
		return authorList;
		
	}
}
