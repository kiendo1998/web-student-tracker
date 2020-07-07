<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>User Info</title>
   </head>
   <body>
 
     <jsp:include page="header.jsp"/>
 
      <h3>Xin chào: ${loginedUser.userName}</h3>
 
      User Name: <b>${loginedUser.userName}</b>
      <br />
      
      <c:if test="${loginedUser.getGender()=='M'}">
		Giới tính: Nam <br />
		</c:if>
		<c:if test="${loginedUser.getGender()=='F'}">
		Giới tính: Nữ <br />
		</c:if>
      
      
 
 
   </body>
</html>