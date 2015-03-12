package com.willwang1989.account.bean;

public class AccountInfo {
	private Long Id;
	private String Item;
	private double Amount;
	private String Remarks;
	private long CreateTime;
	/**
	 * isPersonal:1：个人消费 0:集体消费
	 */
	private int IsPersonal;
	private int Uid;
	
	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double account) {
		Amount = account;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setRemarks(String remark) {
		this.Remarks = remark;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		this.CreateTime = createTime;
	}

	public int getIsPersonal() {
		return IsPersonal;
	}

	public void setIsPersonal(int isPersonal) {
		IsPersonal = isPersonal;
	}

	public int getUid() {
		return Uid;
	}

	public void setUid(int uid) {
		this.Uid = uid;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}


}
