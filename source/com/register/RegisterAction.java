package com.register;
import java.sql.*;
public class RegisterAction {
	public static int register(RegisterUser r) {
		int status=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "H@resh2810");
			PreparedStatement ps=conn.prepareStatement("insert into accounts(user,password) values(?,?)");
			ps.setString(1, r.getUser());
			ps.setString(2, r.getPassword());
			status=ps.executeUpdate();
		}
		catch(Exception e){
    		e.printStackTrace();
		}
		return status;
	}
}
