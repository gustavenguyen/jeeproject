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
	width: 550px;


}
.alert-danger{
padding:5px;
}
</style>
</head>

<body>


	<jsp:include page="menubar.jsp" />
	<jsp:include page="banner.jsp" />
	<div class="container">
		<!-- Example row of columns -->
		<div class="well">
	 
			<div class="center_form">
				<h2>Sign Up</h2>
					
				<form method="POST" >
					
					<div class="form-group">
						<label>Username</label> <input type="text" name="username" value="<c:out value="${user.username}"/>"
							class="form-control"> <br /> 
						 	<c:if test="${MapErrors['username'].length()>0}"><span class ="alert alert-danger">${MapErrors['username'] }</span>
					</c:if>
					</div>
					<div class="form-group">
						<label>Email Address</label> <input type="text" name="email" value="<c:out value="${user.email}"/>"
							class="form-control"> <br />
						<c:if test="${MapErrors['email'].length()>0}">	<span class ="alert alert-danger">${MapErrors['email'] }</span>
					</c:if>
					</div>
					
					<div class="form-group">
						<label>Password</label> <input type="password" name="password"
							class="form-control"><br />
							<c:if test="${MapErrors['password'].length()>0}"><span class ="alert alert-danger">${MapErrors['password'] }</span>
					</c:if>
					</div>
					<div class="form-group">
						<label>Confirm password</label> <input type="password"
							name="confirmpassword" class="form-control"><br />
					</div>
					<label><input type="checkbox" name="terms"> I agree
						with the <a href="#">Terms and Conditions</a>.</label> <input
						type="submit" value="Sign up" class="btn btn-primary pull-right">
					<div class="clearfix"></div>
				</form>
			</div>
		</div>

		<jsp:include page="footer.jsp" />
	</div>
	<c:if test="${MapErrors['agreeterms'].length()>0}">
<script>
var agreeterms = "<c:out value="${MapErrors['agreeterms']}" />";
alert(agreeterms);</script>
	 </c:if>

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
