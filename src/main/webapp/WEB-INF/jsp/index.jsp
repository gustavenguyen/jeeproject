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



<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
<style>
.form-inline {
	padding-top: 7px;
	color: white;
	padding-left: 20px;
}

.form-inline .form-control {
	height: 25px;
}

#btn_valider {
	height: 32px;
	vertical-align: baseline;
}
#search_block, #keywords_block{
float:left;
margin-top:5px;
}
.well {
border:none;
background-color:white;
 box-shadow:none;
}
.albumrow:hover{
cursor:pointer;
}
</style>
</head>

<body >


	<jsp:include page="menubar.jsp" />
	<jsp:include page="banner.jsp" />
	<div class="container">
	<div class="well">
		<!-- Example row of columns -->
		<div class="navbar navbar-default">


			<form class="form-inline">

			<div id="search_block">	Search &nbsp;&nbsp;&nbsp;
				<input type="checkbox"  name ="keywords_checkbox" id="useKeywords"> with keywords<br>
			</div>
				<div id="keywords_block" style="display:none;">&nbsp;&nbsp;&nbsp;<select name="search_type" id="select_type" disabled="disabled">
				        <option value="albumtitle">Album title</option>		
				        <option value="songtitle">Song title</option>
						<option value="author">author</option>
					
				</select>&nbsp;&nbsp;&nbsp;
				<div class="form-group">

					<input type="text" name="search_name" class="form-control" type="text" id="input_research" disabled="disabled">
				</div>
				</div> <div id="sortby_block">
				&nbsp; &nbsp; &nbsp;Sort by &nbsp;&nbsp;&nbsp; <select
					name="category_selected">
					<option value="all">All</option>
					<c:forEach var="category" items="${CategoriesList}">
						<option value="${category}">${category}</option>
					</c:forEach>
				</select> &nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-primary" id="btn_valider">Validate</button>
				</div>
			</form>


		</div>

		<table class="table table-hover">

			<tr>
				<th>Title</th>
				<th>Artist</th>
				<th>Category</th>
				<th>Year</th>
			</tr>
			<c:choose>
    <c:when test="${!empty AlbumsByPage}">
			<c:forEach var="album" items="${AlbumsByPage}">
				<tr class="albumrow"
					onclick="window.location.href = '${pageContext.request.contextPath}/albumdetails?album=${album.getId()}';">
					<td><c:out value="${album.getTitle()}" /></td>
					<td><c:out value="${album.getArtist().getName()}" /></td>
					<td><c:out value="${album.getCategory()}" /></td>
					<td><c:out value="${album.getYear()}" /></td>
				</tr>
			</c:forEach>
			</c:when>
			 <c:otherwise>
       <tr > <td colspan="4">No results found. </td></tr>
    </c:otherwise>
    </c:choose>
		</table>
<c:forEach begin="1" end="${numPages}" var="i">
  <a href="<%=request.getContextPath()+"?"+request.getAttribute( "QueryString")%>&page=${i}">  <c:out value="${i}"/> </a>
</c:forEach>
	
</div>

		<jsp:include page="footer.jsp" />
	</div>
<script>
$(document).ready(function(){
    $('#useKeywords').change(function(){
        if(this.checked)
        {        $('#keywords_block').stop( true, true ).fadeIn();
        $('#select_type').prop("disabled", false);
        $('#input_research').prop("disabled", false);
        }else
        	{
        	$('#keywords_block').stop( true, true ).fadeOut();
        	   $('#select_type').prop("disabled", true);
        	   $('#input_research').prop("disabled", true);
        	}
    });
   
        // Uncheck all checkboxes on page load    
       $(':checkbox:checked').removeAttr('checked');
   
});

</script>
	<!-- /container -->



</body>
</html>
