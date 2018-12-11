<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글쓰기</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		// 취소 버튼 이벤트 처리
		$("#cancelBtn").click(function() {
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
				<form class="form-horizontal" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label class="control-label col-sm-2" for="title">제목:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title"
								pattern=".{4,100}" placeholder="제목입력 4자이상">
						</div>
					</div>
			
					<div class="form-group">
						<label class="control-label col-sm-2" for="model_id">모델명:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="model_id"
								name="model_id" placeholder="작성자 2-10글자." pattern="{2,10}">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-2" for="makecp">제조사:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="makecp" name="makecp"
								placeholder="작성자 2-10글자." pattern="{2,10}">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="price">가격:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="price" name="price"
								placeholder="숫자만." pattern="[0-9]{}">
						</div>
					</div>

					<div class="form-group">
						<label for="content" class="control-label col-sm-2">상품정보:</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="5" id="content"
								name="content"></textarea>
							
						</div>
					</div>
					
					<div class="form-group">
						<label for="content" class="control-label col-sm-2">수량:</label>
						<div class="col-sm-10">
							 <input type="number" name="counts" min="1" max="9999999">
						</div>
					</div>

					<div class="form-group">
						<!-- 		 	 <form id='form1' action="/board/uploadForm.do" method="post" -->
						<!-- 				enctype="multipart/form-data"> -->
						<label class="control-label col-sm-2" for="'file'"> 이미지:</label>
						<div class="col-sm-10">
							<!-- 		   <form id='form1' method="post" enctype="multipart/form-data" method="post"> -->
							<input type='file' name='file' class="form-control">
							<!--  			</form> -->
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-sm-2" for="'files'">뷰 이미지: </label>
						<div class="col-sm-10">						
								<input multiple="multiple" type='file' name='files'
									class="form-control">	<small class="text-muted">사이트는 400X400px에 맟추어져있습니다.</small>		
								
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
			</div>
		</div>
	</div>
</body>
</html>