<!DOCTYPE html>
<html>
<head>
	<title>Sửa tin tức</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Sửa tin tức</h3>
		<p style="color: red;">${errorMessage}</p>
		<form action="NewsControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden" name="newsId" value="${THE_NEWS.newsid}"/>
			
			<table>
			
				<tbody>
					<tr>
						<td><label>Tiêu đề:</label></td>
						<td><input type="text" name="newstitle" 
						           value="${THE_NEWS.newstitle}"/></td>
					
					</tr>
					<tr>
						<td><label>Nội dung:</label></td>
						<td><input type="text" name="newscontent" 
						           value="${THE_NEWS.newscontent}"/></td>
					
					</tr>
					<tr>
						<td><label>Người đăng:</label></td>
						<td><input type="text" name="username" 
						           value="${THE_NEWS.username}"/></td>
					
					</tr>
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



