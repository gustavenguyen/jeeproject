<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/jquery.js"></script>
<style> /*style pour la partie deconnexion */
.logout_block {
	float: right;
	margin-top: 8px;
}

.logout_block span:first-child {
	margin-right: 20px;
	color: white;
}
</style>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="<%=request.getContextPath()%>/" id="home_link">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/discoverapi">Use our API </a></li>
				<li><a href="<%=request.getContextPath()%>/about">About</a></li>

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
					<span>logged in as ${session_user.getUsername()}</span><a href="<c:url value="/deconnexion"/>"><button
							type="submit" class="btn">Log out</button></a>
				</div>


			</c:if>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#home_link").click(function(e) {                         // interdit les multiples clicks sur le lien home
		$("#home_link").click(function(e) {
		    e.preventDefault();

		    if (!$(this).data('isClicked')) {
		        var link = $(this);

		        
		        link.data('isClicked', true);
		        setTimeout(function() {
		            link.removeData('isClicked')
		        }, 3000);
		    } else {
		       
		    }
		});
		});
		$("#btn_login").click(function(event) {
			var redirect = "<c:out value='${request.getContextPath()}'/>";
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
						alert("Invalid login/password pair");
					else
						window.location.replace(redirect);
				}
			});

		});
	});
</script>