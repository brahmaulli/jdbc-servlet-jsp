package com.brahma.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brahma.pojo.User;

public class UserService {
	
	private  String insert = "insert into users values(?,?,?,?)";
	private  String selectallusersdata = "select * from users";
	protected Connection getConnection() {
		Connection conn = null;
		try {
		Class.forName("oracle.jdbc.OracleDriver");
		
		 conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "brahma");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return conn;
		
	}
	
	
	public void saveUserDetails(User user) {
		
		Connection con	= getConnection();
		try {
		PreparedStatement ps= con.prepareStatement(insert);
		ps.setInt(1, user.getUserid());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getEmail());
		ps.setString(4, user.getCountry());
		
		ps.executeUpdate();
		con.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public ArrayList selectAllusers(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws SQLException {
	
		ArrayList al = new ArrayList();
		Connection con	= getConnection();
		PreparedStatement ps = con.prepareStatement(selectallusersdata);
		 ResultSet rs= ps.executeQuery();
		 
		 while (rs.next()) {
			User user = new User(); 
			 
			int id = rs.getInt("ID");
			String name = rs.getString("name");
			String email = rs.getString("email");
			String country = rs.getString("country");
			
			user.setUserid(id);
			user.setUsername(name);
			user.setEmail(email);
			user.setCountry(country);
			
			al.add(user);
			
		}
		 
		return al; 
		 
		
	}
	
   public static User userEdit(HttpServletRequest httprequest, HttpServletResponse httpresponse) {
		
	   UserService user =new UserService();
	   
	   String findusersdetsilss = "select * from users where id =?";
	   User newuser = new User();
	   
	int id = Integer.parseInt(httprequest.getParameter("id"));
	try {
	
	Connection con	= user.getConnection();
	
	PreparedStatement ps= con.prepareStatement(findusersdetsilss);
	ps.setInt(1, id);
	ResultSet rs= ps.executeQuery();
	
	
	while(rs.next()) {
	int userid = rs.getInt("ID");
	String username = rs.getString("NAME");
	String email = rs.getString("email");
	String country = rs.getString("country");
	
	newuser.setUserid(userid);
	newuser.setUsername(username);
	newuser.setEmail(email);
	newuser.setCountry(country);
	}
	
	
	
	
	
	
	}catch(Exception ex) {
		
	}
	
	return newuser;
		
	}


public String userUpdate(String userid, String username) {
	String message = null;
	String update ="update users set name=? where id=?";
	
	 int id= Integer.parseInt(userid);
	
	Connection con	= getConnection();
	
	try {
	PreparedStatement ps= con.prepareStatement(update);
	
	ps.setString(1, username);
	ps.setInt(2,id);
	
	int count= ps.executeUpdate();
	if(count==1) {
		message="record updated";
	}
	
	}catch(Exception ex) {
		System.out.println(ex);
	}
	return message;
	
}


public void delete(HttpServletRequest httprequest, HttpServletResponse httpresponse){
	
	
String userid	= httprequest.getParameter("id");
int idvale= Integer.parseInt(userid);

String delete= "delete from users where id =?";

Connection con= getConnection();
try {
 PreparedStatement ps=  con.prepareStatement(delete);
 ps.setInt(1,idvale);
int count =  ps.executeUpdate();
if(count==1) {
	System.out.println("record deleted");
}else {
	System.out.println("record not deleted");
}
  
 con.close();
 ps.close();
}catch (Exception e) {
	// TODO: handle exception
}


  
 
  
  

}


	
}
