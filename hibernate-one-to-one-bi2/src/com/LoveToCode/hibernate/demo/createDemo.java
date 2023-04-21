package com.LoveToCode.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.LoveToCode.hibernate.entity.Address;
import com.LoveToCode.hibernate.entity.Staff;

public class createDemo {

	public static void main(String[] args) {
		// create the session factory.
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Staff.class)
				.addAnnotatedClass(Address.class).buildSessionFactory();
		
		// start session.
		Session session = factory.getCurrentSession();
		
		Staff staff = new Staff("Allan" , "Nyamu" , "Murage" , "30273", "nyamuallan@gmail.com" );
		
		Address address = new Address(  "Ruai", "Nairobi", "0100-181 Matuu" , "0791565635");
		
		// associate the two classes.
		staff.setAddress(address);
		
		try {
			// begin transaction.
			session.beginTransaction();
			 
			System.out.println("Save the staff and in turn save his / her address");
			
			session.save(staff);
			
			System.out.println("\n"); 
			System.out.println("Retrieving the staff and in turn his / her address");
			
			Staff theStaff = session.get(Staff.class, staff.getStaffId());
			System.out.println(theStaff);
			
			System.out.println("\n");
			System.out.println("Deleting the staff and in turn his / her address");
			//session.delete(theStaff);
			
			System.out.println("\n");
			System.out.println("updating the staff and in turn his her address");
			theStaff.setFirstName("lamiq");
			System.out.println(theStaff);
			
			// commit transaction.
			session.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			
			session.close();
			
			factory.close();
		}

	}

}
