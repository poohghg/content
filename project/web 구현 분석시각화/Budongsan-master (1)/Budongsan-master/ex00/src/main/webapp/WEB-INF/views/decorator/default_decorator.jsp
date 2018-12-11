<%@page import="org.zerock.member.dto.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
pageContext.setAttribute("absUri", request.getContextPath());
%>
<%
	LoginDTO dto = new LoginDTO(); 
	dto = (LoginDTO)session.getAttribute("login");
%>

<!DOCTYPE html>
<html>
  <head>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
    <!-- Bootstrap core CSS -->
   <link href=" ${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

   <!-- Custom fonts for this template -->
   <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
   <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
   <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

   <!-- Custom styles for this template -->
   <link href="${pageContext.request.contextPath}/resources/css/clean-blog.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/resources/css/nanumFont.css" rel="stylesheet" type="text/css">

  
  <decorator:head></decorator:head>
  <script type="text/javascript">
	  $(document).ready(function(){
		  var idx = 0;
		  var opposite = false;
		  $("#"+idx+"").addClass("active");
		  $("#right").click(function(){
			  var check = idx;
			  $(".item").removeClass("active");
			  if(idx > 1){
				  idx = -1;
			  }
			  idx++;
			  $("#"+idx+"").addClass("active");
			  console.log(idx);
		  });
	  });
  
  </script>
  
  <style type="text/css">
/*   header{ */
/*   	width: 100%; */
/*   	height: 300px; */
/*   } */
  
/*   .overlay{ */
/*   	margin: 0 auto; */
/*   } */
  nav{
  	background-color: gray;
  }
  </style>
  
  </head>
  <body>
  
  <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand" href="/">부동산닷컴</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link" href="/agentboard/list.do">매물정보</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/news/list">뉴스</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/ds/chart.do">매매 거래동향 </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/furniture/list.do">중고가구거래 </a>
            </li>
            
           
           <c:if test="<%=dto != null %>"> 
            <c:choose>
            	<c:when test="<%= dto.getLogin()==true && dto.getGrade()==9 %>">
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/profile.do"><%=dto.getName() %>님</a>
            		</li>  
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/list.do">회원리스트</a>
            		</li>
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/logout.do">로그아웃</a>
            		</li>	
            	</c:when>
            	
            	<c:when test="<%= dto.getLogin()==true && dto.getGrade()==1 %>">
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/profile.do"><%=dto.getName() %>님</a>
            		</li>
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/logout.do">로그아웃</a>
            		</li>
            	</c:when>

            	<c:when test="<%= dto.getLogin()==true && dto.getGrade()==5 %>">
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/profile.do"><%=dto.getName() %>님</a>
            		</li>
            		<li class="nav-item">
            		  <a class="nav-link" href="/member/logout.do">로그아웃</a>
            		</li>
            	</c:when>
            </c:choose>
            </c:if>
            
            <c:choose>
            	<c:when test="<%=dto == null %>">
            		<li class="nav-item">
              			<a class="nav-link" href="/member/login.do">로그인</a>
            		</li>
		            <li class="nav-item">
		              <a class="nav-link" href="/member/join.do">회원가입</a>
		            </li>
		        </c:when>
            </c:choose>
                        
          </ul>
        </div>
      </div>
    </nav>
    <header class="masthead" style="background-color: gray;" >
      
      <div class="container" style="background-color: gray;">
        
        
      </div>
    </header>

 
  
  <decorator:body></decorator:body>
    
       <hr>

    <!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <ul class="list-inline text-center">
               <li class="list-inline-item">
                <a href="https://github.com/MomoSong/Budongsan">
                  <span class="fa-stack fa-lg">
                    <i class="fa fa-circle fa-stack-2x"></i>
                    <i class="fa fa-github fa-stack-1x fa-inverse"></i>
                  </span>
                </a>
              </li>
            </ul>
            <p class="copyright text-muted">Copyright &copy; 부동산 닷컴 2018</p>
          </div>
        </div>
      </div>
    </footer>

    <!-- Bootstrap core JavaScript -->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for this template -->
    <script src="resources/js/clean-blog.js"></script>
  </body>
</html>
        
        