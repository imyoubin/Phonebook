package com.javaex.ex01;

import java.io.*;
import java.util.*;

public class Phone {
    private String name;
    private String hp;
    private String company;

    public Phone() {}

    public Phone(String name, String hp, String company) {
        this.name = name;
        this.hp = hp;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public String getHp() {
        return hp;
    }

    public String getCompany() {
        return company;
    }

    public static List<Phone> readPhoneList(String filePath) {
        List<Phone> phoneList = new ArrayList<>();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    phoneList.add(new Phone(data[0].trim(), data[1].trim(), data[2].trim()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("전화번호부 파일이 존재하지 않습니다: " + filePath);
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류 발생: " + e.getMessage());
        }
        return phoneList;
    }

    @Override
    public String toString() {
        return "이름: " + name + ", 핸드폰: " + hp + ", 회사전화: " + company;
    }
}