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
.well .form-group {
	width: 300px;
	float:left;
	margin-top:30px;
}

.well {
	clear:both;
}


#btn_validate {
	float:left;
    margin-top:30px;
    
}
#json_return {

display:none;
    
}
#parse_json {
	clear:both;
    display:none;
}
#available_services {
clear:both;
	margin-top:30px;
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

			<div id="description">
				<h3>Discover our API to find albums using our web service</h3>
          <br />
		<div>
			The GPNtunes API is a RESTful API which uses HTTP requests and JSON responses. <br />
			For example, finding albums by author can be done by using /rest/api/albums/byauthor?name={type the name you want} <br />
			The response will be in JSON, parse it to retrieve the data. <br />
			Below is an example of finding albums by author using ajax to connect to the API. <br />
			Just type a composer name in the field, then it will return corresponding albums. (Default value Coldplay will return all Coldplay's albums.)
		</div>
        <div class="form-group">
						<input type="text" placeholder="type album title"
							class="form-control" id="searchparameter">
					</div>
					<button class="btn btn-primary" id="btn_validate">Validate</button>
					<div class="clear"></div>
		<div id="json_return">The following json response is returned : <br /> <div id="json_response"  class="alert alert-info" ></div></div>
		<div id ="parse_json"><p>Then retrieving data is easy by parsing json. Click below to parse using ajax again:</p>
				<button class="btn btn-primary" id="btn_parse" >Parse the json response</button><div id="parse_response"></div></div>
			<div id="available_services"> <i>Available services : <br />
			find album by title : /rest/api/albums/byalbumtitle?name={name you want} <br />
			find albums by category : /rest/api/albums/bycat?name={name you want} <br />
			find albums by composer : /rest/api/albums/byauthor?name={name you want} <br />
			find albums by song title : /rest/api/albums/bysongtitle?name={name you want}</i> <br />
			</div>	
		</div>
		<div style="clear:both">
		</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>

	<script type="text/javascript" >
$(document).ready(function() {
	$(function () {
		  $("#searchparameter").val("Coldplay");
		});
    $("#btn_validate").click(function(event){ 
    	var parameter = $("#searchparameter").val();
    	if(parameter.length>0)
    	{var apiurl ="rest/api/albums/byauthor?name="+parameter;
   
    	$.ajax({
    		  url: apiurl,
    		  success: function( data ) {
    			 var json_return = JSON.stringify(data);
    			 $("#json_return").css("display","block");
    			 $("#json_response").html(json_return);
    			
    			 $("#parse_json").css("display","block");
    			 $("#parse_response").css("display","none");
    		  }
    		});	
    }
    	else
    		alert("field cannot be empty");

    });
    $("#btn_parse").click(function(event){ 
    	var parameter = $("#searchparameter").val();
    	if(parameter.length>0)
    	{var apiurl ="rest/api/albums/byauthor?name="+parameter;
   
    	$.ajax({
    		  url: apiurl,
    		  success: function( data ) {
    			  $("#parse_response").css("display","block");
    			
    			  var album ="<br />";
    			   var i=0;
    			  data.forEach(function(item,i){
    				 i=i+1;
    				 album= album+ "album "+i+": <br />" + "title : "+ item.title+"<br />"+"composer : "+ item.Artist+"<br />"
    				  +"year : "+ item.year+"<br />"+"category : "+item.category+"<br /> <br />";
    				});
    			  $("#parse_response").html( album);
    		  }
    		});	
    }
    	else
    		alert("field cannot be empty");

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
