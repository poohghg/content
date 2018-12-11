<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script type="text/javascript">

$(document).ready(function(){
	// 회원 탈퇴버튼
	$("#deleteBtn").click(function(){
		var con = confirm("정말로 탈퇴하십니까?");
		if(con == true){
			$("#dataForm").attr("action","secession.do");
			$("#dataForm").submit();			
		}
	});
	
	//회원정보 수정 
	$("#modifyBtn").click(function(){
		$("#dataForm").attr("action","modify.do");
		$("#dataForm").submit();
	});
	
});


</script>

</head>
<body>

<form id="dataForm">
	<input name="email" value="${login.email }" type="hidden" id="email">
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
	        <td>${login.name }</td>
	      </tr>
	      <tr>
	      	<th>아이디</th>
	        <td>${login.email }</td>
	      </tr>
	      <tr>
	      	<th>사업자명</th>
	        <td>${login.seller_name }</td>
	      </tr>
	      <tr>
	      	<th>나이</th>
	        <td>${login.age }</td>
	      </tr>
	      <tr>
	      	<th>주소</th>
	        <td>${login.addr }</td>
	      </tr>
	      <tr>
	      	<th>등급</th>
	        <td>${login.grade }</td>
	      </tr>
	      <tr>
	      	<th>보유잔액</th>
	        <td>${login.money }</td>
	      </tr>
	     
	    </tbody>
	    <tfoot>
	    	<tr>
	    		<td colspan="2">
	    			<button id="modifyBtn">회원정보 수정</button>
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