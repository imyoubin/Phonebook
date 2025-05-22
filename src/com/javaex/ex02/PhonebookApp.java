/**************************************
전화번호 관리 프로그램(ex01) 에서 아래의 기능을 메소드로 만듬

loadList() //파일을 읽어 리스트로 만듬(메모리에 올림)
saveList() //리스트(메모리)의 데이터를 파일로 저장
showList() //리스트의 내용을 모두출력
showList(String keyword) //리스트의 내용중 키워드를 포함한 내용만 출력

***************************************/

package com.javaex.ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javaex.ex01.PersonVo;

public class PhonebookApp {

	// 필드
	// 여러 메소드에서 공통으로 사용
	private static List<PersonVo> personList;

	// 메소드-일반
	public static void main(String[] args) {

		///////////////////////////////////////////////////////////////////
		// 준비영역 (주로 프로그램 전체에서 사용하는 것들을 배치)
		///////////////////////////////////////////////////////////////////

		// 스캐너(키보드)
		Scanner sc = new Scanner(System.in);

		// 시작화면부터 프로그램 종료까지의 while문 탈출을 위한 변수
		boolean run = true;

		// 파일을 읽어 리스트로 만드는 메소드 실행
		loadList();

		// 시작화면 출력
		System.out.println("******************************************");
		System.out.println("*    전화번호 관리 프로그램(ex02)        *");
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

			case 1: // 리스트
				System.out.println("<1.리스트>");

				showList(); // 모든 리스트 출력함수
				break;

			case 2: // 등록
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = sc.nextLine();

				System.out.print(">휴대전화: ");
				String hp = sc.nextLine();

				System.out.print(">회사전화: ");
				String company = sc.nextLine();

				// 리스트에 추가하기-->리스트 최신으로 유지하기
				PersonVo personVo = new PersonVo();
				personVo.setName(name);
				personVo.setHp(hp);
				personVo.setCompany(company);

				personList.add(personVo);

				// 리스트의 내용을 파일에 저장:
				// *전체리스트를 다 파일에 다시쓴다--> 주의)추가된 주소만 추가하는게 아니다
				saveList();
				System.out.println("[등록되었습니다.]");
				break;

			case 3: // 삭제
				System.out.println("<3.삭제>");
				System.out.print(">번호 : ");
				int no = sc.nextInt();
				personList.remove(no - 1); // 리스트에서 삭제(실제출력되는 번호와 리스트의 index번호가 1 차이난다)

				// 리스트의 내용을 파일에 저장:
				// *전체리스트를 다 파일에 다시쓴다--> 주의)삭제할 사람만 지우는게 아니다
				saveList();
				System.out.println("[삭제되었습니다.]");
				break;

			// 4(검색)
			case 4:
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String keyword = sc.nextLine();

				showList(keyword); // 키워드를 포함한 리스트 출력
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

		sc.close(); // 스캐너 종료
	}

	///////////////////////////////////////////////////////////////////
	// 복잡한코드, 반복적으로 사용하는 코드를 메소드로 만듬
	///////////////////////////////////////////////////////////////////

	// 파일을 읽어 리스트로 만듬(메모리에 올림)
	public static void loadList() {
		try {
			Reader fr = new FileReader("./PhonebookDB.txt");
			BufferedReader br = new BufferedReader(fr);

			personList = new ArrayList<PersonVo>();

			while (true) {
				String line = br.readLine();
				if (line == null) {
					break;
				}

				String[] data = line.split(",");
				String name = data[0];
				String hp = data[1];
				String company = data[2];

				PersonVo personVo = new PersonVo(name, hp, company);

				personList.add(personVo);

			}
			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 리스트(메모리)의 데이터를 파일로 저장
	public static void saveList() {
		try {
			Writer fw = new FileWriter("./PhonebookDB.txt");
			BufferedWriter bw = new BufferedWriter(fw);

			for (int i = 0; i < personList.size(); i++) {
				String str = personList.get(i).getName() + "," + personList.get(i).getHp() + ","
						+ personList.get(i).getCompany();
				bw.write(str);
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 리스트의 내용을 모두 출력
	// *아래 메소드와 이름같음(메소드 오버로딩)
	public static void showList() {
		showList(""); // 아래함수에 키워드를 아무것도 없는 값으로 전달
	}

	// 리스트의 내용중 키워드를 포함한 내용만 출력
	// *위 메소드와 이름같음(메소드 오버로딩)
	public static void showList(String keyword) {
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
