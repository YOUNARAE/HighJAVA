<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	MemberVO mv = (MemberVO)request.getAttribute("mv");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 변경</title>
</head>
<body>
	<form action="update.do" method="post">
		<input type="hidden" name="memId" value="<%=mv.getMemId() %>">
		<table>
			<tr>
				<td>I D:</td>
				<td><%=mv.getMemId() %></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="memName" value="<%=mv.getMemName() %>"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel" value="<%=mv.getMemTel() %>"></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr" ><%=mv.getMemAddr() %></textarea></td>
			</tr>
		</table>
		<button>회원정보 수정</button>
	</form>
</body>
</html>