<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.png">

<title>Music Library</title>

<!-- Bootstrap core CSS -->
<link href="CSS/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jumbotron.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
<style>
.center_form {
	width: 500px;
}

.well {
	
}

#description {
	height: 200px;
}
</style>
</head>

<body>


	<jsp:include page="menubar.jsp" />
	<jsp:include page="banner.jsp" />
	<div class="container">
		<!-- Example row of columns -->
		<div class="well">

			<div id="description">
				<h3>Discover our API to find album using our web service</h3>
          <br />
			The GPNtunes API is a RESTful API which uses HTTP requests and JSON responses.
		</div>

		<jsp:include page="footer.jsp" />
	</div>

	

	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-assets/jquery.js"></script>
	<script src="js/bootstrap-assets/transition.js"></script>
	<script src="js/bootstrap-assets/alert.js"></script>
	<script src="js/bootstrap-assets/modal.js"></script>
	<script src="js/bootstrap-assets/dropdown.js"></script>
	<script src="js/bootstrap-assets/scrollspy.js"></script>
	<script src="js/bootstrap-assets/tab.js"></script>
	<script src="js/bootstrap-assets/tooltip.js"></script>
	<script src="js/bootstrap-assets/popover.js"></script>
	<script src="js/bootstrap-assets/button.js"></script>
	<script src="js/bootstrap-assets/collapse.js"></script>
	<script src="js/bootstrap-assets/carousel.js"></script>
	<script src="js/bootstrap-assets/typeahead.js"></script>

</body>
</html>
