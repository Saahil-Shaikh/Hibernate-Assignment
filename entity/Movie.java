package com.blockbuster.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Movie {
	
	@Id
	@GeneratedValue
	private int movieId;
	
	private String movieName;
	
	@Column(length = 2)
	private int certificate;
	
	private double rent;
	
	@OneToOne(mappedBy = "movie")
	private ShopRecord record;

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getCertificate() {
		return certificate;
	}

	public void setCertificate(int certificate) {
		this.certificate = certificate;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public ShopRecord getRecord() {
		return record;
	}

	public void setRecord(ShopRecord record) {
		this.record = record;
	}
	
	
	
}
