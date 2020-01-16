<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Quản lý điểm theo sinh viên</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
	<input style="float:right" type="submit" value="In bảng điểm" onClick="window.print()"/>
		<h3>Bảng điểm theo sinh viên</h3>
		
		<h4>Thông tin sinh viên</h4>
		<form action="StudentScoreControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden"  name="studentId" value="${THE_STUDENT.masv}"/>
			
			<table>
			
				<tbody>
				<tr>
						<td><label>Mã sinh viên:</label></td>
						<td><input style="border:none" type="text" name="studentId1" 
						           value="${THE_STUDENT.masv}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Họ và Tên:</label></td>
						<td><input style="border:none" type="text" name="tensv" 
						           value="${THE_STUDENT.tensv}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Ngày sinh:</label></td>
						<td><input style="border:none" type="text" name="ngaysinh" 
						           value="${THE_STUDENT.ngaysinh}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Giới tính:</label></td>
						<td><input style="border:none" type="text" name="gioitinh" 
						           value="${THE_STUDENT.gioitinh}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Địa chỉ:</label></td>
						<td><input style="border:none" type="text" name="diachi" 
						           value="${THE_STUDENT.diachi}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Mã lớp:</label></td>
						<td><input style="border:none" type="text" name="malop" 
						           value="${THE_STUDENT.malop}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Số điện thoại:</label></td>
						<td><input style="border:none" type="text" name="sdt" 
						           value="${THE_STUDENT.sdt}" readonly/></td>
					
					</tr>
				
				</tbody>
			
			</table>
			
			<h4>Thông tin điểm</h4>
					<input type="hidden" name="command" value="LIST1" />
	<table>
			
				<tr>
					<th>Mã môn học</th>
					<th>Tên môn học</th>
					<th>Điểm Quá trình</th>
					<th>Điểm thi</th>
					<th>Kỳ học</th>
					<th>Hành động</th>

				</tr>
				
				<c:forEach var="tempScore" items="${SCORE_LIST}">
				<!-- set up a link for each score -->
				<c:url var="tempLink" value="StudentScoreControllerServlet">
					<c:param name="command" value="LOAD1"/>
					<c:param name="studentId" value="${tempScore.masv}"/>
					<c:param name="scoreId" value="${tempScore.id}"/>
					
				
				</c:url>
				<!-- set up a link for delete a student -->
				<c:url var="deleteLink" value="StudentScoreControllerServlet">
					<c:param name="command" value="DELETE1"/>
					<c:param name="studentId" value="${tempScore.masv}"/>
					<c:param name="scoreId" value="${tempScore.id}"/>
					
				
				</c:url>
				<tr>
					<td>${tempScore.mamh}</td>
					<td>${tempScore.tenmh}</td>
					<td>${tempScore.dqt}</td>
					<td>${tempScore.diemthi}</td>
					<td>${tempScore.kyhoc}</td>
					<td>
					<a href="${tempLink}">Cập nhật</a>
						|
						<a href="${deleteLink}"
						onclick="if (!(confirm('Bạn có chắc muốn xóa điểm này?'))) return false">
						Xóa</a>
					</td>
				</tr>
				
			</c:forEach>
			</table>
		</form>
<!-- 		<div style="clear: both;"></div> -->
		<p>
		<a href="StudentScoreControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



