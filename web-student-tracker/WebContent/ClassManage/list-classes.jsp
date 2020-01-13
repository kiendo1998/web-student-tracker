<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quản lý lớp</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


<jsp:include page="../header.jsp"/>
	 
	<div id="container">
		<div id="content">
		<!-- put new button: Add new subject -->
		<input type="button" value="Thêm lớp"
				onclick="window.location.href='ClassManage/add-class-form.jsp';return false;"
				class="add-student-button"
				/>
		 <!--  add a search box -->
            <form action="ClassControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm lớp:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm kiếm" class="add-student-button" />
            
            </form>
            <h3>Danh sách lớp trong hệ thống</h3>
			<table>
			
				<tr>
					<th>Mã lớp</th>
					<th>Tên lớp</th>
					<th>Mã khoa</th>
					<th>Tên khoa</th>
					<th>Sĩ số</th>
					<th>Loại lớp</th>
					<th>Hành động</th>

				</tr>
				
				<c:forEach var="tempClass" items="${CLASS_LIST}">
				<!-- set up a link for each class -->
				<c:url var="tempLink" value="ClassControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="classId" value="${tempClass.malop}"/>
					
				
				</c:url>
				<!-- set up a link for delete a class -->
				<c:url var="deleteLink" value="ClassControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="classId" value="${tempClass.malop}"/>
					
				
				</c:url>
				<tr>
					<td>${tempClass.malop}</td>
					<td>${tempClass.tenlop}</td>
					<td>${tempClass.makhoa}</td>
					<td>${tempClass.tenkhoa}</td>
					<td>${tempClass.siso}</td>
					<td>${tempClass.loailop}</td>
					<td>
						<a href="${tempLink}">Cập nhật</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Bạn có chắc muốn xóa lớp này không?'))) return false">
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