package com.blockbuster.dao;

import java.time.LocalTime;
import java.util.List;

import com.blockbuster.entity.CounterStaff;

public class StaffDao extends GenericDao {
	
	public List<CounterStaff> getCurrentStaff(int shopId){
		
		LocalTime time = LocalTime.now();
		if(time.getHour() > 15) {
			return entityManagerFactory
					.createEntityManager()
					.createQuery("select s from CounterStaff s where s.staffShift = 'Second' and s.shop.shopId = :shopId" , CounterStaff.class)
					.setParameter("shopId", shopId)
					.getResultList();
		}
		else {
			return entityManagerFactory
					.createEntityManager()
					.createQuery("select s from CounterStaff s where s.staffShift = 'First' and s.shop.shopId = :shopId" , CounterStaff.class)
					.getResultList();
		}
		
	}

}
