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
		<!-- put new button: Add new student -->
		<input type="button" value="Thêm Bảng Điểm"
				onclick="window.location.href='ScoreTableControllerServlet?command=SHOW';return false;"
				class="add-student-button"
				/>
		 <!--  add a search box -->
            <form action="ScoreTableControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm theo tên môn học:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm kiếm" class="add-student-button" />
            
            </form>
      <h3>Danh sách bảng điểm</h3>
			<table>
			
				<tr>
					<th>Mã Bảng Điểm</th>
					<th>Mã Môn Học</th>
					<th>Tên Môn Học</th>
					<th>Kỳ Học</th>
					<th>Hành động</th>
				</tr>
				
				<c:forEach var="tempScoretable" items="${SCORETABLE_LIST}">
				<!-- set up a link for each student -->
				<c:url var="tempLink" value="ScoreTableControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="scoretableId" value="${tempScoretable.scoretableid}"/>
					
				
				</c:url>
				<!-- set up a link for delete a student -->
				<c:url var="deleteLink" value="ScoreTableControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="scoretableId" value="${tempScoretable.scoretableid}"/>
					
				
				</c:url>
				<tr>
					<td>${tempScoretable.scoretableid}</td>
					<td>${tempScoretable.mamh}</td>
					<td>${tempScoretable.tenmh}</td>
					<td>${tempScoretable.kyhoc}</td>
					<td>
<%-- 						<a href="${tempLink}">Cập nhật</a> --%>
<!-- 						| -->
						<a href="${tempLink}">Nhập điểm</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Xóa Bảng điểm ?'))) return false">
						Xóa</a>
					</td>
				</tr>
				
			</c:forEach>
			</table>
		</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>