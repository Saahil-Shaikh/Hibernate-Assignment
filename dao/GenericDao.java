package com.blockbuster.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class GenericDao {

protected static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("hibernateAssignment");
		Runtime.getRuntime().addShutdownHook(new Thread(() -> entityManagerFactory.close()));
	}
	
	public void save(Object obj) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction tx = entityManager.getTransaction();
			tx.begin();

			entityManager.merge(obj);
			
			tx.commit();
			
		}
		finally {
			entityManager.close();
		}
		
	}
	
	public <E> E findById(Class<E> cls , Object pk) {
		EntityManager entityManager = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			E e = entityManager.find(cls, pk);	
			return e;
		}
		finally {
			entityManager.close();
		}
		
	}
	
}
