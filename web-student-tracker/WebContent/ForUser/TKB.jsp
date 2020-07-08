<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Xem TKB</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


	<jsp:include page="../header.jsp"/>
		
	
	<div id="container">
		<div id="content">
		 <!--  add a search box -->
      <h3>Thời khóa biểu</h3>
			<form action="UserServlet" method="GET">
			<input type="hidden" name="command" value="TKB" />
			 <input type="hidden"  name="username" value="${loginedUser.userName}" />
			 
			<table>
			
				<tr>
					<th>Môn học</th>
					<th>Lịch học</th>
					
				</tr>
				
				<c:forEach var="tempTKB" items="${TKB_LIST}">
				<tr>
					<td>${tempTKB.monhoc}</td>
					<td>${tempTKB.tkb}</td>

				</tr>
				
			</c:forEach>
			</table>
			</form>
		</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>