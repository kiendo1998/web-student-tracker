<!DOCTYPE html>
<html>
<head>
	<title>Sửa thông tin sinh viên</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Sửa thông tin sinh viên</h3>
		<form action="StudentControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden"  name="studentId" value="${THE_STUDENT.masv}"/>
			
			<table>
			
				<tbody>
					<tr>
						<td><label>Tên:</label></td>
						<td><input type="text" name="tensv" 
						           value="${THE_STUDENT.tensv}"/></td>
					
					</tr>
					<tr>
						<td><label>Ngày sinh:</label></td>
						<td><input type="text" name="ngaysinh" 
						           value="${THE_STUDENT.ngaysinh}"/></td>
					
					</tr>
					<tr>
						<td><label>Giới tính:</label></td>
						<td><input type="text" name="gioitinh" 
						           value="${THE_STUDENT.gioitinh}"/></td>
					
					</tr>
					<tr>
						<td><label>Địa chỉ:</label></td>
						<td><input type="text" name="diachi" 
						           value="${THE_STUDENT.diachi}"/></td>
					
					</tr>
					<tr>
						<td><label>Số tín:</label></td>
						<td><input type="text" name="sotin" 
						           value="${THE_STUDENT.sotin}"/></td>
					
					</tr>
					<tr>
						<td><label>Điểm tích lũy:</label></td>
						<td><input type="text" name="diemtichluy" 
						           value="${THE_STUDENT.diemtichluy}"/></td>
					
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Lưu" class="save" /></td>
					
					</tr>
				
				</tbody>
			
			</table>
		
		</form>
		<div style="clear: both;"></div>
		<p>
		<a href="StudentControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



