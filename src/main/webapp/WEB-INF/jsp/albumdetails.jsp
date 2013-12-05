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
	width: 650px;
	
}

#description {
width:300px;
float:left;
	margin-left:30px;
}
#imgalbum {
	height: 200px;
	width:200px;
	border:1px solid #D5D5D5;
	float:left;
}
#comments_wrapper{
margin-top:50px;
font-style:italic;
}

.clear{
clear:both;
}
.table{
border-top: none;
width:300px;
margin-left:260px;
}
.table tr th{
border-top: none;
}
</style>

</head>

<body>


	<jsp:include page="menubar.jsp" />
	<jsp:include page="banner.jsp" />
	<div class="container">
		
		<div class="well">
<img src="<%=request.getContextPath()%>/images/${chosen_album.image}" id="imgalbum">
			<div id="description">
				<h2>${chosen_album.title}</h2>
               

				<span style="font-size:18px">${chosen_album.artist.name}</span><br />
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

			<tr >
				<th style="border-top: none">Track</th>
				<th style="border-top: none">Title</th>
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
				<textarea class="form-control" rows="3" name="comment_value" id="comment_text"></textarea>
				<button class="btn btn-default" type="button" id="submit_comment">Submit</button>
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
                                                            url : "rest/api/like?album="+ VarAlbumId+ "&user="+VarLoggedInUser,
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

<script type="text/javascript">
	
		$("#submit_comment").click(function(event) {
			var album_id ="<c:out value='${param.album}'/>";
			var loggeduser ="<c:out value='${sessionScope.session_user.username}'/>";
			var serviceUrl = "rest/api/comments/add";
			var redirect="<c:out value='${request.getContextPath()}'/>"+"albumdetails?album="+album_id;
			var text = $.trim($("#comment_text").val());
			if(text.length >5 )
			{
			
		
			var myDataObject = {
				"comment_value" : text,
				"album": album_id,
				"loggeduser": loggeduser,
			};

			$.ajax({
				dataType : "text",
				type : "POST",
				url : serviceUrl,
				data : myDataObject,
				success : function(msg) {
					
					var result = $.trim(msg);
					if (result == "wrong")
						alert("Invalid login/password pair");
					else
						window.location.replace(redirect);
				}
			});
			}
			else if( text.length>0 && text.length <6)
				 alert("Comment must contain at least 6 characters")
				 else
					 alert("Comment can't be empty");
		
		});
		
		
</script>


</body>
</html>
