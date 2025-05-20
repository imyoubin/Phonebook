package com.javaex.ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneApp {

    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
        String filePath = "C:\\javaStudy\\PhoneDB.txt";

        System.out.println("******************************");
        System.out.println("*      전화번호 관리 프로그램       *");
        System.out.println("******************************");

        while (true) {
            System.out.println("1.리스트  2. 등록  3. 삭제  4. 검색  5. 종료");
            System.out.println("-------------------------------------");
            System.out.print(">메뉴번호: ");

            int menu = sc.nextInt();
            sc.nextLine();  

            if (menu == 1) {
                
                System.out.println("<1.리스트>");
                try {
                    FileReader fr = new FileReader(filePath);
                    BufferedReader br = new BufferedReader(fr);

                    String line;
                    int index = 1;
                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data.length == 3) {
                            System.out.println(index + ". " + data[0] + "\t" + data[1] + "\t" + data[2]);
                            index++;
                        }
                    }

                    br.close();
                    fr.close();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            } else if (menu == 2) {
               
                System.out.println("<2.등록>");
                System.out.print("이름: ");
                String name = sc.nextLine();
                System.out.print("휴대전화: ");
                String hp = sc.nextLine();
                System.out.print("회사전화: ");
                String company = sc.nextLine();

                try {
                    PrintWriter pw = new PrintWriter(new FileWriter(filePath, true));
                    		
                    pw.println(name + "," + hp + "," + company);
                    pw.close();
                    System.out.println("[등록되었습니다.]");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            } else if (menu == 3) {
                
                System.out.println("<3.삭제>");

                List<String> plist = new ArrayList<>();
                try {
                    FileReader fr = new FileReader(filePath);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                    	plist.add(line);
                    }
                    br.close();
                    fr.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    continue;
                }

                for (int i = 0; i < plist.size(); i++) {
                    String[] data = plist.get(i).split(",");
                    if (data.length == 3) {
                        System.out.println((i + 1) + ". " + data[0] + "\t" + data[1] + "\t" + data[2]);
                    }
                }

                System.out.print(">삭제: ");
                int del = sc.nextInt();
                sc.nextLine();

                if (del < 1 || del > plist.size()) {
                    System.out.println("[올바른 번호를 입력하세요]");
                } else {
                	plist.remove(del - 1);
                    try {
                        PrintWriter pw = new PrintWriter(new FileWriter(filePath)); 
                        for (String record : plist) {
                            pw.println(record);
                        }
                        pw.close();
                        System.out.println("[삭제되었습니다.]");
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }

            } else if (menu == 4) {
            	System.out.println("<4.검색");
                System.out.println(">이름: ");
                String key = sc.nextLine();
                
                List<String> plists = new ArrayList<>();
                                
                try {
					
				} catch (Exception e) {
					
				}
                

            } else if (menu == 5) {
             
                System.out.println("******************************");
                System.out.println("*           감사합니다          *");
                System.out.println("******************************");
                break;

            } else {
                System.out.println("[다시 입력 해주세요.]");
            }
        }

        sc.close();
    }
}
