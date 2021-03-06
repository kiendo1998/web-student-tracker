﻿<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Web quản lý sinh viên</title>
<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">

<link type="text/css" rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<!-- using jstl tags instead of using jsp scriptlet -->


<body>


	<div id="wrapper">
		<div id="header" >
			<div class="tieude"><a href="login" class="w3-button w3-black">Đăng nhập</a></div>
			<div class="tieude"><a href="logout" class="w3-button w3-black">Đăng xuất</a></div>
			<a href="../StudentControllerServlet"><img src="../images/LOGO_DHXD.png" width="80px" height="80px" class="tieude1" /></a>
			
			 <div class="tieude2">Trường đại học Xây Dựng</div>
			 <p style="color:white;margin-top:0px;" >55 Giải Phóng - Hai Bà Trưng - Hà Nội <br/> SĐT: 0986579099</p>
			 <br/>
		
		</div>
	 <ul>
	 <li><a class="active" href="StudentControllerServlet">Trang chủ</a></li>
  <li><a class="active" href="../StudentControllerServlet">Quản lý sinh viên</a></li>
  <li><a href="../SubjectControllerServlet">Quản lý môn học</a></li>
  <li class="dropdown">
    <a href="#" class="dropbtn">Quản lý điểm theo bảng điểm</a>
    <div class="dropdown-content">
      <a href="../ScoreTableControllerServlet">Nhập điểm theo bảng điểm</a>
      <a href="../ScoreTableControllerServletReport">Thống kê điểm theo bảng điểm</a>
    </div>
   
  </li>
    <li class="dropdown">
    <a href="../StudentScoreControllerServlet" class="dropbtn">Quản lý điểm theo sinh viên</a>
<!--       <div class="dropdown-content"> -->
<!--       <a href="../xemdiem.jsp">Bảng điểm sinh viên</a> -->
<!--       <a href="#">Bảng điểm bảo lưu</a> -->
<!--       </div> -->
   
  </li>
<li><a class="active" href="../ClassControllerServlet">Quản lý lớp</a></li>
<li><a class="active" href="../FaculityControllerServlet">Quản lý Khoa</a></li>
<li><a class="active" href="userInfo">Thông tin người dùng</a></li>
</ul>
	</div>
</body>	