<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Thêm sinh viên</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../add-header.jsp"/>
			
	<div id="container">
		<h3>Thêm sinh viên</h3>
		<form action="../StudentScoreControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD"/>
			
			<table>
			
				
					<tr>
						<td><label>Tên:</label></td>
						<td><input type="text" name="tensv" /></td>
					
					</tr>
					<tr>
						<td><label>Ngày sinh:</label></td>
						<td><input type="text" name="ngaysinh" /></td>
					
					</tr>
					<tr>
						<td><label>Giới tính:</label></td>
						<td><input type="text" name="gioitinh" /></td>
					
					</tr>
					<tr>
						<td><label>Địa chỉ:</label></td>
						<td><input type="text" name="diachi" /></td>
					
					</tr>
<!-- 					<tr> -->
<!-- 						<td><label>Số tín:</label></td> -->
<!-- 						<td><input type="text" name="sotin" /></td> -->
					
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td><label>Điểm tích lũy:</label></td> -->
<!-- 						<td><input type="text" name="diemtichluy" /></td> -->
					
<!-- 					</tr> -->
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Lưu" class="save" /></td>
					
					</tr>
				
				</tbody>
			
			</table>
		
		</form>
		<div style="clear: both;"></div>
		<p>
		<a href="../StudentControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



