<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/bootstrap-assets/jquery.js"></script>
<style> /*style pour la partie deconnexion */
.logout_block {
	float: right;
	margin-top: 8px;
}

.logout_block a {
	color: white;
}

.logout_block a:first-child {
	margin-right: 20px;
}
</style>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="<%=request.getContextPath()%>">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>

			</ul>
			<c:if test="${empty session_user}">
				<form class="navbar-form navbar-right">
					<div class="form-group">
						<input type="text" name="username" placeholder="username"
							class="form-control" id="username">
					</div>
					<div class="form-group">
						<input type="password" name="psswd" placeholder="Password"
							class="form-control" id="psswd">
					</div>
					<button type="button" class="btn" id="btn_login">Sign in</button>
				</form>
			</c:if>
			<c:if test="${!empty session_user}">
				<div class="logout_block">
					<a href="">My account</a><a href="<c:url value="/deconnexion"/>"><button
							type="submit" class="btn">Log out</button></a>
				</div>


			</c:if>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		$("#btn_login").click(function(event) {
			
			var username = $("#username").val();
			var psswd = $("#psswd").val();
		
			var myDataObject = {
				"username" : username,
				"psswd" : psswd
			};

			$.ajax({
				dataType : "text",
				type : "POST",
				url : "home",
				data : myDataObject,
				success : function(msg) {
					var result = $.trim(msg);
					if (result == "wrong")
						alert("Identifiants incorrects");
					else
						window.location.replace(redirect);
				}
			});

		});
	});
</script>