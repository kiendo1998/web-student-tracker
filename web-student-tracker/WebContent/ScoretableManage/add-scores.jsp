<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Thêm điểm sinh viên</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="../css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


<jsp:include page="../header.jsp"/>
	 
	<div id="container">
		<div id="content">
	<br/>
		<h3>Thêm điểm sinh viên</h3>
		<p style="color: red;">${errorMessage}</p>
<!-- 		<form action="ScoreTableControllerServlet" method="GET"> -->
        
<!--                 <input type="hidden" name="command" value="LOAD1" /> -->
<!--                 <input type="button" value="Xem Bảng điểm" class="add-student-button" /> -->
            
<!--             </form> -->
		<form action="ScoreTableControllerServlet" method="GET">

			<input type="hidden" name="command" value="ADD1" />
			<label>Mã bảng điểm:</label><input style="border:none"  name="scoretableId" value="${THE_SCORETABLE.scoretableid}" readonly/>
			<table>

				<tbody>
<!-- 					<tr> -->
<!-- 						<td><label>Môn:</label></td> -->
<!-- 						<td><select name="tenmh"> -->
<%-- 		<c:forEach var="tempScore"  items="${SCORE_LIST}"><option>${tempScore.tenmh}</option></c:forEach> --%>
	
<!-- 	</select></td> -->

<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td><label>Lớp:</label></td> -->
<!-- 						<td><select name="tenlop"> -->
<%-- 		<c:forEach var="tempScore"  items="${SCORE_LIST}"><option>${tempScore.tenlop}</option></c:forEach> --%>
	
<!-- 	</select></td> -->

<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td><label>Kỳ học:</label></td> -->
<!-- 						<td><select name="kyhoc"> -->
<%-- 		<c:forEach var="tempScore"  items="${SCORE_LIST}"><option>${tempScore.kyhoc}</option></c:forEach> --%>
	
<!-- 	</select></td> -->

<!-- 					</tr> -->
				
					<tr>
						<td><label>Mã sinh viên:</label></td>
						<td><input type="text" name="masv" /></td>

					</tr>
					<tr>
						<td><label>Điểm quá trình:</label></td>
						<td><input type="text" name="dqt" /></td>

					</tr>
					<tr>
						<td><label>Điểm thi:</label></td>
						<td><input type="text" name="diemthi" /></td>

					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Lưu" class="save" /></td>

					</tr>

				</tbody>

			</table>
	
	


			<input type="hidden" name="command" value="LIST1" />
	<table>
			
				<tr>
					<th>Mã Sinh viên</th>
					<th>ĐQT</th>
					<th>Điểm thi</th>

				</tr>
				
				<c:forEach var="tempScore" items="${SCORE_LIST}">
				
				<tr>
					<td>${tempScore.masv}</td>
					<td>${tempScore.dqt}</td>
					<td>${tempScore.diemthi}</td>
				</tr>
				
			</c:forEach>
			</table>
	
	
		</form>
		<p>
		<a href="ScoreTableControllerServlet">Trở về</a>
		</p>
				</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>
</html>