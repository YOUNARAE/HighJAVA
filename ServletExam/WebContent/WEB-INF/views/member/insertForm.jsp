<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규회원 등록</title>
</head>
<body>
	<form action="insert.do" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>I D:</td>
				<td><input type="text" name="memId" value=""></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="memName" value=""></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel" value=""></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr" ></textarea></td>
			</tr>
			<tr>
				<td>첨부파일:</td>
				<td><input type="file" name="atchFile" ></td>
			</tr>
		</table>
		<button>회원등록</button>
	</form>
</body>
</html>