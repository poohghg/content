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
<title>게시판 리스트</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
.data:hover {
	background: #eee; cursor: pointer;
}
</style> 

<script type="text/javascript">
	$(document).ready(function(){
		
		// 버튼 이벤트 처리
		// 글쓰기 버튼
		$("#writeBtn").click(function(){
			location = "write.do";
		});
		
		// 새로고침 버튼
		$("#reloadBtn").click(function(){
			location.reload();
		});
		
		// 게시글에 대한 tr 이벤트 처리
		$(".data").click(function(){
			var no = $(this).find("td:first").text();
			location = "view.do?no="+no // 글번호 전달
					+"&page=${cri.page}" // 페이지 전달한다.
					+"&perPageNum=${cri.perPageNum}"
					+"&searchType=${cri.searchType}"
					+"&keyword=${cri.keyword}"
					; // 페이지 당 글수 전달
		});
		
		// 표시하는 글수를 바꾸면 이벤트 처리를 해서 다시 리스트를 불러온다.
		$("#perPageNum").change(function(){
// 			alert("select change!");
			location="list.do?"
				+"page=1" // 1페이지 전달한다.
				+"&perPageNum="+$("#perPageNum").val()
				+"&searchType=${cri.searchType}"
				+"&keyword=${cri.keyword}"
				; // 페이지 당 글수 전달
		});
		
		// 글등록 후 경고
		${(msg == "writeOK")?"alert('글등록이 완료되었습니다.');":""}
		// 글삭제 후 경고
		${msg == "deleteOK"?"alert('글삭제가 완료되었습니다.');":""}
	});
</script>


</head>

<body>
<img src="/resources/gurrn2.jpg" height="400" width="700">
<img src="/resources/gurrn3.jpg" height="400" width="700">
<img src="/resources/gurrn.jpg" height="400" width="700">
<img src="/resources/gurrn1.jpg" height="400" width="700">
<img src="/resources/gurrn5.jpg" height="400" width="700">
<img src="/resources/gurrn4.jpg" height="700" width="1200">
</body>

</html>