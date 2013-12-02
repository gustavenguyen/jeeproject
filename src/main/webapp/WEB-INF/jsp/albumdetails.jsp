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

#description {
float:left;
	margin-left:30px;
}
#imgalbum {
	height: 200px;
	width:200px;
	float:left;
}
#comments_wrapper{
margin-top:50px;
font-style:italic;
}

.clear{
clear:both;
}
</style>

</head>

<body>


	<jsp:include page="menubar.jsp" />
	<jsp:include page="banner.jsp" />
	<div class="container">
		<!-- Example row of columns -->
		<div class="well">
<img src="<%=request.getContextPath()%>/images/${chosen_album.image}" id="imgalbum">
			<div id="description">
				<h2>${chosen_album.title}</h2>
               

				${chosen_album.artist.name}<br /> ${chosen_album.category}<br />
				<br /> <span id="like" style ="visibility:${(chosen_album.rating>0) ? 'visible': 'hidden'}">
			
			<c:choose>
			 <c:when test="${chosen_album.rating==0}"> <span id="numberoflikes">0</span> 
  
      </c:when>
			 <c:when test="${chosen_album.rating==1 && hasliked != 'yes'}"> <span id="numberoflikes">1</span> person likes this.
  
      </c:when>
       <c:when test="${chosen_album.rating==1 && hasliked == 'yes'}">You like this.
  
      </c:when>
         <c:when test="${chosen_album.rating==2 && hasliked == 'yes'}">1 person and you like this.
  
      </c:when>
      <c:when test="${!empty session_user && hasliked == 'yes'}"><span id="numberoflikes">${chosen_album.rating-1}</span> people and you like this.
  
      </c:when>
      <c:otherwise><span id="numberoflikes">${chosen_album.rating}</span> people like this.
      </c:otherwise>
</c:choose>
			
				
				</span> 
				<br />
			<c:if test="${!empty session_user && hasliked == 'no'}">	<button type="button" class="btn btn-primary" id="btn_like">Like</button>
			</c:if>
			</div>
			<div class="clear"></div>
			<table class="table">

			<tr>
				<th>Track</th>
				<th>Title</th>
			</tr>
	
			<c:forEach var="song" items="${SongList}">
				<tr>
					<td><c:out value="${song.getTrack()}" /></td>
					<td><c:out value="${song.getTitle()}" /> </td>
				</tr>
			</c:forEach>
		
		</table>
			<div id="comments_wrapper">
			<c:forEach var="CommentsList" items="${CommentsList}">
			<div class="commentbox"> 
				Comment by ${CommentsList.getAuthor().getUsername()} : 	${CommentsList.getDate()}<br />
					${CommentsList.getMessage()}
				
					</div>
				
			</c:forEach>
			</div>
			<br />
			<c:if test="${!empty session_user }">
			<form method="POST">
				<textarea class="form-control" rows="3" name="comment_value"></textarea>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
				</c:if>
		</div>

		<jsp:include page="footer.jsp" />
	</div>

	<script type="text/javascript">
		$(document).ready(function() {

							$("#btn_like").click(function(event) {

												var VarAlbumId = "<c:out value='${chosen_album.id}'/>";
												var VarLoggedInUser = "<c:out value='${session_user.getUsername()}'/>"
                                                 
												$.ajax({
															type : "GET",
                                                            url : "/peguno-project/rest/api/like?album="+ VarAlbumId+ "&user="+VarLoggedInUser,
          													dataType : "json",
															success : function(
																	data) {
																if (data.success == 1) {
																	var numLikes = parseInt($("#numberoflikes").text().trim(),10);
																	
																	if(numLikes==0)
																	$("#like").html("You like this");
																	else if(numLikes==1)
																		$("#like").html("1 person and you like this");
																	else
																		$("#like").html(numLikes +" people and you like this");
																	
																	$("#like").css("visibility","visible");
																	$("#btn_like").css("visibility","hidden");
																}
															}
														});

											});
						});
	</script>

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
