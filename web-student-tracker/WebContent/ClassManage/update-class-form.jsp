<!DOCTYPE html>
<html>
<head>
	<title>Sửa thông tin lớp</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Sửa thông tin lớp</h3>
		<form action="ClassControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden" name="classId" value="${THE_CLASS.malop}"/>
			
			<table>
			
				<tbody>
					<tr>
						<td><label>Tên lớp:</label></td>
						<td><input type="text" name="tenlop" 
						           value="${THE_CLASS.tenlop}"/></td>
					
					</tr>
					<tr>
						<td><label>Mã khoa:</label></td>
						<td><input type="text" name="makhoa" 
						           value="${THE_CLASS.makhoa}"/></td>
					
					</tr>
					<tr>
						<td><label>Sĩ số:</label></td>
						<td><input type="text" name="siso" 
						           value="${THE_CLASS.siso}"/></td>
					
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
		<a href="ClassControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



