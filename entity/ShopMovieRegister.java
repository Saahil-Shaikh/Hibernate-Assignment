package com.blockbuster.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@IdClass(ShopMoviePK.class)
public class ShopMovieRegister {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "shopId")
	private Shop shop;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "movieId")
	private Movie movie;
	
	private LocalDateTime allotedAt;
	
	private int quantity;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public LocalDateTime getAllotedAt() {
		return allotedAt;
	}

	public void setAllotedAt(LocalDateTime allotedAt) {
		this.allotedAt = allotedAt;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
