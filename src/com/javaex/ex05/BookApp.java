package com.javaex.ex05;

import java.util.List;


public class BookApp {

	public static void main(String[] args) {
		
		
		//작가테이블 책테이블 완성
		// 작가테이블 시퀀스, 책테이블 시퀀스 완성 
		
		
		//스토리시작 
		//authorDao.authorInsert(); 작가 6명 추가
		
		// bookDao.bookInsert(): 책 8개 추가 
		
		//책수정,삭제, 각자 알아서 테스트
		
		
		
		
		//스캐너를 통해서 사용자한테 키워드 입력받음
		
		//********리스트 출력 *************
		
		//생성자를 불러옴
		BookDao bookDao = new BookDao();
		AuthorDao authorDao = new AuthorDao();
		//리스트
		List<BookVo> bookList;
		
		
		//데이터 가져오기
		bookList = bookDao.getBookList();
		
		//리스트를 for문으로 출력 -- > 메소드 이용
		printList(bookList);
	
	
		
		
		//책 추가
		
		
		
		
		
		
		
		// 작가등록 
		
		//생성자를 불러옴 
		
		//리스트
		List<AuthorVo>  authorList;
		
		//작가 등록
		AuthorVo inauthovo = new AuthorVo("주형준", "호카게");
		int iCount = authorDao.authorInsert(inauthovo);
		
		
		//책등록 
		 BookVo bookvo = new BookVo("닌자의 서", "하시모토 나니","2007-01-01",7);
		int bCount = bookDao.BookInsert(bookvo);
		
		
		//리스트 출력
		//DB에서 데이터 가져오기
			bookList = bookDao.getBookList();
			
			//for
			
			printList(bookList);
		
		
		
		
		
	}

	
	 // 리스트
	public static void printList(List<BookVo> bookList) {
		for(int i =0; i<bookList.size(); i++) {
			
			int bookId = bookList.get(i).getBookId();
			String title =bookList.get(i).getTitle();
			String pubs =bookList.get(i).getPubs();
			String pub_date = bookList.get(i).getPubDate();
			int author_id = bookList.get(i).getAuthorId();
			String author_name = bookList.get(i).getAuthorName();
			String authordesc = bookList.get(i).getAuthorDesc();
			
			System.out.println(bookId+", "+title+", "+pubs+", "+pub_date+", "+author_name+","+author_id+","+authordesc);
		}
		
	}

}
