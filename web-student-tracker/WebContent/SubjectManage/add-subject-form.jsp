﻿<!DOCTYPE html>
<html>
<head>
<title>Thêm môn học</title>
<link rel="icon" href="../images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
<link type="text/css" rel="stylesheet" href="../css/style.css">
<link type="text/css" rel="stylesheet"
	href="../css/add-student-style.css">


</head>

<body>
<jsp:include page="../header.jsp"/>
	 
	<div id="container">
		<h3>Thêm môn học</h3>
		<form action="SubjectControllerServlet" method="GET">

			<input type="hidden" name="command" value="SADD" />
			<p style="color: red;">${errorMessage}</p>

			<table>

				<tbody>
					<tr>
						<td><label>Tên môn học:</label></td>
						<td><input type="text" name="tenmh" /></td>

					</tr>
					<tr>
						<td><label>Số tín chỉ:</label></td>
						<td><input type="text" name="sotc" /></td>

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
			<a href="SubjectControllerServlet">Trở về</a>
		</p>

	</div>

	<jsp:include page="../my-footer.jsp" />
</body>

</html>