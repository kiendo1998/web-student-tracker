<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quản lý môn học</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


<jsp:include page="../header.jsp"/>
	 
	<div id="container">
		<div id="content">
		<!-- put new button: Add new subject -->
		<input type="button" value="Thêm môn học"
				onclick="window.location.href='SubjectControllerServlet?command=SHOW';return false;"
				class="add-student-button"
				/>
		 <!--  add a search box -->
            <form action="SubjectControllerServlet" method="GET">
        
                <input type="hidden" name="command" value="SSEARCH" />
            
                <font color="black">Tìm kiếm môn học:</font><input type="text" name="theSearchName"/>
                
                <input type="submit" value="Tìm kiếm" class="add-student-button" />
            
            </form>
            <h3>Danh sách môn học</h3>
            <p style="color: red;">${errorMessage}</p>
			<table>
			
				<tr>
					<th>Mã môn học</th>
					<th>Tên môn học</th>
					<th>Số tín chỉ</th>
					<th>Hành động</th>

				</tr>
				
				<c:forEach var="tempSubject" items="${SUBJECT_LIST}">
				<!-- set up a link for each subject -->
				<c:url var="tempLink" value="SubjectControllerServlet">
					<c:param name="command" value="SLOAD"/>
					<c:param name="subjectId" value="${tempSubject.mamh}"/>
					
				
				</c:url>
				<!-- set up a link for delete a student -->
				<c:url var="deleteLink" value="SubjectControllerServlet">
					<c:param name="command" value="SDELETE"/>
					<c:param name="subjectId" value="${tempSubject.mamh}"/>
					
				
				</c:url>
				<tr>
					<td>${tempSubject.mamh}</td>
					<td>${tempSubject.tenmh}</td>
					<td>${tempSubject.sotc}</td>

					<td>
						<a href="${tempLink}">Cập nhật</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Bạn có chắc muốn xóa môn học này không?'))) return false">
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