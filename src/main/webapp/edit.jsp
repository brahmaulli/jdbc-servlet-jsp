<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>User Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>
       </br>
       <div class="container col-md-5">
       <form action="update">
        <label>userid</label>
       <input type="text"  name="useirid" value="<c:out value='${usersdetails.userid}' />" /></br>
       <label>Username</label>
       <input type="text" name="username" value="<c:out value='${usersdetails.username}' />" /></br>
             <label>email</label>
       <input type="text" name="emailid" value="<c:out value='${usersdetails.email}' />" /></br>
         <label>country</label>
       <input type="text" name="country" value="<c:out value='${usersdetails.country}' />" />
       <input type="submit" value="update"/>
       
       
       
       </form> 
       
       </div>

        

        </html>