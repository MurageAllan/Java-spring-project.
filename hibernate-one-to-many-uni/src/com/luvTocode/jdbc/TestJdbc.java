package com.luvTocode.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-04-one-to-many-uni?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
		String user = "hbstudent";
		String pass = "Hbstudent3087#";
		try {
			System.out.println("Connecting to the database" + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection succesful!!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
