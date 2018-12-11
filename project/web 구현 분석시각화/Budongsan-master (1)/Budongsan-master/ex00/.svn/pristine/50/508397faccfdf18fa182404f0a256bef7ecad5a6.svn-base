<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>오류 페이지</title>
</head>
<body>
<h3>${exception.message }</h3>
<ul>
<c:forEach items="${exception.stackTrace }" var="stack">
	<li>${stack }</li>
</c:forEach>
</ul>
</body>
</html>