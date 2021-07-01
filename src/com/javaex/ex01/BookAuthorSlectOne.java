package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorSlectOne {

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
				    
				    query +=" select bo.book_id, ";
				    query +=" bo.title, ";
				    query +=" bo.pubs, ";
				    query +=" bo.pub_date, ";
				    query +=" au.author_id, ";
				    query +=" au.author_name, ";
				    query +=" au.author_desc ";
				    query +=" from  book bo , author au ";
				    query +=" where bo.author_id = au.author_id and bo.book_id = ? ";
				    
				    
				    pstmt = conn.prepareStatement(query);
				    pstmt.setInt(1, 2);
				    
				    rs = pstmt.executeQuery();
				    
				    System.out.println(query);
					
					
					
				    // 4.결과처리

				    while(rs.next()) {
				      int bookid = rs.getInt("book_id");
				      String bt =rs.getString("title");
				      String bp =rs.getString("pubs");
				      String bp1 =rs.getString("pub_date");
				      int aa =rs.getInt("author_id");
				      String aan = rs.getString("author_name");
				      String aad =rs.getString("author_desc");
				      
				      
				      System.out.println(
		bookid +"," + bt+","+bp+","+bp1+","+aa+","+aan+","+aad		    		  
				    		  
				    		  );
				      
				      
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