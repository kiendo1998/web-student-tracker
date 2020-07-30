<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Thêm lớp</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Thêm lớp</h3>
		<p style="color: red;">${errorMessage}</p>
		<form action="ClassControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="ADD"/>
			
			<table>
			
				
					<tr>
						<td><label>Tên lớp:</label></td>
						<td><input type="text" name="tenlop" /></td>
					
					</tr>
					<tr>
						<td><label>Mã khoa:</label></td>
						<td><input type="text" name="makhoa" /></td>
					
					</tr>
					<tr>
						<td><label>Sĩ số:</label></td>
						<td><input type="text" name="siso" /></td>
					
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
		<a href="ClassControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



