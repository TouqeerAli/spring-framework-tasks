package com.springjdbc.springjdbc.model;

public class Student {
	private int id;
	private String studentName;
	private String studentCity;
	
	public Student(int id, String studentName, String studentCity) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.studentCity = studentCity;
	}
	
	public String getstudentCity() {
		return studentCity;
	}
	public void setstudentCity(String studentCity) {
		this.studentCity = studentCity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getstudentName() {
		return studentName;
	}
	public void setstudentName(String studentName) {
		this.studentName = studentName;
	}
	


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", studentCity=" + studentCity + "]";
	}
	
}
