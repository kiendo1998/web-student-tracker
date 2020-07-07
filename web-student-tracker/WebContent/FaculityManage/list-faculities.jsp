<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quản lý khoa</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


	<jsp:include page="../header.jsp"/>
		
	
	<div id="container">
		<div id="content">
		<!-- put new button: Add new student -->
		<input type="button" value="Thêm khoa"
				onclick="window.location.href='FaculityControllerServlet?command=SHOW';return false;"
				class="add-student-button"
				/>
		 <!--  add a search box -->
            <form action="FaculityControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm khoa:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm Kiếm" class="add-student-button" />
            
            </form>
      <h3>Danh sách khoa</h3>
			<table>
			
				<tr>
					<th>Mã khoa</th>
					<th>Tên khoa </th>
					<th>Số điện thoại </th>
					<th>Hành động </th>
				</tr>
				
				<c:forEach var="tempFaculity" items="${FACULITY_LIST}">
				<!-- set up a link for each student -->
				<c:url var="tempLink" value="FaculityControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="faculityId" value="${tempFaculity.makhoa}"/>
					
				
				</c:url>
				<!-- set up a link for delete a student -->
				<c:url var="deleteLink" value="FaculityControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="faculityId" value="${tempFaculity.makhoa}"/>
					
				
				</c:url>
				<tr>
					<td>${tempFaculity.makhoa}</td>
					<td>${tempFaculity.tenkhoa}</td>
					<td>${tempFaculity.sdt}</td>
					<td>
						<a href="${tempLink}">Cập nhật</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Bạn có chắc muốn xóa khoa này không?'))) return false">
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