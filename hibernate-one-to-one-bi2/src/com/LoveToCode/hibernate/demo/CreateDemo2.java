package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Address;
import com.LoveToCode.hibernate.entity.Staff;

public class CreateDemo2 {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Staff.class).addAnnotatedClass(Address.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		Staff staff = new Staff("Janice", "Wanjiku", "Kamau", "30273", "wanjikujanice@gmail.com");

		Address address = new Address("Thika", "Kiambu", "0100-181 Nyeri", "0772629227");

		staff.setAddress(address);
		address.setStaff(staff);

		try {
			// start a new transaction.
			session.beginTransaction();

			System.out.println("\n");
			System.out.println("SAVING");
			session.save(address);

			// commit the transaction.
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			session.close();
			
			factory.close();

		}
	}

}
