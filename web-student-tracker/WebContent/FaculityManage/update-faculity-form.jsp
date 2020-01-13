<!DOCTYPE html>
<html>
<head>
	<title>Sửa thông tin khoa</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Sửa thông tin khoa</h3>
		<form action="FaculityControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden"  name="faculityId" value="${THE_FACULITY.makhoa}"/>
			
			<table>
			
				<tbody>
					<tr>
						<td><label>Tên:</label></td>
						<td><input type="text" name="tenkhoa" 
						           value="${THE_FACULITY.tenkhoa}"/></td>
					
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
		<a href="FaculityControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



