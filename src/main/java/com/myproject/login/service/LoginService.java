package com.myproject.login.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginService 
{
	public boolean isValidUser(String uname, String pass)
	{
		try
		{
			ResourceBundle reader = ResourceBundle.getBundle("dbconfig");
			String db_url = reader.getString("db.url");
			String db_user = reader.getString("db.username");
			String db_pass = reader.getString("db.password");
			Class.forName(reader.getString("db.driver"));
			Connection con = DriverManager.getConnection(db_url, db_user, db_pass);
			PreparedStatement pstmt = con.prepareStatement("Select * from user where uname=binary ? and pass=binary ?");
			pstmt.setString(1, uname);
			pstmt.setString(2,  pass);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}

}
