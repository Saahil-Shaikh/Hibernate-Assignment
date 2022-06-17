package com.blockbuster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CounterStaff {
	
	@Id
	@GeneratedValue
	private int staffId;
	
	private String staffName;
	
	private String staffShift;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shopId")
	private Shop shop;

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffShift() {
		return staffShift;
	}

	public void setStaffShift(String staffShift) {
		this.staffShift = staffShift;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}	
	
}
