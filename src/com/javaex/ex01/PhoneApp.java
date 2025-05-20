package com.javaex.ex01;

import java.io.*;
import java.util.*;

public class PhoneApp {

    private static final String filePath = "C:/javaStudy/PhoneDB.txt";
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Phone> pList = Phone.readPhoneList(filePath);

        System.out.println("******************************");
        System.out.println("*    전화번호 관리 프로그램     *");
        System.out.println("******************************");

        while (true) {
            System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
            System.out.println("-------------------------------------");
            System.out.print(">메뉴번호: ");

            String number = sc.nextLine();

            if (number.equals("1")) {
                System.out.println("<1.리스트>");
                for (int i = 0; i < pList.size(); i++) {
                    System.out.println((i + 1) + ". " + pList.get(i));
                }

            } else if (number.equals("2")) {
                System.out.println("<2.등록>");
                System.out.print("이름: ");
                String name = sc.nextLine();
                System.out.print("휴대전화: ");
                String hp = sc.nextLine();
                System.out.print("회사전화: ");
                String company = sc.nextLine();

                Phone newPhone = new Phone(name, hp, company);
                pList.add(newPhone);
                writePhoneList(pList);
                System.out.println("[등록되었습니다.]");

            } else if (number.equals("3")) {
                System.out.println("<3.삭제>");
                System.out.print(">번호: ");
                String dn = sc.nextLine();

                try {
                    int delnum = Integer.parseInt(dn) - 1;
                    if (delnum >= 0 && delnum < pList.size()) {
                        pList.remove(delnum);
                        writePhoneList(pList);
                        System.out.println("[삭제되었습니다.]");
                    } else {
                        System.out.println("[올바른 번호를 입력하세요.]");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[숫자를 입력해주세요.]");
                }

            } else if (number.equals("4")) {
                System.out.println("<4.검색>");
                System.out.print(">이름: ");
                String key = sc.nextLine();

                for (int i = 0; i < pList.size(); i++) {
                    Phone p = pList.get(i);
                    if (p.getName().contains(key)) {
                        System.out.println((i + 1) + ". " + p.getName() + " " + p.getHp() + " " + p.getCompany());
                    }
                }

            } else if (number.equals("5")) {
                System.out.println("******************************");
                System.out.println("*          감사합니다         *");
                System.out.println("******************************");
                break;

            } else {
                System.out.println("[다시 입력 해주세요.]");
            }
        }

        sc.close();
    }

    private static void writePhoneList(List<Phone> list) {
        try (OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
             BufferedWriter bw = new BufferedWriter(osw)) {
            for (Phone p : list) {
                bw.write(p.getName() + "," + p.getHp() + "," + p.getCompany());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
        }
    }
}