package com.blockbuster.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ShopRecord {
	
	@Id
	@GeneratedValue
	private int recordId;
	
	@OneToOne
	@JoinColumn(name = "membershipId")
	private Membership membership;
	
	@OneToOne
	@JoinColumn(name = "movieId")
	private Movie movie;
	
	@OneToOne
	@JoinColumn(name = "shopId")
	private Shop shop;
	
	private LocalDateTime rentedAt;
	
	private double dueAmount;
	
	private String movieAvaibility;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public LocalDateTime getRentedAt() {
		return rentedAt;
	}

	public void setRentedAt(LocalDateTime rentedAt) {
		this.rentedAt = rentedAt;
	}

	public double getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}

	public String getMovieAvaibility() {
		return movieAvaibility;
	}

	public void setMovieAvaibility(String movieAvaibility) {
		this.movieAvaibility = movieAvaibility;
	}
	
	

}
