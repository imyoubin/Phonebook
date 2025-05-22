/**************************************
* main 메소드에 모든 코드 적용
* 복잡해 보이는 단점이 있다
***************************************/

package com.javaex.ex01;

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

public class PhonebookApp {
	public static void main(String[] args) throws IOException {

		///////////////////////////////////////////////////////////////////
		// 준비영역 (주로 프로그램 전체에서 사용하는 것들을 배치)
		///////////////////////////////////////////////////////////////////

		// 스캐너(키보드)
		Scanner sc = new Scanner(System.in);

		// 시작화면부터 프로그램 종료까지의 while문 탈출을 위한 변수
		boolean run = true;

		// 파일을 쓰기 스트림 준비
		Writer fw = null;
		BufferedWriter bw = null;

		// 파일을 읽기 스트림 준비
		Reader fr = new FileReader("./PhonebookDB.txt");
		BufferedReader br = new BufferedReader(fr);

		// 리스트 생성
		List<PersonVo> personList = new ArrayList<PersonVo>();

		// 파일을 읽어 리스트에 추가
		// 추가, 삭제등 변동내용이 있으면 리스트에 반영후 파일에 쓴다
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

		br.close(); // 읽기스트림 종료

		// 시작화면 출력
		System.out.println("******************************************");
		System.out.println("*    전화번호 관리 프로그램(ex01)        *");
		System.out.println("******************************************");

		// while 시작(5.종료 전까지 계속반복)
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
			case 1: {// 리스트 출력
				System.out.println("<1.리스트>");
				for (int i = 0; i < personList.size(); i++) { // 리스트출력
					System.out.print(i + 1 + ".   ");
					System.out.print(personList.get(i).getName() + "\t");
					System.out.print(personList.get(i).getHp() + "\t");
					System.out.print(personList.get(i).getCompany() + "\t");
					System.out.println("");
				}
				break;
			}
			case 2: {// 등록
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = sc.nextLine();

				System.out.print(">휴대전화: ");
				String hp = sc.nextLine();

				System.out.print(">회사전화: ");
				String company = sc.nextLine();

				// 리스트에 입력받은 사람을 추가하고
				// 리스트의 내용을 모두 파일에 새로쓴다
				// ( 방금등록된 내용만 추가하는게 아님)
				PersonVo personVo = new PersonVo(); // 입력받은 데이터를 vo로 만든다
				personVo.setName(name);
				personVo.setHp(hp);
				personVo.setCompany(company);

				personList.add(personVo); // 리스트에 추가한다

				// 리스트의 내용을 파일에 저장:
				// *전체리스트를 다 파일에 다시쓴다--> 주의)추가된 주소만 추가하는게 아니다
				fw = new FileWriter("./PhonebookDB.txt"); // 이때 파일의 내용은 다 없어진다
				bw = new BufferedWriter(fw);

				for (int i = 0; i < personList.size(); i++) {
					// 리스트의 내용을 파일에 한줄씩 쓴다
					String str = personList.get(i).getName() + "," + personList.get(i).getHp() + ","
							+ personList.get(i).getCompany();
					bw.write(str);
					bw.newLine(); // 줄바꿈
				}
				bw.flush();
				bw.close();

				System.out.println("[등록되었습니다.]");
				break;
			}
			case 3: // 삭제
				System.out.println("<3.삭제>");
				System.out.print(">번호 : ");
				int no = sc.nextInt();
				personList.remove(no - 1); // 출력되는 번호와 리스트의 index번호가 1차이가 있음

				// 리스트에서 1개의 정보를 삭제한다
				// *전체리스트를 다 파일에 다시쓴다--> 주의)삭제할 사람만 지우는게 아니다
				fw = new FileWriter("./PhonebookDB.txt"); // 이때 파일의 내용은 다 없어진다
				bw = new BufferedWriter(fw);

				for (int i = 0; i < personList.size(); i++) {
					String str = personList.get(i).getName() + "," + personList.get(i).getHp() + ","
							+ personList.get(i).getCompany();
					bw.write(str);
					bw.newLine(); // 줄바꿈
				}
				bw.flush();
				bw.close();
				System.out.println("[삭제되었습니다.]");
				break;

			case 4: // 검색
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String keyword = sc.nextLine();

				for (int i = 0; i < personList.size(); i++) {
					String serchName = personList.get(i).getName();
					if (serchName.contains(keyword)) { // 키워드가 이름항목에 포함되어 있으면 출력한다
						System.out.print(i + 1 + ".   ");
						System.out.print(personList.get(i).getName() + "\t");
						System.out.print(personList.get(i).getHp() + "\t");
						System.out.print(personList.get(i).getCompany() + "\t");
						System.out.println("");
					}
				}
				break;

			case 5: // 종료
				run = false;
				break;

			default: // 없는 번호일때
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
}
