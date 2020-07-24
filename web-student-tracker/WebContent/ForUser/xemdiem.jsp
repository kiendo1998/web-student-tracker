<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Xem điểm</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


	<jsp:include page="../header.jsp"/>
		
	
	<div id="container">
		<div id="content">
		 <!--  add a search box -->
      <h3>Xem điểm</h3>
			<form action="UserServlet" method="GET">
			<input type="hidden" name="command" value="XD" />
			 <input type="hidden"  name="username" value="${loginedUser.userName}" />
			 
			<table>
			
				<tr>
					<th>Mã môn học</th>
					<th>Tên môn học</th>
					<th>ĐQT</th>
					<th>Điểm thi</th>
					<th>Điểm kết thúc</th>
					<th>Điểm hệ 4</th>
					<th>Điểm chữ</th>
					<th>Kỳ học</th>
					
				</tr>
				
				<c:forEach var="tempD" items="${D_LIST}">
				<tr>
					<td>${tempD.mamh}</td>
					<td>${tempD.tenmh}</td>
					<td>${tempD.dqt}</td>
					<td>${tempD.diemthi}</td>
					<td>${tempD.diemthi*0.7+tempD.dqt*0.3}</td>
					<td>${tempD.diemhe4}</td>
					<td>${tempD.diemchu}</td>
					<td>${tempD.kyhoc}</td>

				</tr>
				
			</c:forEach>
			</table>
			</form>
		</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>