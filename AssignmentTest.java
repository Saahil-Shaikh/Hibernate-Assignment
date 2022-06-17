package com.blockbuster.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.blockbuster.dao.CustomerDao;
import com.blockbuster.dao.GenericDao;
import com.blockbuster.dao.MovieShopDao;
import com.blockbuster.dao.StaffDao;
import com.blockbuster.entity.CounterStaff;
import com.blockbuster.entity.Customer;
import com.blockbuster.entity.Manager;
import com.blockbuster.entity.Membership;
import com.blockbuster.entity.Movie;
import com.blockbuster.entity.Shop;
import com.blockbuster.entity.ShopMovieRegister;
import com.blockbuster.entity.ShopRecord;

class AssignmentTest {

	private GenericDao dao;

	@BeforeEach
	public void init() {
		dao = new GenericDao();
	}

	@Test
	public void addShops() {
		Shop shop = new Shop();
		shop.setLocation("Bandra");
		shop.setManager(dao.findById(Manager.class, 4));

		dao.save(shop);
	}

	@Test
	public void addStaff() {
		CounterStaff staff = new CounterStaff();
		staff.setStaffName("Simran");
		staff.setShop(dao.findById(Shop.class, 7));
		staff.setStaffShift("Second");

		dao.save(staff);
	}

	@Test
	public void addMovies() {
		Movie movie = new Movie();
		movie.setMovieName("Top Gun Maverick");
		movie.setCertificate(15);
		movie.setRent(300.00);

		dao.save(movie);

	}

	@Test
	public void addMoviesToShop() {
		Movie m = dao.findById(Movie.class, 14);
		Shop s = dao.findById(Shop.class, 7);

		ShopMovieRegister obj = new ShopMovieRegister();
		obj.setMovie(m);
		obj.setShop(s);
		obj.setAllotedAt(LocalDateTime.now());
		obj.setQuantity(5);

		dao.save(obj);
	}

	@Test
	public void registerCustomer() {
		Customer c = dao.findById(Customer.class, 2);
		Shop s = dao.findById(Shop.class, 7);

		Membership member = new Membership();
		member.setCustomer(c);
		member.setShop(s);
		member.setMembershipType("Platinum");

		dao.save(member);

	}

	@Test
	public void issueMoviesToCustomer() {
		Membership m = dao.findById(Membership.class, 15);
		CustomerDao cdao = new CustomerDao();
		MovieShopDao msdao = new MovieShopDao();
		Customer c = cdao.findCustomerByMemberId(m.getMembershipId());
		Movie movie = dao.findById(Movie.class, 12);
		Shop shop = dao.findById(Shop.class, 6);
		long age = ChronoUnit.YEARS.between(c.getDateOfBirth(), LocalDate.now());
		if (msdao.avaibilityStatus(movie.getMovieId(), shop.getShopId()) == false) {
			System.out.println("Movie is out of stock!");
		}
		if (age < movie.getCertificate()) {
			System.out.println("Not a valid age to watch this movie");
		} else {
			ShopRecord record = new ShopRecord();
			record.setMembership(m);
			record.setMovie(movie);
			record.setRentedAt(LocalDateTime.now());
			record.setDueAmount(0);
			record.setShop(shop);
			record.setMovieAvaibility("Leased");
			msdao.deleteOneQuantity(movie.getMovieId(), shop.getShopId());
			dao.save(record);
		}
	}

	@Test
	public void CustomerReturnsMovie() {
		ShopRecord record = dao.findById(ShopRecord.class, 17);
		Membership member = dao.findById(Membership.class, record.getMembership().getMembershipId());

		// considering customer delayed in returning the movie on time

		LocalDateTime ret = LocalDateTime.of(LocalDate.of(2022, 6, 20), LocalTime.of(20, 42));
		record.setMovieAvaibility("Returned");
		LocalDateTime leasedDate = record.getRentedAt();
		
		if(member.getMembershipType().equals("Silver")) {
			leasedDate = leasedDate.plusHours(24);
		}
		
		if(ret.isAfter(leasedDate)) {
			record.setDueAmount(50);
		}
		
		MovieShopDao msdao = new MovieShopDao();
		msdao.addOneQuantity(record.getMovie().getMovieId(), record.getShop().getShopId());
		dao.save(record);

	}
	
	@Test
	public void fetchAvailableMovieInShop() {
		MovieShopDao msdao = new MovieShopDao();
		msdao.fetchAvailableMoviesInShop(7).forEach(m -> System.out.println(m.getMovieName()));
	}
	
	@Test
	public void fetchDueCustomers() {
		CustomerDao cdao = new CustomerDao();
		List<Customer> actual = cdao.findCustomerWithExpiredLeaseTime();
		assertEquals("Saahil", actual.get(0).getCustomerName());
	}
	
	@Test
	public void fetchCurrentStaff() {
		StaffDao sd = new StaffDao();
		sd.getCurrentStaff(6).forEach(s -> System.out.println(s.getStaffName()));
	}

}
