<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<title>Insert title here</title>

<script type="text/javascript">
$(document).ready(function(){
	// 버튼 이벤트 처리
	// 수정 버튼
	$("#updateBtn").click(function(){
		$("#dataForm").attr("action","update.do");
		// form.submit() 넘긴다.
		$("#dataForm").submit();
	});
	// 회원 탈퇴버튼
	$("#deleteBtn").click(function(){
		var con = confirm("정말로 탈퇴시킵니까?");
		if(con == true){
			$("#dataForm").attr("action","secession.do");
			// form.submit() 넘긴다.
			$("#dataForm").submit();			
		}
	});

});


</script>

</head>
<body>
<form id="dataForm">
	<input name="email" value="${param.email }" type="hidden" id="email">
	<input name="page" value="${param.page }" type="hidden">
	<input name="perPageNum" value="${param.perPageNum }" type="hidden">
	<input name="searchType" value="${param.searchType }" type="hidden">
	<input name="keyword" value="${param.keyword }" type="hidden">
</form>
<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">게시판 글보기</div>
    <div class="panel-body">
      <table class="table">
	    <tbody>
	    
	      <tr>
	      	<th>이름</th>
	        <td>${dto.name }</td>
	      </tr>
	      <tr>
	      	<th>아이디</th>
	        <td>${dto.email }</td>
	      </tr>
	      <tr>
	      	<th>사업자명</th>
	        <td>${dto.seller_name }</td>
	      </tr>
	      <tr>
	      	<th>나이</th>
	        <td>${dto.age }</td>
	      </tr>
	      <tr>
	      	<th>주소</th>
	        <td>${dto.addr }</td>
	      </tr>
	      <tr>
	      	<th>등급</th>
	        <td>${dto.grade }</td>
	      </tr>
	      <tr>
	      	<th>보유잔액</th>
	        <td>${dto.money }</td>
	      </tr>
	     
	    </tbody>
	    <tfoot>
	    	<tr>
	    		<td colspan="2">
	    			<button id="updateBtn">회원정보 수정</button>
	    			<button id="deleteBtn">회원 탈퇴</button>
	    		</td>
	    	</tr>
	    </tfoot>
	  </table>
	
	
    </div>
  </div>
</div>


  

</body>
</html>