<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <link type="text/css" rel="stylesheet" href="css/style.css">
      <title>Thông tin người dùng</title>
   </head>
   <body>
 
     <jsp:include page="header.jsp"/>

 
      <h3>Thông tin người dùng: ${loginedUser.userName}</h3>
 <p style="color: red;">${errorMessage}</p>
      <form method="GET" action="${pageContext.request.contextPath}/updateuser">
         <input type="hidden" name="username0" value="${loginedUser.userName}" />
         <table border="5" class="user">
            <tr>
               <td>User Name</td>
               <td><input type="text" name="username" value= "${loginedUser.userName}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${loginedUser.password}" /> </td>
            </tr>
      		
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Sửa thông tin" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>