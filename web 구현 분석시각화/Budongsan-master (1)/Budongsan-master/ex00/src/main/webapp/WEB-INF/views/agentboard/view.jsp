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

<style type="text/css">
th {
	background-color: #FAEBD7;
}
td {
	background-color: white;
}
</style>

<script type="text/javascript"
 src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<!-- handlebars template를 적용한 소스만들기  --> 
<script type="text/x-handlebars-template" id="template">
{{#each .}}
<li data-rno={{rno}}>
	<i class="fa fa-comments bg-blue"></i>

	<div class="timeline-item">
		<span class = "time">
			<i class = "fa fa-clock-o"></i>
			{{prettifyDate writeDate}}
		</span>
		<h3 class="timeline-header">
			<strong class="rno">{{rno}}</strong> - {{writer}}
		</h3>
		<div class="timeline-body content">{{content}}</div>
		<div class = "timeline-footer">
			<button type='button' class='updateBtn' data-toggle='modal'
				data-target='#myModal'>수정/삭제</button>
		</div>
	</div>
</li>
{{/each}}
</script>



<script type="text/javascript">
	$(document).ready(function(){
		
		var no = ${dto.no};
		// 버튼 이벤트 처리
		// 수정 버튼
		$("#updateBtn").click(function(){
// 			location = "update.do?no=${dto.no}";
			$("#dataForm").attr("action","update.do");
			// form.submit() 넘긴다.
			$("#dataForm").submit();
		});
		
		// 삭제 버튼
		$("#deleteBtn").click(function(){
			if(confirm("정말 삭제하시겠습니까?")){
				
				var arr=[];
				$("#fileList").each(function(index){
					arr.push($(this).attr("data-src"));
				});
				if(arr.length>0){
					$.post("/deleteAllFiles",{files:arr}, function(){
						
					});
				}
				
				$("fileList").attr("action", "/agentboard/delete.do")
				$("fileList").submit();
				location="delete.do?no=${dto.no}";
			}
			   
		});
		
		// 리스트 버튼
		$("#listBtn").click(function(){
// 			location="list.do";
			// 폼에서 글번호 제거
			$("#no").attr("disabled","disabled");
			$("#dataForm").attr("action", "list.do");
			$("#dataForm").submit();
		});
		//글 수정 후 경고
		${msg == "updateOK"?"alert('수정이 완료되었습니다.')":""}
		
		
	}); // end of document.ready()
	
	
	function printPaging(cri){
		var str = "";
		// 이전 페이지 출력
		if(cri.prev){
			str += "<li><a href='"+(cri.startPage -1)
			+"'><i class='glyphicon glyphicon-step-backward'></i>"
			+"</a></li>";
		}
		// 반복 페이지
		for(var i = cri.startPage ; i<= cri.endPage; i++){
			var strClass = (cri.page==i?" class='active' ":"");
			str += "<li "+strClass +"><a href='"+i+"' >"
			+i+"</a></li>";
		}
		// 다음 페이지 출력
		if (cri.next){
			str += "<li><a href='"+(cri.endPage+1)
			+"'><i class='glyphicon glyphicon-step-forward'></i>"
			+"</a></li>";	
		}
		// 작성된 str(li)를 pagination 에 넣자.
		$(".pagination").html(str);
	}
	
</script>

</head>
<body>
<!-- 넘겨야할 데이터를 uri 뒤에 붙이지 않고 form 태그 사용해서 넘기기 :update, list
		update : 글번호, 한페이지의 데이터 갯수, 페이지, 검색 타입, 검색어
		list : 한페이지의 데이터 갯수, 페이지, 검색 타입, 검색어
-->
<form id="dataForm"">
	<input name="no" value="${param.no }" type="hidden" id="no">
	<input name="page" value="${param.page }" type="hidden">
	<input name="perPageNum" value="${param.perPageNum }" type="hidden">
	<input name="searchType" value="${param.searchType }" type="hidden">
	<input name="keyword" value="${param.keyword }" type="hidden">
</form>
<div class="container">
  <div class="panel panel-default">
    <div class="panel-heading"><strong>중개 정보</strong></div>
    <div class="panel-body" style= "background: linear-gradient(to bottom, #ffffcc 0%, #ff99cc 100%);">
        <table class="table">
  <!--     <table class="detail-list"> -->
       <colgroup>
                <col width="100px">
                <col width="150px">
                <col width="100px">
                <col width="150px">
            </colgroup>
	    <tbody>
	      <tr class="i-top">
	      	<th>글번호</th>
	        <td colspan="3" bgcolor=" #F5F5F5">${dto.no }</td>
	      </tr>
	      <tr>
	      <th>이미지</th>
	      <td colspan="3">
	      
	 	     <div id="fileList">
			  	<c:forEach items="${list }" var="fileDto">
	  				<c:if test="${fileDto.image }">
	  					<img src="/displayFile?filename=${fileDto.fileName }" /><br/>
	  				</c:if>
	  			</c:forEach>
	 		 </div>
	 		 
	  	  </td>
	      </tr>
	      <tr>
	      	<th >보증금/월세(만원)</th>
	        <td>${dto.title }</td>
	        
	      	<th >방 구조</th>
	        <td>${dto.type }</td>
	      </tr>
	      <tr>
	      	<th>내용</th>
	        <td colspan="3">${dto.content }</td>
	      </tr>
	      <tr>
	      	<th>주소</th>
	        <td colspan="3">${dto.addr1}</td>
	      </tr>
	      <tr>
	      	<th>작성자</th>
	        <td>${dto.writer }</td>
	     
	      	<th>전화번호</th>
	        <td>${dto.tel }</td>
	      </tr>
	      <tr>
	      	<th>작성일</th>
	        <td ><fmt:formatDate value="${dto.writeDate }" 
	        pattern="yyyy-MM-dd"/></td>
	      
	      	<th>조회수</th>
	        <td>${dto.hit }</td>
	      </tr>
	    </tbody>
	    <tfoot>
	    	<tr>
	    		<td colspan="4">
	    			<button id="updateBtn">수정</button>
	    			<button id="deleteBtn">삭제</button>
	    			<button id="listBtn">리스트</button>
	    		</td>
	    	</tr>
	    </tfoot>
	  </table>
    </div>
  </div>
</div>

</body>
</html>