<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="jumbotron">
	<div class="container">
		<h2>Welcome to our music library</h2>
		<p>Search for your favorite musics and give us your
			recommendations</p>
		<p>
			<%String adresseUrl = request.getRequestURI();  //enleve le bouton signup sur la page signup
		    adresseUrl=adresseUrl.substring(adresseUrl.lastIndexOf("/")+1,adresseUrl.length());
			if(adresseUrl.equals("signup.jsp"))
				out.println("<a class=\"btn btn-primary btn-lg\" style=\"visibility:hidden;\">Sign up &raquo;</a>");
	        else if(session.getAttribute("session_user") != null )
	        	out.println("<a class=\"btn btn-primary btn-lg\" style=\"visibility:hidden;\">Sign up &raquo;</a>");
			else 
				out.println("<a class=\"btn btn-primary btn-lg\" href=\""+request.getContextPath()+"/form\">Sign up &raquo;</a>");%>
		</p>
	</div>
</div>
