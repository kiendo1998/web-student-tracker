<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Trang chủ</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


<jsp:include page="../header.jsp"/>
	 
	<div id="container">
		<div id="content">
		<!-- put new button: Add new subject -->
		
		 <!--  add a search box -->
            <form action="NewsControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm tin tức:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm kiếm" class="add-student-button" />
            
            </form>
            <h3>Danh sách tin tức trong hệ thống</h3>
			<table>
			
				<tr>
					<th>Mã tin</th>
					<th>Tiêu đề</th>
					<th>Nội dung</th>
					<th>Người đăng</th>
					<th>Hành động</th>

				</tr>
				
				<c:forEach var="tempNews" items="${NEWS_LIST}">
				<!-- set up a link for each class -->
				<c:url var="tempLink" value="NewsControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="newsId" value="${tempNews.newsid}"/>
					
				
				</c:url>
				<!-- set up a link for delete a class -->
				<c:url var="deleteLink" value="NewsControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="newsId" value="${tempNews.newsid}"/>
					
				
				</c:url>
				<tr>
					<td>${tempNews.newsid}</td>
					<td>${tempNews.newstitle}</td>
					<td>${tempNews.newscontent}</td>
					<td>${tempNews.username}</td>
					<td>
						<a href="${tempLink}">Xem chi tiết</a>
						
					</td>
				</tr>
				
			</c:forEach>
			</table>
		
		</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>
</html>