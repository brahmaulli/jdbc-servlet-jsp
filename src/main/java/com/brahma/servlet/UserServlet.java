package com.brahma.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.DefaultFocusManager;

import com.brahma.pojo.User;
import com.brahma.service.UserService;

public class UserServlet extends GenericServlet{
	
	
	/*
	 * protected void service(HttpServletRequest req,HttpServletResponse res) {
	 * 
	 * String name = req.getParameter("name"); String email =
	 * req.getParameter("email"); String country = req.getParameter("country");
	 * 
	 * }
	 */

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		HttpServletRequest httprequest = (HttpServletRequest) request; 
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		 String action = httprequest.getServletPath();
		
		
		
	    switch (action) {
		 case "/adduser":
			showUSerDetails(httprequest,httpresponse);
			break;
		 case "/insertuser":
			 insertuser(httprequest, httpresponse);
			 break;
			 
		 case "/edit":
			 userEdit(httprequest, httpresponse);
			 break;
			 
		 case "/update":
			 userUpdate(httprequest, httpresponse);
			 break;	 
			 
		 case "/delete":
			 delete(httprequest, httpresponse);
			 break;		 
		 
		 case "/selectAllUsers":
			try {
				selectAllusers(httprequest,httpresponse);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	 
		}
		
	
		
		
		
		
		//httpresponse.sendRedirect("user-list.jsp");
		
		
	}
	
	

	



	private void delete(HttpServletRequest httprequest, HttpServletResponse httpresponse) {
		// TODO Auto-generated method stub
		
		UserService userservice = new UserService();
		
		userservice.delete(httprequest,httpresponse);
		
		try {
			httpresponse.sendRedirect("selectAllUsers");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







	private void userUpdate(HttpServletRequest httprequest, HttpServletResponse httpresponse) {
		// TODO Auto-generated method stub
	
		UserService userservice = new UserService();
		String userid= httprequest.getParameter("useirid");
		String username= httprequest.getParameter("username");
     	String message	= userservice.userUpdate(userid,username);
     	
     	System.out.println(userid);
     	System.out.println();
	
	//RequestDispatcher rd= httprequest.getRequestDispatcher("selectAllUsers");
	try {
		httpresponse.sendRedirect("selectAllUsers");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println(userid);
		
	}







	private void userEdit(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws ServletException, IOException {
		
		 User newuser = UserService.userEdit(httprequest, httpresponse);
		 
		 httprequest.setAttribute("usersdetails", newuser);
		 
		 RequestDispatcher dispather= httprequest.getRequestDispatcher("edit.jsp");
		 
		 dispather.forward(httprequest, httpresponse);
		 
		 
		 
		
	}







	private void selectAllusers(HttpServletRequest httprequest, HttpServletResponse httpresponse) throws SQLException {
		
		
		
		
		UserService userservice = new UserService();
		ArrayList al = userservice.selectAllusers(httprequest,httpresponse);
		httprequest.setAttribute("listUser", al);
		
		 RequestDispatcher dispatcher = httprequest.getRequestDispatcher("user-list.jsp");
	        try {
				dispatcher.forward(httprequest, httpresponse);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}



	void showUSerDetails(HttpServletRequest httprequest,HttpServletResponse httpresponse) {
		
		 RequestDispatcher dispatcher = httprequest.getRequestDispatcher("userregistration.jsp");
	        try {
				dispatcher.forward(httprequest, httpresponse);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	void insertuser(HttpServletRequest httprequest,HttpServletResponse httpresponse) throws ServletException, IOException{
		
		String name= httprequest.getParameter("name");
		String email= httprequest.getParameter("email");//country
		String country= httprequest.getParameter("country");
		System.out.println("hai");
		User user = new User();
		UserService userservice = new UserService();
		Random rm = new Random();
		int numbers = rm.nextInt(900)+100;
		user.setUserid(numbers);
		user.setUsername(name);
		user.setCountry(country);
		user.setEmail(email);
		userservice.saveUserDetails(user);
		
	    RequestDispatcher dispatcher = httprequest.getRequestDispatcher("selectAllUsers");
        dispatcher.forward(httprequest, httpresponse);
		
	}

	
	

}
