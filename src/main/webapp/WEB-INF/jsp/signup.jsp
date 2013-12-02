<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Music Library</title>

<!-- Bootstrap core CSS -->
<link href="CSS/bootstrap.css" rel="stylesheet">


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
					 <input
						type="submit" value="Sign up" class="btn btn-primary pull-right">
					<div class="clearfix"></div>
				</form>
			</div>
		</div>

		<jsp:include page="footer.jsp" />
	</div>
	
</body>
</html>
