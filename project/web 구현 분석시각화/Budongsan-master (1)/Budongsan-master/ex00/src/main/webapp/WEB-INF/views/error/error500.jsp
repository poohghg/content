<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>500서버 에러</title>
</head>
<body>


<div class="container">
  <div class="jumbotron">
    <h1>500에러</h1>      
    <p>서버오류입니다. 부동산 닷컴에 문의하세요. johnmor78@gmail.com</p>
  </div> 
</div>

<div class="container">
	<ol>
	<c:forEach items="${exception.getStackTrace() }" var="stack">
		 
			<li>${stack.toString() }</li>
		
	</c:forEach>
	</ol>
</div>

</body>
</html>