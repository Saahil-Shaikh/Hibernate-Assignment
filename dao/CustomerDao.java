package com.blockbuster.dao;

import java.util.List;

import com.blockbuster.entity.Customer;

public class CustomerDao extends GenericDao {
	
	public Customer findCustomerByMemberId(int memberId) {
		return entityManagerFactory
				.createEntityManager()
				.createQuery("select c from Customer c where c.membership.membershipId = :memberId" , Customer.class)
				.setParameter("memberId", memberId)
				.getSingleResult();
				
	}
	
	public List<Customer> findCustomerWithExpiredLeaseTime(){
		return entityManagerFactory
				.createEntityManager()
				.createQuery("select r.membership.customer from ShopRecord r where r.dueAmount > 0" , Customer.class)
				.getResultList();
	}

}
