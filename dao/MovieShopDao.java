package com.blockbuster.dao;

import java.util.List;

import com.blockbuster.entity.Movie;
import com.blockbuster.entity.ShopMovieRegister;

public class MovieShopDao extends GenericDao {
	
	public boolean avaibilityStatus(int movieId , int shopId) {
		ShopMovieRegister reg = entityManagerFactory
							.createEntityManager()
							.createQuery("select r from ShopMovieRegister r where r.shop.shopId = :shopId and r.movie.movieId = :movieId" , ShopMovieRegister.class)
							.setParameter("shopId", shopId)
							.setParameter("movieId", movieId)
							.getSingleResult();
		if(reg.getQuantity() == 0){
			return false;
		}
		else {
			return true;
		}
	}
	
	public void deleteOneQuantity(int movieId , int shopId) {
		ShopMovieRegister reg = entityManagerFactory
				.createEntityManager()
				.createQuery("select r from ShopMovieRegister r where r.shop.shopId = :shopId and r.movie.movieId = :movieId" , ShopMovieRegister.class)
				.setParameter("shopId", shopId)
				.setParameter("movieId", movieId)
				.getSingleResult();
		reg.setQuantity(reg.getQuantity() - 1);
		save(reg);
	}
	
	public void addOneQuantity(int movieId , int shopId) {
		ShopMovieRegister reg = entityManagerFactory
				.createEntityManager()
				.createQuery("select r from ShopMovieRegister r where r.shop.shopId = :shopId and r.movie.movieId = :movieId" , ShopMovieRegister.class)
				.setParameter("shopId", shopId)
				.setParameter("movieId", movieId)
				.getSingleResult();
		reg.setQuantity(reg.getQuantity() + 1);
		save(reg);
	}
	
	public List<Movie> fetchAvailableMoviesInShop(int shopId) {
		List<Movie> movie = entityManagerFactory
					.createEntityManager()
					.createQuery("select r.movie from ShopMovieRegister r where r.shop.shopId = :shopId and r.quantity > 0" , Movie.class)
					.setParameter("shopId", shopId)
					.getResultList();
		return movie;
	}

}
