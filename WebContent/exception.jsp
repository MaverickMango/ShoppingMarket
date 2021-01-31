<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	out.println(exception.getMessage());
	if(exception.getMessage() == null) {//没有获取到购物车
		//请登录
		response.sendRedirect("Login.jsp");
	}
	%>
</body>
</html>