﻿<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            <form action="ScoreTableControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm theo tên lớp:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm kiếm" class="add-student-button" />
            
            </form>
      <h3>Danh sách bảng điểm</h3>
			<table>
			
				<tr>
					<th>Mã Bảng Điểm</th>
					<th>Mã Môn Học</th>
					<th>Tên Môn Học</th>
					<th>Mã Lớp</th>
					<th>Tên Lớp</th>
					<th>Kỳ Học</th>
					<th>Hành động</th>
				</tr>
				
				<c:forEach var="tempScoretable" items="${SCORETABLE_LIST}">
				<!-- set up a link for each scoretable -->
				<c:url var="tempLink" value="ScoreTableControllerServletReport">
					<c:param name="command" value="LOAD"/>
					<c:param name="scoretableId" value="${tempScoretable.scoretableid}"/>
					
				
				</c:url>
				<tr>
					<td>${tempScoretable.scoretableid}</td>
					<td>${tempScoretable.mamh}</td>
					<td>${tempScoretable.tenmh}</td>
					<td>${tempScoretable.malop}</td>
					<td>${tempScoretable.tenlop}</td>
					<td>${tempScoretable.kyhoc}</td>
					<td>
<%-- 						<a href="${tempLink}">Cập nhật</a> --%>
<!-- 						| -->
						<a href="${tempLink}">Xem, In bảng điểm</a>
					</td>
				</tr>
				
			</c:forEach>
			</table>
		</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>