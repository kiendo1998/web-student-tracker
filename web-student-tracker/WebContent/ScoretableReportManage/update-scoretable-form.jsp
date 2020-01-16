<!DOCTYPE html>
<html>
<head>
	<title>Update Student</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Sửa thông tin bảng điểm</h3>
		<form action="ScoreTableControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE"/>
				<input type="hidden" name="studentId" value="${THE_SCORETABLE.scoretableid}"/>
			
			<table>
			
				<tbody>
					<tr>
						<td><label>Mã môn học:</label></td>
						<td><input type="text" name="maMH" 
						           value="${THE_SCORETABLE.mamh}"/></td>
					
					</tr>
					<tr>
						<td><label>Kỳ Học:</label></td>
						<td><input type="text" name="kyHoc" 
						           value="${THE_SCORETABLE.kyhoc}"/></td>
					
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
		<a href="ScoreTableControllerServlet">Trở về</a>
		</p>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



