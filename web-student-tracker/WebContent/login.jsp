<html>
	<head>
		<title>Đăng nhập</title>
		<meta charset="UTF-8"> 
		<link rel="icon" href="images/LOGO_DHXD.png" type="image/gif" sizes="16x16">
		<link type="text/css" rel="stylesheet" href="css/login.css">
	</head>
	<body>
	<div class="wrapper fadeInDown">
	  <div id="formContent">
	    <!-- Tabs Titles -->
	    <h2 class="active"> Đăng nhập </h2>
	    <!-- Icon -->
	    <div class="fadeIn first">
	      <img src="images/LOGO_DHXD.png" id="icon" alt="User Icon" />
	    </div>
	
	    <!-- Login Form -->
	    <form action="StudentControllerServlet" method="GET">
	    <input type="hidden" name="command" value="login"/>
	    <input type="text"  class= "noborder" name="warning" placeholder="Mời nhập thông tin" value="${Warning}" readonly/>
	      <input type="text" class="fadeIn second" name="username" placeholder="TÊN ĐĂNG NHẬP">
	      <input type="password" type="text" class="fadeIn third" name="userpass" placeholder="MẬT KHẨU">
	      <input type="submit" class="fadeIn fourth" value="Đăng nhập">
	    </form>
	
	    <!-- Remind Passowrd -->
	    <div id="formFooter">
	      <a class="underlineHover" href="#">Quên mật khẩu?</a>
	    </div>
	
	  </div>
	</div>
	</body>
</html>
