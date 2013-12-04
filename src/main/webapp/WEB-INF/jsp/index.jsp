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
	 <script src="js/jquery.js"></script>
   
	

<title>Music Library</title>

<!-- Bootstrap core CSS -->
<link href="CSS/base/jquery.ui.all.css" rel="stylesheet" >
<link href="CSS/bootstrap.css" rel="stylesheet">

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
#search_block,#keywords_block, #sortby_block{
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
tr:hover{
background-color:#d9edf7;

}
tr:hover td{

color:#3276b1;
}
#table_titles:hover{
background-color:white;

}
#table_titles th{
border-top:none;

}
.table{
margin-top:-10px;
}
.ui-autocomplete { position: absolute; cursor: default;z-index:1000;}
</style>
	<script>

	 $(function() {	
		 function log( message ) {
				$( "<div>" ).text( message ).prependTo( "#log" );
				$( "#log" ).scrollTop( 0 );
			}
		$( "#input_research" ).autocomplete({

			source: function( request, response ) {
				var searchcriteria = $("#select_type").val();
				
				var serviceUrl= "http://localhost:8080/peguno-project/rest/api/albums/byalbumtitle";
			
			    if($.trim(searchcriteria) == "songtitle")
					serviceUrl = "http://localhost:8080/peguno-project/rest/api/songs/bytitle";
			    if($.trim(searchcriteria) == "author")
					serviceUrl = "http://localhost:8080/peguno-project/rest/api/artists/byname";
				$.ajax({
					
					url: serviceUrl,
					dataType: "json",
					data: {
					
						name: request.term
					},
					success: function( data ) {
						
						if($.trim(searchcriteria) == "albumtitle")
							{
						
						response( $.map( data, function( item ) {
							return {
								label: item.title,
								value: item.title
							}
						}));
							}
						else if($.trim(searchcriteria) == "author")
							{
							  response( $.map( data, function( item ) {
								return {
									label: item.name,
									value: item.name
								}
							}));
							}
						else
							{
							 response( $.map( data, function( item ) {
									
								 return {
										label: item.title + ", "+item.artist,
										artist:item.artist,
										value: item.title
									}
								}));
							
							}
							
					}
				});
			},
			minLength: 2,
			select: function( event, ui ) {
				log( ui.item ?
					"Selected: " + ui.item.label :
					"Nothing selected, input was " + this.value);
			if(ui.item.artist !=null)
				{$("#input_artist_name").val(ui.item.artist);
			     $('#input_artist_name').prop("disabled", false);}
			
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		});
	 });
	
	</script>
</head>

<body >


	<jsp:include page="menubar.jsp" />
	<jsp:include page="banner.jsp" />
	<div class="container">
	<div class="well">
		<!-- Example row of columns -->
		<div class="navbar navbar-default">


			<form class="form-inline">

			<div id="search_block">	Search by&nbsp;&nbsp;&nbsp;
				
			</div>
				<div id="keywords_block">&nbsp;&nbsp;&nbsp;<select name="search_type" id="select_type" >
				         <option value="category">category</option>	
				        <option value="albumtitle">Album title</option>		
				        <option value="songtitle">Song title</option>
						<option value="author">author</option>
					
				</select>&nbsp;&nbsp;&nbsp;
				<div class="form-group">

					<input type="text" name="search_name" class="form-control" id="input_research" style="padding:0px 12px;display:none;" disabled="disabled">
				</div>
				</div> <div id="sortby_block">
				 <select
					name="category_selected" id="select_category">
					<option value="all">All</option>
					<c:forEach var="category" items="${CategoriesList}">
						<option value="${category}">${category}</option>
					</c:forEach>
				</select> 	</div>&nbsp;&nbsp;&nbsp;
				<input type="text" name="artist" class="form-control" id="input_artist_name" style="padding:0px 12px;display:none;" disabled="disabled">
				<button type="submit" class="btn btn-primary" id="btn_valider">Validate</button>
			
			</form>


		</div>

		<table class="table">

			<tr id="table_titles">
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
       $('#select_type').change(function() {
    	    var selected = $(this).val();
    	    if(selected == 'category'){
    	      $('#input_research').hide();
    	      $('#select_category').show();
    	      $('#select_category').prop('disabled', false);
    	    }
    	    else{
    	      $('#select_category').hide();
    	      $('#select_category').prop('disabled', true);
    	      $('#input_research').show();
    	      $("#input_research").prop('disabled', false);
    	    }
    	});
       

      

});


</script>

	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.position.js"></script>
	<script src="js/jquery.ui.menu.js"></script>
	<script src="js/jquery.ui.autocomplete.js"></script>


</body>
</html>
