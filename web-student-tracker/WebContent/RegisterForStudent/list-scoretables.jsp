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
      <h3>Danh sách lớp môn học đã đăng ký</h3>
			<table>
			
				<tr>
					<th>Mã Bảng Điểm</th>
					<th>Mã Môn Học</th>
					<th>Tên Môn Học</th>
					<th>Kỳ Học</th>
					<th>Trạng thái</th>
				</tr>
				
				<c:forEach var="tempScoretable" items="${REGISTED_LIST}">
				<tr>
					<td>${tempScoretable.scoretableid}</td>
					<td>${tempScoretable.mamh}</td>
					<td>${tempScoretable.tenmh}</td>
					<td>${tempScoretable.kyhoc}</td>
					<td>
						Đã đăng ký
					</td>
				</tr>
				
			</c:forEach>
			
			</table>
			<h3>Danh sách lớp môn học chưa đăng ký</h3>
			
			<table>
			<tr>
					<th>Mã Bảng Điểm</th>
					<th>Mã Môn Học</th>
					<th>Tên Môn Học</th>
					<th>Kỳ Học</th>
					<th>Hành động</th>
				</tr>
			<c:forEach var="tempScoretable" items="${TOREGISTED_LIST}">
			<c:url var="tempLink" value="UserServlet">
					<c:param name="command" value="REGISTER"/>
					<c:param name="scoretableId" value="${tempScoretable.scoretableid}"/>
					<c:param name="username" value="${loginedUser.userName}"/>
					
				
				</c:url>
				<tr>
					<td>${tempScoretable.scoretableid}</td>
					<td>${tempScoretable.mamh}</td>
					<td>${tempScoretable.tenmh}</td>
					<td>${tempScoretable.kyhoc}</td>
					<td>
						<a href="${tempLink}">Đăng ký</a>
					</td>
				</tr>
				
			</c:forEach>
				</table>
		</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>