package com.amitesh.model;

public class Student {

	private int id;
	private String name;
	private int age;
	private int branchid;

	public Student(int id, String name, int age, int branchid) {

		this.id = id;
		this.name = name;
		this.age = age;
		this.branchid = branchid;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getBranchId() {
		return branchid;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", branchid=" + branchid + "]";
	}
}
