/**************************************
파일(나중에는 DB)과 관련된 기능을 PhonebookDao클래스로 분리 


***************************************/
package com.javaex.ex03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PhonebookDao {

	// 필드
	private List<PersonVo> personList;

	// 생성자
	public PhonebookDao() {
		// 메모리에 올리기
		loadList();
	}

	// 메소드 gs
	// 리스트 주소 가져오기
	public List<PersonVo> personSelect() {

		return personList;
	}

	// 메소드 일반
	// 주소록에 1명 추가
	public void personInsert(PersonVo personVo) {
		personList.add(personVo); //리스트에 1명 추가하고
		saveList(); //모든내용을 파일에 저장한다
	}
	
	// 주소록에 1명 삭제
	public void personDelete(int num) {
		personList.remove(num-1); //리스트에서 1명 삭제하고
		saveList(); //모든내용을 파일에 저장한다
	}
	

	// 파일을 읽어 리스트로 만듬(메모리에 올림)
	// 내부에서만 사용
	private void loadList() {

		try {
			personList = new ArrayList<PersonVo>();

			Reader fr = new FileReader("./PhonebookDB.txt");
			BufferedReader br = new BufferedReader(fr);

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
	// 내부에서만 사용
	private void saveList() {

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

}