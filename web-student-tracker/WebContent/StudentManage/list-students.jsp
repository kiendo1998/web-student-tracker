<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quản lý sinh viên</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


	<jsp:include page="../header.jsp"/>
		
	
	<div id="container">
		<div id="content">
		<!-- put new button: Add new student -->
		<input type="button" value="Thêm Sinh Viên"
				onclick="window.location.href='StudentManage/add-student-form.jsp';return false;"
				class="add-student-button"
				/>
		 <!--  add a search box -->
            <form action="StudentControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                <font color="black">Tìm kiếm sinh viên:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm Kiếm" class="add-student-button" />
            
            </form>
      <h3>Danh sách sinh viên</h3>
			<table>
			
				<tr>
					<th>Mã SV</th>
					<th>Tên SV</th>
					<th>Ngày Sinh</th>
					<th>Giới tính</th>
					<th>Địa chỉ</th>
					<th>Mã lớp</th>
					<th>Số điện thoại</th>
					<th>Hành động</th>
				</tr>
				
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				<!-- set up a link for each student -->
				<c:url var="tempLink" value="StudentControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="studentId" value="${tempStudent.masv}"/>
					
				
				</c:url>
				<!-- set up a link for delete a student -->
				<c:url var="deleteLink" value="StudentControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="studentId" value="${tempStudent.masv}"/>
					
				
				</c:url>
				<tr>
					<td>${tempStudent.masv}</td>
					<td>${tempStudent.tensv}</td>
					<td>${tempStudent.ngaysinh}</td>
					<td>${tempStudent.gioitinh}</td>
					<td>${tempStudent.diachi}</td>
					<td>${tempStudent.malop}</td>
					<td>${tempStudent.sdt}</td>
					<td>
						<a href="${tempLink}">Cập nhật</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Bạn có chắc muốn xóa sinh viên này không?'))) return false">
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