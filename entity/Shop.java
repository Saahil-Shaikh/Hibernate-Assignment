package com.blockbuster.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Shop {
	
	@Id
	@GeneratedValue
	private int shopId;
	
	@Column(unique = true)
	private String location;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "managerId")
	private Manager manager;
	
	/*
	 * @OneToOne(mappedBy = "shop" , fetch = FetchType.LAZY) private CounterStaff
	 * staff;
	 */
	
	@OneToOne(mappedBy = "shop" , fetch = FetchType.LAZY)
	private ShopRecord record;
	
	@OneToOne(mappedBy = "shop" , fetch = FetchType.LAZY)
	private Membership membership;

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	/*
	 * public CounterStaff getStaff() { return staff; }
	 * 
	 * public void setStaff(CounterStaff staff) { this.staff = staff; }
	 */

	public ShopRecord getRecord() {
		return record;
	}

	public void setRecord(ShopRecord record) {
		this.record = record;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	

}
