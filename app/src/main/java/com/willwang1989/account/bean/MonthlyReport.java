package com.willwang1989.account.bean;

public class MonthlyReport {

	private String Id;
	private String Name;
	private String Total;
	private String Avg;
	private String Pay;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTotal() {
		return Total;
	}
	public void setTotal(String total) {
		Total = total;
	}
	public String getAvg() {
		return Avg;
	}
	public void setAvg(String avg) {
		Avg = avg;
	}
	public String getPay() {
		return Pay;
	}
	public void setPay(String pay) {
		Pay = pay;
	}
}
