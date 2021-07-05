package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdbs";
	private String pw = "webdbs";
	
	
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
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
	// 책 등록하기
		public int BookInsert(BookVo bookVo) {
			
			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " insert into book ";
				query += " values(seq_book_id.nextval, ?, ?, ?, ?) ";

				pstmt = conn.prepareStatement(query);

				pstmt.setString(1, bookVo.getTitle());
				pstmt.setString(2, bookVo.getPubs());
				pstmt.setString(3, bookVo.getPubDate());
				pstmt.setInt(4, bookVo.getAuthorId());
				count = pstmt.executeUpdate();

				
				// 4.결과처리
				System.out.println(count + "건 등록");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count; // 성공갯수 리턴
			
			
		}
		//책 삭제
		
	public int BookDelete(BookVo bookVo) {
			
			int count = -1;

			getConnection();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "";
				query += " delete from book ";
				query += " where book_id = ? ";

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1,bookVo.getBookId());
				
				count = pstmt.executeUpdate();

				
				// 4.결과처리
				System.out.println(count + "삭제");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			close();

			return count; // 성공갯수 리턴
			
			
		}
		
	
	//업데이트(수정)
	
	public int BookUpdate(BookVo bookVo) {
		
		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update book ";
			query += " set title = ? ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setInt(2, bookVo.getBookId());
			
			
			count = pstmt.executeUpdate();

			
			// 4.결과처리
			System.out.println(count + "수정");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return count; // 성공갯수 리턴
		
		
	}
	
	
		
		//책 리스트 가져오기
		public List<BookVo> getBookList(){
			
			//리스트를 생성 
			List<BookVo> bookList = new ArrayList<BookVo>();
			
			getConnection();
			
			
			try {
				String query = "";
				query += " select  b.book_id, ";
				query += "         b.title, ";
				query += "         b.pubs, ";
				query += "         to_char(b.pub_date,'YYYY-MM-DD') pubD, ";
				query += " 		   b.author_id, ";
				query += " 		   a.author_name, ";
				query += " 		   a.author_desc ";
				query += " from book b, author a ";
				query += " where b.author_id = a.author_id ";
				
				pstmt = conn.prepareStatement(query);
				
				rs = pstmt.executeQuery();
				
				//결과처리
				
				while(rs.next()) {
					int bookId = rs.getInt("book_id");
					String title =rs.getString("title");
					String pubs = rs.getString("pubs");
					String pubDate = rs.getString("pubD");
					int authorId =rs.getInt("author_id");
					String authorName = rs.getString("author_name");
					String authorDesc =rs.getString("author_desc");
					
					BookVo bookVo = new BookVo(bookId,title,pubs,pubDate,authorId,authorName,authorDesc);
					
					bookList.add(bookVo);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			close();
			
			return bookList;
		}
	
}
