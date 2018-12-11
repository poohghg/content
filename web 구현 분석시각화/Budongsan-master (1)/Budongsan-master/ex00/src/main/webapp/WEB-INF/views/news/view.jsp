<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글보기</title>

<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

<script type="text/javascript"
 src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style type="text/css">
th {
width:80px; 
}
strong{
	size: 100cm;
}

</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		var no = ${dto.no};
		
		// 버튼 이벤트 처리
		// 수정 버튼
		// 리스트 버튼
		$("#listBtn").click(function(){
			location="list.do";
			// 폼에서 글번호 제거
// 			$("#no").attr("disabled","disabled");
// 			$("#dataForm").attr("action", "list");
// 			$("#dataForm").submit();
		});

	}); // end of document.ready()
	
	
	
</script>

</head>
<body>
<!-- 넘겨야할 데이터를 uri 뒤에 붙이지 않고 form 태그 사용해서 넘기기 :update, list
		update : 글번호, 한페이지의 데이터 갯수, 페이지, 검색 타입, 검색어
		list : 한페이지의 데이터 갯수, 페이지, 검색 타입, 검색어
-->
<form id="dataForm">
	<input name="no" value="${param.no }" type="hidden" id="no">
	<input name="page" value="${param.page }" type="hidden">
	<input name="perPageNum" value="${param.perPageNum }" type="hidden">
	<input name="searchType" value="${param.searchType }" type="hidden">
	<input name="keyword" value="${param.keyword }" type="hidden">
</form>
<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">게시판 글보기</div>
    <div class="panel-body">
      <table class="table" >
	    <tbody>
	      <tr>
	      	<th class="nowrap">제목</th>
	        <td>${dto.title }</td>
	      </tr>
	      <tr>
	      	
	      </tr>
	      <tr>
	      	<th>작성자</th>
	        <td>${dto.writer }</td>
	      </tr>
	      <tr>
	      	<th>작성일</th>
<%-- 	        <td><fmt:formatDate value="${dto.writeDate }"  --%>
<%-- 	        pattern="yyyy-MM-dd HH:mm:ss"/></td> --%>
			<td>${dto.writeDate}
	      </tr>
	      <tr>
	      	<th>조회수</th>
	        <td>${dto.hit }</td>
	      </tr>
			<tr>
	      <th>내용</th>
	       <td><img src="${dto.img}" width="400px" height="400px" align="left" style="margin: 30px"><strong>${dto.title}</strong><br><br><br>${dto.content }</td>
	        </tr>
	    </tbody>
	    <tfoot>
	    	<tr>
	    		<td colspan="2">
	    		
	    			<button id="listBtn">리스트</button>
	    		</td>
	    	</tr>
	    </tfoot>
	  </table>
	  <div>
	  	
	  </div>
    </div>
  </div>
</div>

</body>
</html>