<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/assets/ico/favicon.png">

<title>Song Recommendations for Music Lovers</title>

<!-- Bootstrap Core CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="${pageContext.request.contextPath}/resources/css/startmin.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="${pageContext.request.contextPath}/resources/css/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.13/datatables.min.js"></script>
	
	<script type="text/javascript">

			function toggle(y){
				var x = document.getElementById(y);
				
				
				if(x.style.display=="block"){
					x.style.display="none";
					
				}else{
					x.style.display="block";
				}
			};
	
	</script>
	<style type="text/css">  

		#learnMoreRecommendedTrend,#learnMoreRecommendedBest,#learnMoreRecommended{
			display:none;
			
		}
	
	</style>
</head>
<body>

	<div class="navbar-wrapper" style="background-color: #222222;">
		<div class="container">

			<div class="navbar navbar-inverse navbar-static-top">
				<div class="container" style="border-color: none;">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#">Song Recommendation for Music
							Lover</a>
					</div>
					<div class="navbar-collapse collapse"
						style="border-color: none; background-color: #222222; width: 100%;">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Home</a></li>
							<li><a href="dashboard">Dashboard</a></li>
							<!-- <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Charts <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Nav header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li> -->
							<li><a href="#">Ashish Dass</a></li>
							<!-- <li><a href="sourcetableform">Source Tables</a></li>
                 -->
							<li><a href="#">Chand Teckchandani</a></li>
							<li><a href="#">Vidya Viswakumar</a></li>


						</ul>
					</div>
				</div>
			</div>

		</div>
	</div>


	
	
	<div class="container marketing">
		<!-- Page Content -->
		<div id="page-wrapper" style="margin-left: 0px;">
			<div class="container-fluid">
				<div class="page-header" style="text-align: center;">
					
					<!-- Three columns of text below the carousel -->
					<h2><i>You are feeling ${mood}</i></h2>
					<hr class="featurette-divider">
					<h2><i>Best Recommended songs</i></h2>
					<input type="button" value="Learn More>>" onclick="toggle('learnMoreRecommendedBest')" />
					<div id="learnMoreRecommendedBest"><img src="${pageContext.request.contextPath}/resources/images/BestRecommended.jpg"></div>
					<div class="row">	
					<c:forEach items="${bestRecommendedSongs}" var="song">
					
							<div class="col-lg-3">
								<label for="linearLabel"
									style="position: absolute; margin: 65px 0px 0px 40px;"><b></b></label>
							
							<img class="img-circle"
								src="${pageContext.request.contextPath}/resources/images/music.jpg"
								alt="Generic placeholder image">
							<h3><i>"${song.getTitle()}"</i></h3>
							<p>
								<h4><i>Artist Name: ${song.getArtist_name()}"</i></h4>
							</p>
							</div>
							
					</c:forEach>
					</div>
					<hr class="featurette-divider">
					<h3><i>Trending Recommended songs</i></h3>
					<input type="button" value="Learn More>>" onclick="toggle('learnMoreRecommendedTrend')" />
					
					<div id="learnMoreRecommendedTrend"><img src="${pageContext.request.contextPath}/resources/images/TrendingRecommended.jpg"></div>
					<div class="row">
					
					<c:forEach items="${trendingRecommendedSongs}" var="song">
					
							<div class="col-lg-3">
								<label for="linearLabel"
									style="position: absolute; margin: 65px 0px 0px 40px;"><b></b></label>
							
							<img class="img-circle"
								src="${pageContext.request.contextPath}/resources/images/trend.jpg"
								alt="Generic placeholder image">
							<h3><i>"${song.getTitle()}"</i></h3>
							<p>
								<h4><i>Artist Name: ${song.getArtist_name()}"</i></h4>
							</p>
							</div>
							
					</c:forEach>
					</div>
					<hr class="featurette-divider">
					<h2><i>Recommended songs</i></h2>
					<input type="button" value="Learn More>>" onclick="toggle('learnMoreRecommended')" />
					<div id="learnMoreRecommended"><img src="${pageContext.request.contextPath}/resources/images/Recommended.jpg"></div>
					<div class="row">
					<c:forEach items="${recommendedSongsList}" var="song">
					
							<div class="col-lg-3">
								<label for="linearLabel"
									style="position: absolute; margin: 65px 0px 0px 40px;"><b></b></label>
							
							<img class="img-circle"
								src="${pageContext.request.contextPath}/resources/images/guitar.png"
								alt="Generic placeholder image">
							<h3><i>"${song.getTitle()}"</i></h3>
							<div >
								<h4><i>Artist Name: ${song.getArtist_name()}"</i></h4>
																	
								
							</div>
							</div>
						
					</c:forEach>
					</div>
						
						

					</div>
					<!-- /.row -->
				</div>
			</div>
</div>





</body>
</html>
