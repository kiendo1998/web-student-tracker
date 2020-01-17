<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Thống kê điểm theo bảng điểm</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="../css/style.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


<jsp:include page="../header.jsp"/>
	 
	<div id="container">
		<div id="content">
	
	<input style="float:right" type="submit" value="In bảng điểm" onClick="window.print()"/>
		<h3>Xem bảng điểm</h3>
		<br/>
<!-- 		<form action="ScoreTableControllerServlet" method="GET"> -->
        
<!--                 <input type="hidden" name="command" value="LOAD1" /> -->
<!--                 <input type="button" value="Xem Bảng điểm" class="add-student-button" /> -->
            
<!--             </form> -->
		<form action="ScoreTableControllerServletReport" method="GET">

			<input type="hidden"  name="scoretableId" value="${THE_SCORETABLE.scoretableid}" readonly/>
			
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
			<table>
			
				<tbody>
				<tr>
						<td><label>Mã bảng điểm:</label></td>
						<td><input style="border:none" type="text" name="studentId1" 
						           value="${THE_SCORETABLE.scoretableid}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Tên môn học:</label></td>
						<td><input style="border:none" type="text" name="tensv" 
						           value="${THE_SCORETABLE.tenmh}" readonly/></td>
					
					</tr>
					<tr>
						<td><label>Kỳ học:</label></td>
						<td><input style="border:none" type="text" name="gioitinh" 
						           value="${THE_SCORETABLE.kyhoc}" readonly/></td>
					
					</tr>
				</tbody>
			
			</table>	
					
			
			<input type="hidden" name="command" value="LIST1" />
	<table>
			
				<tr>
					<th>Mã Sinh viên</th>
					<th>Tên Sinh viên</th>
					<th>ĐQT</th>
					<th>Điểm thi</th>
					<th>Điểm kết thúc</th>
					<th>Điểm hệ 4</th>
					<th>Điểm chữ</th>

				</tr>
				
				<c:forEach var="tempScore" items="${SCORE_LIST}">
				
				<tr>
					<td>${tempScore.masv}</td>
					<td>${tempScore.tensv}</td>
					<td>${tempScore.dqt}</td>
					<td>${tempScore.diemthi}</td>
					<td>${tempScore.diemthi*0.7+tempScore.dqt*0.3}</td>
					<td>${tempScore.diemhe4}</td>
					<td>${tempScore.diemchu}</td>
				</tr>
				
			</c:forEach>
			</table>
	
	
		</form>
		<p>
		<a href="ScoreTableControllerServletReport">Trở về</a>
		</p>
				</div>
	
	</div>

<jsp:include page="../my-footer.jsp"/>
</body>
</html>