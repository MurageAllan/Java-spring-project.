package com.LoveToCode.springmvc.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.LoveToCode.springmvc.entity.Customer;
import com.LoveToCode.springmvc.util.SortUtil;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers(int sortField) {
		Session session = sessionFactory.getCurrentSession();

		// determine the sort field
		String fieldName = null;

		switch (sortField) {

		case SortUtil.FIRSTNAME:
			fieldName = "firstName";
			break;
		case SortUtil.LASTNAME:
			fieldName = "lastName";
			break;
		case SortUtil.EMAIL:
			fieldName = "emailAddress";
			break;
		default:
			fieldName = "lastName";
		}
		Query<Customer> query = session.createQuery("from Customer order by " + fieldName, Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(customer);

	}

	@Override
	public Customer getCustomer(Integer theId) {
		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void deleteCustomer(Integer theId) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("delete from Customer where customerId =: Id");

		query.setParameter("Id", theId);

		query.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theSearchName) {

		Session session = sessionFactory.getCurrentSession();

		Query query = null;

		if (theSearchName != null) {
			query = session.createQuery(
					"from Customer where lower(firstName) like : theName or lower(lastName) like : theName",
					Customer.class);
			query.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		} else {
			query = session.createQuery("from Customer order by lastName", Customer.class);
		}
		List<Customer> customers = query.getResultList();

		return customers;
	}

}
