﻿<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Thêm tin tức</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Thêm tin tức</h3>
		<form action="NewsControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD"/>
			<p style="color: red;">${errorMessage}</p>
			<table>
			
				
					<tr>
						<td><label>Tiêu đề:</label></td>
						<td><input type="text" name="newstitle" /></td>
					
					</tr>
					<tr>
						<td><label>Nội dung:</label></td>
						<td><input type="text" name="newscontent" /></td>
					
					</tr>
					<tr>
						<td><label>Người đăng:</label></td>
						<td><input type="text" name="username" value="${loginedUser.userName}" /></td>
					
					</tr>

<!-- 					<tr> -->
<!-- 						<td><label>Số tín:</label></td> -->
<!-- 						<td><input type="text" name="sotin" /></td> -->
					
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td><label>Điểm tích lũy:</label></td> -->
<!-- 						<td><input type="text" name="diemtichluy" /></td> -->
					
<!-- 					</tr> -->
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Lưu" class="save" /></td>
					
					</tr>
				
				</tbody>
			
			</table>
		
		</form>
		<div style="clear: both;"></div>
		<p>
		<a href="NewsControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



