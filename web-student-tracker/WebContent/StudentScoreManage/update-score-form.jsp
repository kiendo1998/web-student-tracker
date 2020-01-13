<!DOCTYPE html>
<html>
<head>
	<title>Sửa thông tin điểm sinh viên</title>
	<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
	<link type="text/css" rel="stylesheet" href="../css/style.css">
	<link type="text/css" rel="stylesheet" href="../css/add-student-style.css">
	

</head>

<body>
		<jsp:include page="../header.jsp"/>
			
	<div id="container">
		<h3>Sửa thông tin điểm sinh viên</h3>
		<form action="StudentScoreControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE1"/>
				<input type="hidden"  name="studentId" value="${THE_STUDENT1.masv}"/>
				<input type="hidden"  name="scoreId" value="${THE_STUDENT1.scoreid}"/>
			<table>
			
				<tbody>
					<tr>
						<td><label>Điểm quá trình:</label></td>
						<td><input type="text" name="dqt" 
						           value="${THE_STUDENT1.dqt}"/></td>
					
					</tr>
					<tr>
						<td><label>Điểm thi</label></td>
						<td><input type="text" name="diemthi" 
						           value="${THE_STUDENT1.diemthi}"/></td>
					
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Lưu" class="save" /></td>
					
					</tr>
				
				</tbody>
			
			</table>
		
		</form>
		<div style="clear: both;"></div>
<!-- 		<p> -->
<!-- 		<a href="StudentScoreControllerServlet?command=LOAD">Trở về</a> -->
<!-- 		</p> -->
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>

</html>



