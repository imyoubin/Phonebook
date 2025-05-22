/**************************************
전화번호 관리 프로그램(ex02) 에서 
파일과 관련된 기능을 PhonebookDao클래스로 분리 

PhonebookDao (데이터 관련내용)-------------
getPersonList() : 리스트가져오기
addPerson(): 전화번호 추가
removePerson(): 전화번호 삭제
-----------------------------------------

showList() //리스트의 내용을 모두출력
showList(String keyword) //리스트의 내용중 키워드를 포함한 내용만 출력

***************************************/
package com.javaex.ex03;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class PhonebookApp {

	// 필드
	
	// 메소드-일반
	public static void main(String[] args) throws IOException {

		///////////////////////////////////////////////////////////////////
		// 준비영역 (주로 프로그램 전체에서 사용하는 것들을 배치)
		///////////////////////////////////////////////////////////////////
		
		// PhonebookDao
		// 생성자가 파일을 읽어 personList를 만든다
		PhonebookDao phonebookDao = new PhonebookDao();
		
		// 스캐너(키보드)
		Scanner sc = new Scanner(System.in);

		// 시작화면부터 프로그램 종료까지의 while문 탈출을 위한 변수
		boolean run = true;


		// 시작화면 출력
		System.out.println("******************************************");
		System.out.println("*    전화번호 관리 프로그램(ex03)        *");
		System.out.println("******************************************");

		// while 시작
		while (run) {
			// 메뉴 출력
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("------------------------------------------");
			System.out.print(">메뉴번호: ");

			// 메뉴 입력
			int menuNum = sc.nextInt();
			sc.nextLine(); // 숫자 뒤에 문자열 받을때 생기는 오류 수정용

			// switch() 시작
			switch (menuNum) {

				case 1: //리스트
					System.out.println("<1.리스트>");
					
					showList(phonebookDao.personSelect()); //모든 리스트 출력함수
					break;
				
				case 2: //등록
					System.out.println("<2.등록>");
					System.out.print(">이름: ");
					String name = sc.nextLine();
					
					System.out.print(">휴대전화: ");
					String hp = sc.nextLine();

					System.out.print(">회사전화: ");
					String company = sc.nextLine();

					// 입력받은 내용 personVo로 묶기
					PersonVo personVo = new PersonVo();
					personVo.setName(name);
					personVo.setHp(hp);
					personVo.setCompany(company);

					// 추가
					phonebookDao.personInsert(personVo);
					
					System.out.println("[등록되었습니다.]");
					break;	
					
				case 3: //삭제
					System.out.println("<3.삭제>");
					System.out.print(">번호 : ");
					int no = sc.nextInt();
					
					//삭제
					phonebookDao.personDelete(menuNum);
					
					System.out.println("[삭제되었습니다.]");
					break;
					
				// 4(검색)
				case 4:
					System.out.println("<4.검색>");
					System.out.print(">이름: ");
					String keyword = sc.nextLine();
					
					showList(phonebookDao.personSelect(), keyword); //키워드를 포함한 리스트 출력
					break;	
					
				// 5(종료)
				case 5:
					run = false;
					break;
	
				// 없는 번호일때
				default:
					System.out.println("[다시 입력해 주세요.]");
					break;
					
			}// switch() 종료

		} // while 종료

	

		// 종료화면
		System.out.println("");
		System.out.println("******************************************");
		System.out.println("*                   감사합니다           *");
		System.out.println("******************************************");
		
		
		sc.close(); //스캐너 종료
	}
	
	
	///////////////////////////////////////////////////////////////////
	// 복잡한코드, 반복적으로 사용하는 코드를 메소드로 만듬
	///////////////////////////////////////////////////////////////////
	
	//리스트의 내용을 모두 출력   
	//*아래 메소드와 이름같음(메소드 오버로딩)
	public static void showList(List<PersonVo> personList) {
		showList(personList, ""); // 아래함수에 키워드를 아무것도 없는 값으로 전달
	}
	
	//리스트의 내용중 키워드를 포함한 내용만 출력 
	//*위 메소드와 이름같음(메소드 오버로딩)
	public static void showList(List<PersonVo> personList, String keyword) {
		for (int i = 0; i < personList.size(); i++) {
			String serchName = personList.get(i).getName();
			if (serchName.contains(keyword)) {
				System.out.print(i + 1 + ".   ");
				System.out.print(personList.get(i).getName() + "\t");
				System.out.print(personList.get(i).getHp() + "\t");
				System.out.print(personList.get(i).getCompany() + "\t");
				System.out.println("");
			}
		}
	}
}	