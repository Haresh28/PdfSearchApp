package com.validate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class ValidateUser {
	private ResultSet ps; 
	private boolean result;
	
	public static Connection conn() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "H@resh2810");
	}
	
	public boolean validateAccount(String user,String password) {
		try {
	    	ps = conn().createStatement().executeQuery("SELECT user, password FROM accounts WHERE user='" + user+ "' \r\n" + 
	    			"AND password= '"+password+"'");
	        result = ps.next();
	        System.out.print("success");
	    	}
		catch(Exception e){
	    		e.printStackTrace();
	    }
		return result;
	}
}
