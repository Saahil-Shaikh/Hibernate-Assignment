package com.blockbuster.test;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.blockbuster.dao.GenericDao;
import com.blockbuster.entity.Address;
import com.blockbuster.entity.Customer;
import com.blockbuster.entity.Manager;

public class InsertTest {
	
	private GenericDao dao;
	
	@BeforeEach
	public void init() {
		dao = new GenericDao();
	}
	
	@Test
	public void addCustomer() {
		Customer c = new Customer();
		Address a = new Address();
		
		a.setCity("Panvel");
		a.setPincode(5678);
		a.setState("MH");
		
		c.setAddress(a);
		c.setDateOfBirth(LocalDate.of(2001, 1, 1));
		c.setCustomerName("Dinesh");
		
		dao.save(c);
	
	}
	
	@Test
	public void addManager() {
		Manager m = new Manager();
		m.setManagerName("Joe");
		
		dao.save(m);
	}

}
