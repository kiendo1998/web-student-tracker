<!DOCTYPE html>
<html>
<head>
	<title>Xem tin tức</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Xem chi tiết tin tức</h3>
		<p style="color: red;">${errorMessage}</p>
		<form action="NewsControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden" name="newsId" value="${THE_NEWS.newsid}"/>
			
						<h3 style="color: red; margin-bottom:0px;padding-bottom:0px;">Tiêu đề:</h3>
						
						<textarea style="border: 0px;" name="message" rows="2" cols="70" readonly>${THE_NEWS.newstitle}</textarea>
	<br><br>
						<h3 style="color: red; margin-bottom:0px;padding-bottom:0px;">Nội dung:</h3>
						
						<textarea style="border: 0px;" name="message" rows="10" cols="70" readonly>${THE_NEWS.newscontent}</textarea>
<br><br>
						<h3 style="color: red; margin-bottom:0px;padding-bottom:0px;">Người đăng:</h3>
						
						<input style="border: 0px;" type="text" name="username" 
						           value="${THE_NEWS.username}" readonly/>

		
		</form>
		<div style="clear: both;"></div>
		<p>
		<a href="NewsControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



