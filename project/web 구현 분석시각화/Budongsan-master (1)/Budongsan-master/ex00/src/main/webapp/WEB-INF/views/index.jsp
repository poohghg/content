<%@page import="org.zerock.member.dto.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 
uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>부동산닷컴</title>


  </head>

  <body>

	  <!-- Page Header -->
    <header class="masthead" >
      
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-10 mx-auto">
            <div class="site-heading">
              <div class="container">
					  <h2></h2>  
					  <div id="myCarousel" class="carousel slide" data-ride="carousel">
<!-- 					    Indicators -->
					    <ol class="carousel-indicators">
					      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					      <li data-target="#myCarousel" data-slide-to="1"></li>
					      <li data-target="#myCarousel" data-slide-to="2"></li>
					    </ol>
					
<!-- 					    Wrapper for slides -->
					    <div class="carousel-inner">
					
						<c:forEach items="${carousel }" varStatus="status"  var="main">
					      <div id="${status.index }" class="item" style="margin: 0 auto; width:300px; height: 250px;">
					        <a href="/agentboard/view.do?no=${main.no }">
					        <img src="/resources/imgfile/${main.fileName }" alt="${main.title }" style="width:300px; height: 250px;">
					        </a>
					        <P>${main.title }</P>
					      </div>
						</c:forEach>
					
					    </div>
					
					    <a id="right" class="right carousel-control" href="#myCarousel" data-slide="next">
					      <span class="glyphicon glyphicon-chevron-right"></span>
					      <span class="sr-only">Next</span>
					    </a>
					  </div>
				</div>
            </div>
          </div>
        </div>
      </div>
    </header>

   

  

 

  </body>

</html>
