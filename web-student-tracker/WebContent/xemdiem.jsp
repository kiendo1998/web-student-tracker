<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
        <title>Xem điểm</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp"/>
	
            <div class="thongtinsinhvien" align="center">
                    
Mã SV <input type="text" name="MaSV" />
	<br/><br/>
Kỳ Học	<select class="country"name="country">
		<option>Kỳ 1 nam 2018</option>
		<option>Kỳ 2 nam 2019</option>
		<option>Kỳ 3 nam 2019</option>
	
	</select>
	<br/><br/>
		<input class="country1"type="submit" value="Xem"/>
<p>
Mã sinh viên	128961 <br/>
Tên sinh viên	Ðỗ Trung Kiên<br/>
Phái	Nam<br/>
Ngày sinh	10/07/98<br/>
Noi sinh	<br/>
Lớp	61PM1( Ðại học chính quy Khoa Công Nghệ thông tin)<br/>
Ngành	Tin học<br/>
Khoa	Khoa CN Thông Tin<br/>
Hệ đào tạo	Ðại học hệ chính quy<br/>
Khóa học	2016-2021<br/>
    </p>

 </div>
<table>
			
				<tr>
					<th>STT</th>
					<th>Tên Môn</th>
					<th>Số Tín chỉ</th>
					<th>ÐQT</th>
					<th>Ðiểm Thi</th>
					<th>Ðiểm kết thúc</th>
					<th>Thao tác</th>
				</tr>
				<tr>
					<th>1</th>
					<th>Giảitích 1</th>
					<th>3</th>
					<th>8</th>
					<th>8</th>
					<th>8</th>
					<th><input type="submit" value="Sửa,xóa"/></th>
				</tr>
				<tr>
					<th>2</th>
					<th>Ðại số</th>
					<th>2</th>
					<th>9</th>
					<th>9</th>
					<th>9</th>
					<th><input type="submit" value="Sửa,xóa"/></th>
					
				</tr>
				<tr>
					<th>3</th>
					<th>Toán rời rạc</th>
					<th>3</th>
					<th>10</th>
					<th>10</th>
					<th>10</th>
					<th><input type="submit" value="Sửa,xóa"/></th>
				</tr>
				
			
			</table>
<jsp:include page="/my-footer.jsp"/>
</body>
</html>