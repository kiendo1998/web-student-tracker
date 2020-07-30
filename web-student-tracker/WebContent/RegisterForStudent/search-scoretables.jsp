<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quản lý bảng điểm</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


	<jsp:include page="../header.jsp"/>
		
	
	<div id="container">
		<div id="content">

		 <!--  add a search box -->
            <form action="UserServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm theo tên môn học:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm kiếm" class="add-student-button" />
            
            </form>
            <h3>Kết quả tìm kiếm</h3>
			<table>
			
				<tr>
					<th>Mã Bảng Điểm</th>
					<th>Mã Môn Học</th>
					<th>Tên Môn Học</th>
					<th>Kỳ Học</th>
				</tr>
				
				<c:forEach var="tempScoretable" items="${SEARCH_LIST}">
				<tr>
					<td>${tempScoretable.scoretableid}</td>
					<td>${tempScoretable.mamh}</td>
					<td>${tempScoretable.tenmh}</td>
					<td>${tempScoretable.kyhoc}</td>
				</tr>
			</c:forEach>
			
			</table>
      
		</div>
	<p>
		<a href="UserServlet?command=DKMH&username=${loginedUser.userName}">Trở về</a>
		</p>
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>