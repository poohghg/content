<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 글보기</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- <link href="/resources/css/carousel.css" rel="stylesheet"> -->




<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>게시판 뷰</title>



<script type="text/javascript">
	// function add_item(){

	// 	alert("add_item 실행")
	//     if (document.getElementById('commentdiv').style.display == "none") 
	//     	document.getElementById('commentdiv').style.display = "block"
	//     if (document.getElementById('commentdiv').style.display = "block")
	//     	document.getElementById('commentdiv').style.display = "none"
	// }


	

	$(document).ready(function()
	{
		

		// 버튼 이벤트 처리
		// 수정 버튼
		$("#updateBtn").click(function()
		{
			$("#dataForm").attr("action", "update.do");
			// form.submit() 넘긴다.
			$("#dataForm").submit();
		});

		// 삭제 버튼
		$("#deleteBtn").click(function()
		{
			if (confirm("정말 삭제하시겠습니까?"))
				location = "delete.do?id=${dto.id}";
		});

		// 리스트 버튼
		$("#listBtn").click(function()
		{
			// 			location="list.do";
			// 폼에서 글번호 제거
			alert("버튼이 동작합니다.")
			$("#id").attr("disabled", "disabled");
			$("#dataForm").attr("action", "list.do");
			$("#dataForm").submit();
		});

		$("#commentBtn").click(function()
		{
			// alert("add_item 실행");
			var e = document.getElementById('commentdiv');
			if (e.style.display == 'block')
				e.style.display = 'none';
			else
				e.style.display = 'block';
		});

		$("#buyBtn").click(function()
		{
			if (confirm("정말 물건을 사시겠습니까?"))
			{
				var money = ${sessionScope.login.money}
				var price = ${dto.price}
				if (money > price)
				{
					location = "buy.do?id=${dto.id}&cpn=${dto.cpn}";
				} else
					alert("돈이 부족합니다.")
			}
		});

		$
		{
			msg == "updateOK" ? "alert('수정이 완료되었습니다.')" : ""
		}

	});
</script>
</head>
<body>
	<!-- 넘겨야할 데이터를 uri 뒤에 붙이지 않고 form 태그 사용해서 넘기기 :update, list
		update : 글번호, 한페이지의 데이터 갯수, 페이지, 검색 타입, 검색어
		list : 한페이지의 데이터 갯수, 페이지, 검색 타입, 검색어
-->
	<form id="dataForm">
		<input name="id" value="${param.id }" type="hidden" id="id"> 
		<input name="page" value="${param.page }" type="hidden"> <input name="perPageNum" value="${param.perPageNum }" type="hidden"> <input name="searchType" value="${param.searchType }" type="hidden"> <input name="keyword" value="${param.keyword }" type="hidden">
	</form>

	<div class="container">
		<h3>
			<strong>${dto.title} </strong>
		</h3>
		<hr>
	</div>

	<div class="container">
		<h1></h1>

		<div class="col-xs-12 col-sm-6 col-md-8">
			<div class="card mt-4">


				<div id="myCarousel2" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#myCarousel2" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel2" data-slide-to="1"></li>
						<li data-target="#myCarousel2" data-slide-to="2"></li>
					</ol>

					<div style="width: 400px; height: 400px; overflow: hidden" class="container">
						<div class="carousel-inner" role="listbox">
							<div class="item active">
								<img src="/resources/saveImage/${dto.id}_0.jpg">
							</div>
							<div class="item">
								<img src="/resources/saveImage/${dto.id}_1.jpg">
							</div>
							<div class="item">
								<img src="/resources/saveImage/${dto.id}_2.jpg">
							</div>
						</div>
					</div>

					<a class="left carousel-control" href="#myCarousel2" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#myCarousel2" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span>
					</a>
				</div>


				<div class="card-body">
					<h1 class="card-title">
						<strong>${dto.makecp } </strong>
					</h1>
					<hr>
					<h2 class="card-title">
						판매가: <span class='text-danger'><fmt:formatNumber value = "${dto.price}" type = "number"/></span>
					</h2>
					<span class="text-warning">&#9733; &#9733; &#9733; &#9733; &#9734;</span> 4.0 stars
					<h3>
						상품 정보: <span class='text-muted'><br/>${dto.content }</span>
					</h3>
					<h3>
						모델명: <span class='text-muted'>${dto.model_id }</span>
					</h3>
					<h3>
						수량: <span class='text-muted'>${dto.counts }</span>
					</h3>
					<input type="hidden" name="UUID" value="${dto.UUID }">
				</div>


			</div>

			</br>

			<div class="card card-outline-secondary my-4">
				<div class="card-header">
					<hr>
					<h4>구매자의 상품 평가</h4>
				</div>

				<c:forEach items="${commentlist }" var="commentlist">
					<div class="card-body">
						<p>${commentlist.comments }</p>
						<small class="text-muted">"${commentlist.userid }, ${commentlist.writedate }"</small>
						<hr>
					</div>
				</c:forEach>
			</div>

			<%-- 		 	<p>"${sessionScope.login.email}"</p> --%>
			<c:if test="${sessionScope.login.email eq dto.cpn}">
				<button id="updateBtn" class="btn btn-success">수정</button>
				<button id="deleteBtn" class="btn btn-warning">삭제</button>
			</c:if>
			<c:if test="${!empty sessionScope.login.email}">
				<button id="buyBtn" class="btn btn-success">사기</button>
				<button id="commentBtn" class="btn btn-success" onclick="add_item()">댓글 작성</button>
			</c:if>
			<button id="listBtn" class="btn btn-danger">뒤로가기</button>
		</div>
	</div>

	</br>

	<div id="field"></div>

	<div class="container" id="commentdiv" style="display: none;">
		<form class="form-horizontal" method="post">
			<label class="control-label col-sm-2" for="comments">구매평 작성</label> <input type="text" class="form-control" id="comments" name="comments" pattern=".{4,100}" placeholder="의견을 적어주세요"> <input class="w3-check" type="radio" name="assessment" checked="checked" value="5"> <label>매우좋음</label> <input class="w3-check" type="radio" name="assessment" value="4"> <label>좋음</label> <input class="w3-check" type="radio" name="assessment" value="3"> <label>보통</label> <input class="w3-check" type="radio" name="assessment" value="2"> <label>조금나쁨</label> <input class="w3-check" type="radio" name="assessment" value="1"> <label>매우나쁨</label> </br>
			<button type="submit" class="btn btn-success">제출</button>
		</form>
	</div>

	</br>
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2017</p>
		</div>
		<!-- /.container -->
	</footer>
</body>
</html>