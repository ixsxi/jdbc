package com.javaex.ex03_1;

import java.util.List;

public class AuthorApp {
	//메소드일반
	public static void main(String[] args) {
		
		//생성자를 불러옴 
		AuthorDao authorDao = new AuthorDao();
		//리스트
		List<AuthorVo>  authorList;
		
		//리스트 출력 0x777
		
		//DB에서 데이터 가져오기
		 authorList = authorDao.getAuthorList();
		 
		//리스트를 for문으로 출력 --> 메소드로 정의
		 printList(authorList);
		 
		
		//작가등록
		 AuthorVo inauthovo = new AuthorVo("주형준", "호카게");
		int iCount = authorDao.authorInsert(inauthovo);
		if(iCount>0) { 
			System.out.println("[등록되었습니다.]");
		}else {
			System.out.println("관리자한테 문의하세요" + iCount );
		}
		
			//리스트 출력
			//DB에서 데이터 가져오기
				authorList = authorDao.getAuthorList();
				
			//for문으로 출력 --> 메소드로 정의
				
				 printList(authorList);
	
		
		
		//작가수정 iCount 사용은 생략했음
		int uCount = authorDao.authorUpdate(3, "주형준 사스케","우치하일족");

		//리스트 출력
		//DB에서 가져오기
		 authorList = authorDao.getAuthorList();
		
		//리스트를 for문으로 출력 (메소드를 정의)
		printList(authorList);
		
		 
		
		//작가삭제
		int dCount = authorDao.authorDelet(7);
		//리스트출력
		authorList = authorDao.getAuthorList();
		//for문으로 출력 
		printList(authorList);
		
		
		
		/*
		//작가 1명의 정보
		???= authorDao.getAuthorOne(3);
		*/
	}
	
	//리스트 출력 메소드 
	public static void printList(List<AuthorVo> authorList) {
		
			for(int i =0; i<authorList.size(); i++) {
		
		
			
			int authorId = authorList.get(i).getAuthorId();
			String authorName = authorList.get(i).getAuthorName();
			String authorDesc = authorList.get(i).getAuthorDesc();
			
			System.out.println(authorId+"   "+authorName+"   "+authorDesc);
		
		}
			
			System.out.println("============================");
			
	}
	
	
	
	
	
	
	

}
