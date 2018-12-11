<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글쓰기</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
  

<style type="text/css">
label {
	background-color: #FAEBD7;
}
</style>
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
    <div class="panel-heading">게시판 글쓰기</div>
    <div class="panel-body">
	    <form class="form-horizontal" method="post" enctype="multipart/form-data">
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="title">보증금/월세(만원)</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="title" name="title"
		       placeholder="보증금/월세">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="type">방구조</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="type" name="type"
		        placeholder="ex)오픈형 원룸(방1),1층">
		    </div>
		  </div>
		  
		  <div class="form-group">
			 <label for="content"  class="control-label col-sm-2">내용</label>
		     <div class="col-sm-10">
			   <textarea class="form-control" rows="5" id="content"
			   name="content"></textarea>
			 </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="addr1">주소</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="addr1" name="addr1">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="writer">작성자</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="writer" name="writer"
		       placeholder="작성자 2-10글자." pattern="[가-힣]{2,10}">
		    </div>
		    
		  </div>
		   <div class="form-group">
		    <label class="control-label col-sm-2" for="tel">전화번호</label>
		    <div class="col-sm-10"> 
		      <input type="text" class="form-control" id="tel" name="tel"
		       placeholder="010-1234-5678 형식으로 입력" pattern="\d{3}[\-]\d{4}[\-]\d{4}">
		    </div>
		  </div>
		  
		  <div class="form-group">
		  	<label class="control-label col-sm-2" for="exampleInputEmail1">
		  	File DROP Here</label>		  	
		  </div>
		  
		  <div class="fileDrop">
		  </div>		  
		  <!-- /.box -body -->		  
		  <div class="box-footer">
		  	<div>
		  		<hr>
		  	</div>		  	
		  	<ul class="mailbox-attachments clearfix uploadedList">
		  	</ul>		  	
		  </div>
		  
		  <div class="form-group">
		    <label class="control-label col-sm-2" for="file1">첨부파일:</label>
		    <div class="col-sm-10"> 
		      <input type="file" class="form-control" id="file1" name="file1">
		    </div>
		  </div>
		  <div class="form-group"> 
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">등록</button>
		      <button type="reset" class="btn btn-warning">새로입력</button>
		      <button type="button" class="btn btn-danger" id="cancelBtn">취소</button>
		    </div>
		  </div>
		</form>
		
		<style>
		.fileDrop {
		width: 80%;
		height: 100px;
		border: 1px dotted gray;
		/* background-color: lightslategrey; */
		margin: auto;
		}
		</style>
    </div>
  </div>
</div>
</body>
</html>