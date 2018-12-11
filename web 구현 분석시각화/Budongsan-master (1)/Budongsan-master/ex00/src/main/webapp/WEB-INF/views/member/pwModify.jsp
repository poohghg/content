<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>

<script type="text/javascript">
$(document).ready(function(){
	
});


</script>

</head>
<body>

<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">비밀번호 변경</div>
    <div class="panel-body">
	    <form class="form-horizontal" method="post">
		 
		  <div class="form-group" hidden="true">
		    <label class="control-label col-sm-2" for="email">이메일아이디:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="email" name="email" value="${login.email }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="pw">기존 비밀번호:</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="pw" name="pw">
		    </div>
		  </div><br/><br/>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="newPw">변경할 비밀번호:</label>
		    <div class="col-sm-10"> 
		      <input type="password" class="form-control" id="newPw" name="newPw">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="checkPw">비밀번호 확인:</label>
		    <div class="col-sm-10"> 
		      <input type="password" class="form-control" id="checkPw">
		    </div>
		  </div>
		 
		  
		  <div class="form-group"> 
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" id="modifyBtn" class="btn btn-default">수정</button>
		      <button type="button" class="btn btn-danger" id="cancelBtn">취소</button>
		    </div>
		  </div>
		  
		</form>
    </div>
  </div>
</div>


</body>
</html>