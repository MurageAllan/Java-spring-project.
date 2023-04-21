package com.LoveToCode.springmvc.Dao;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.LoveToCode.springmvc.entity.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	// inject a sessionfactory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// get a session
		Session session = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> query = session.createQuery("from Customer order by lastName", Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		// get a session
		Session session = sessionFactory.getCurrentSession();

		// save or update the customer
		session.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(int theId) {
		// get a session
		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get a session
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from Customer where customerId =: Id");

		query.setParameter("Id", theId);

		query.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomer(String searchName) {
		// get a session
		Session session = sessionFactory.getCurrentSession();

		Query query = null;
		if (searchName != null) {
			query = session.createQuery(
					"from Customer where lower(firstName) like : theName or lower(lastName) like : theName ",
					Customer.class);
			query.setParameter("theName", "%" + searchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer order by lastName" , Customer.class);

		}
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}
