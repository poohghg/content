<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>

<script type="text/javascript">
	$(document).ready(function(){
		// 취소 버튼 이벤트 처리
		$("#cancelBtn").click(function(){
			history.back();
		});
	});
</script>

</head>
<body>

<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading">회원 정보 수정</div>
    <div class="panel-body">
	    <form class="form-horizontal" method="post">
		 
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="name">이름:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="name" name="name"
		       value="${dto.name }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="age">나이:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="age" name="age"
		       value="${dto.age }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="addr">주소:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="addr" name="addr"
		       value="${dto.addr }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="seller_name">사업자명:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="seller_name" name="seller_name"
		       value="${dto.seller_name }">
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="grade">등급:</label>
		    <select name="grade">
			    <option value="1">일반회원</option>
			    <option value="5">판매자회원</option>
			    <option value="9">관리자</option>
			</select>
		  </div>
		  </div>
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="money">보유잔액:</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="money" name="money"
		       value="${dto.money }">
		    </div>
		  </div>
		 
		  <div class="form-group"> 
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">수정</button>
		      <button type="button" class="btn btn-danger" id="cancelBtn">취소</button>
		    </div>
		  </div>
		</form>
    </div>
  </div>
</div>


</body>
</html>