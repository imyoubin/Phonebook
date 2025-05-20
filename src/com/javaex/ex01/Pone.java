package com.javaex.ex01;

public class Pone extends PhoneApp{
	
	private String name ;
	private String hp ;
	private String  company;
	
	public Pone() {
	}

	public Pone(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Pone [name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
	
	
	

}
