package com.willwang1989.account.bean;

public class AccountDetails {
	private int Id;
	private String Item;
	private String UserName;
	private String Remarks;
	private int CreateTime;
	private double Amount;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}

	public int getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(int createTime) {
		CreateTime = createTime;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
}
