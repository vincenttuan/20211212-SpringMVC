<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order OK</title>
</head>
<body>
	name：${ name } <p />
	price：${ price } <p />
	qty：${ qty } <p />
	<hr />
	requestScope.name：${ requestScope.name } <p />
	requestScope.price：${ requestScope.price } <p />
	requestScope.qty：${ requestScope.qty } <p />
	<hr />
	
</body>
</html>