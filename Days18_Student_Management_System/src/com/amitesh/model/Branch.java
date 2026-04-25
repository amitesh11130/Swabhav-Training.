package com.amitesh.model;

public class Branch {

	private String branchName;

	public Branch(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Override
	public String toString() {
		return "Branch [branchName=" + branchName + "]";
	}
}
