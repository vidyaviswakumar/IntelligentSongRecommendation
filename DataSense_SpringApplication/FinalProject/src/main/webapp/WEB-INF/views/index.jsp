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


	<!-- Carousel 
    ================================================== -->
	<div id="myCarousel" class="carousel slide">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>

		</ol>

		<div class="carousel-inner">
			<div class="item active">
				<img style="margin: auto; height: 400px;"
					src="${pageContext.request.contextPath}/resources/images/16.jpg"
					alt="Second slide">
				<div class="container">
					<div class="carousel-caption"></div>
				</div>
			</div>
			<div class="item">
				<img style="margin: auto; height: 400px;"
					src="${pageContext.request.contextPath}/resources/images/12.jpg"
					alt="Third slide">
				<div class="container">
					<div class="carousel-caption"></div>
				</div>
			</div>

			<div class="item">
				<img style="margin: auto; height: 400px;"
					src="${pageContext.request.contextPath}/resources/images/13.jpg"
					alt="First slide">
				<div class="container">
					<div class="carousel-caption"></div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev"><span
			class="glyphicon glyphicon-chevron-left"></span></a> <a
			class="right carousel-control" href="#myCarousel" data-slide="next"><span
			class="glyphicon glyphicon-chevron-right"></span></a>
	</div>
	<!-- /.carousel -->

	<div class="container marketing">
		<!-- Page Content -->
		<div id="page-wrapper" style="margin-left: 0px;">
			<div class="container-fluid">
				<div class="page-header" style="text-align: center;"></div>

				<div id='model1'>



					<div class="row" style="margin: 20px; padding-left: 250px;">


						<form style="font-size: 15px;" action="home" method="POST">

							<div style="margin: 2%;">
							
		<button type="submit" name="mood" value="joy"><img style="margin: auto; height: 100px;" src="${pageContext.request.contextPath}/resources/images/joy.png" alt="First slide"></button>
		<button type="submit" name="mood" value="sadness"><img style="margin: auto; height: 100px;" src="${pageContext.request.contextPath}/resources/images/sad.jpg" alt="First slide"></button> 
		<button type="submit" name="mood" value="disgust"><img style="margin: auto; height: 100px;" src="${pageContext.request.contextPath}/resources/images/disgust.png" alt="First slide"></button> 
		<button type="submit" name="mood" value="fear"><img style="margin: auto; height: 100px;" src="${pageContext.request.contextPath}/resources/images/fear.png" alt="First slide"></button> 
		<button type="submit" name="mood" value="anger"><img style="margin: auto; height: 100px;" src="${pageContext.request.contextPath}/resources/images/red-angry-smiley-face.png" alt="First slide"></button>
							
							</div>





							<div class="form-group col-sm-8">
								<label for="userId">UserId :</label> <input
									placeholder="Enter userId" required type="number" class="form-control" name="userId"
									style="font-size: 15px; height: 50px">
							</div>


							<div class="form-group col-sm-8">
								<label for="size">How are you feeling today</label> <input
									class="form-control" name="mood"
									style="font-size: 15px; height: 50px">
							</div>


							<div class="form-group col-sm-11">

								<button type="submit" class="btn btn-primary"
									style="font-size: 20px;">Submit</button>
							</div>
						</form>


					</div>

					<!-- START THE FEATURETTES -->

					<hr class="featurette-divider">

					<div class="row featurette">
						<div class="col-md-4">
							<h2 class="featurette-heading">
								Artist Population <span class="text-muted">Location based</span>
							</h2>
							<p class="lead">The plot highlights a very close relationship
								between the artists and the location around the globe. And is
								pretty evident that there is a concentration of artists in the
								metropolitian areas</p>
						</div>
						<div class="col-md-8">
							<!-- <img class="featurette-image img-responsive" src="data:image/png;base64," data-src="holder.js/500x500/auto" alt="Generic placeholder image"> -->
							<div class='tableauPlaceholder' id='viz1492936452479'
								style='position: relative'>
								<noscript>
									<a href='#'><img alt='artists_from_places '
										src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;Fo&#47;For_MSD&#47;Sheet2&#47;1_rss.png'
										style='border: none' /></a>
								</noscript>
								<object class='tableauViz' style='display: none;'>
									<param name='host_url'
										value='https%3A%2F%2Fpublic.tableau.com%2F' />
									<param name='site_root' value='' />
									<param name='name' value='For_MSD&#47;Sheet2' />
									<param name='tabs' value='no' />
									<param name='toolbar' value='yes' />
									<param name='static_image'
										value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;Fo&#47;For_MSD&#47;Sheet2&#47;1.png' />
									<param name='animate_transition' value='yes' />
									<param name='display_static_image' value='yes' />
									<param name='display_spinner' value='yes' />
									<param name='display_overlay' value='yes' />
									<param name='display_count' value='yes' />
								</object>
							</div>
							<script type='text/javascript'>
								var divElement = document
										.getElementById('viz1492936452479');
								var vizElement = divElement
										.getElementsByTagName('object')[0];
								vizElement.style.width = '100%';
								vizElement.style.height = (divElement.offsetWidth * 0.75)
										+ 'px';
								var scriptElement = document
										.createElement('script');
								scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';
								vizElement.parentNode.insertBefore(
										scriptElement, vizElement);
							</script>
						</div>
					</div>

					<hr class="featurette-divider">

					<div class="row featurette">
						<div class="col-md-8">
							<div class='tableauPlaceholder' id='viz1492936686711'
								style='position: relative'>
								<noscript>
									<a href='#'><img alt='User_songsHeard_With_Count '
										src='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;Fo&#47;For_MSD_1&#47;Sheet1&#47;1_rss.png'
										style='border: none' /></a>
								</noscript>
								<object class='tableauViz' style='display: none;'>
									<param name='host_url'
										value='https%3A%2F%2Fpublic.tableau.com%2F' />
									<param name='site_root' value='' />
									<param name='name' value='For_MSD_1&#47;Sheet1' />
									<param name='tabs' value='no' />
									<param name='toolbar' value='yes' />
									<param name='static_image'
										value='https:&#47;&#47;public.tableau.com&#47;static&#47;images&#47;Fo&#47;For_MSD_1&#47;Sheet1&#47;1.png' />
									<param name='animate_transition' value='yes' />
									<param name='display_static_image' value='yes' />
									<param name='display_spinner' value='yes' />
									<param name='display_overlay' value='yes' />
									<param name='display_count' value='yes' />
								</object>
							</div>
							<script type='text/javascript'>
								var divElement = document
										.getElementById('viz1492936686711');
								var vizElement = divElement
										.getElementsByTagName('object')[0];
								vizElement.style.width = '100%';
								vizElement.style.height = (divElement.offsetWidth * 0.75)
										+ 'px';
								var scriptElement = document
										.createElement('script');
								scriptElement.src = 'https://public.tableau.com/javascripts/api/viz_v1.js';
								vizElement.parentNode.insertBefore(
										scriptElement, vizElement);
							</script>
							<div class="col-md-4">
								<h2 class="featurette-heading">
									Sales Prediction <span class="text-muted">Using ARIMA
										Model</span>
								</h2>
								<p class="lead"></p>
							</div>
						</div>



						<!-- /END THE FEATURETTES -->


						<!-- FOOTER -->


					</div>
					<footer>
						<p class="pull-right">
							<a href="#">Back to top</a>
						</p>
						<p>
							&copy; 2016 Company, Inc. &middot; <a href="#">Privacy</a>
							&middot; <a href="#">Terms</a>
						</p>
					</footer>
					<!-- /.container -->
				</div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/holder.js"></script>

	<!-- #################################### Team 4 Code End #################################### -->
	<!-- ########################################################################################### -->
	<%-- 

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation"       >
        <div class="navbar-header">
            <a class="navbar-brand" href="home"><b>ADS Assignment 3</b></a>
        </div>

        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>

        <!-- Top Navigation: Left Menu -->
        <ul class="nav navbar-nav navbar-left navbar-top-links" style="margin-left:45%">
            <li style="font-size:18px"><a href="#"><i class="fa fa-users fa-fw"></i><b>TEAM 5</b></a></li>
            <li style="font-size:18px"><a href="#"><b>Suraj Sharma</b></a></li>
            <li style="font-size:18px"><a href="#"><b>Jayesh Samyani</b></a></li>
            <li style="font-size:18px"><a href="#"><b>Dipti Pamnani</b></a></li>
        </ul>


        <!-- Sidebar -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">

                <ul class="nav" id="side-menu">
                   
                    <li style="font-size:25px">
                        <a href="regressionform" class="active" style="padding:30px;" ><i class="fa fa-line-chart fa-fw"></i> Regression</a>
                    </li>

                    <li style="font-size:25px;">
                        <a href="classificationform" class="active" style="padding:30px;"><i class="fa fa-tags fa-fw"></i> Classification</a>
                    </li>

                    <li style="font-size:25px;">
                        <a href="clusteringform" class="active" style="padding:30px;"><i class="fa fa-object-group fa-fw"></i> Clustering</a>
                    </li>

                   
                </ul>

            </div>
        </div>
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid" >
            <center>
                <div class="row" style="margin:20px;" >
               
                    <h1 style="margin:10%"><a href="#" class="active"><i class="fa fa-hand-o-left custom" ></i> Choose Machine Learning Technique</a></h1>
               
            </div>
            </center>
            <div  style="background-image:url(${pageContext.request.contextPath}/resources/images/stats.jpg);height:500px;width:500px;background-repeat:no-repeat;float:right;margin-top:2%" >
              
               
            </div>

          
            
            <!-- ... Your content goes here ... -->

        </div>
    </div>

</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/startmin.js"></script>
 --%>
	<script>
		$(document).ready(function() {
			$('#purpose').on('change', function() {

				if (this.value === '0') {

					$("#model1").show();
					$("#model2").hide();
					$("#model3").hide();
					$("#model4").hide();
				} else if (this.value === '1') {

					$("#model2").show();
					$("#model1").hide();
					$("#model3").hide();
					$("#model4").hide();
				} else if (this.value === '2') {

					$("#model3").show();
					$("#model1").hide();
					$("#model2").hide();

					$("#model4").hide();
				} else if (this.value === '3') {

					$("#model4").show();
					$("#model1").hide();
					$("#model2").hide();
					$("#model3").hide();
				}
			});

		});
	</script>
</body>
</html>
