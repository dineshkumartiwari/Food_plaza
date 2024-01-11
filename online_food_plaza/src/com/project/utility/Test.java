package com.project.utility;

import java.sql.Connection;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = DbConnection.getConnection();
		if (con != null) {
			System.out.println("Connected...");
		} else {
			System.out.println("Not connected...");
		}
	}

}
