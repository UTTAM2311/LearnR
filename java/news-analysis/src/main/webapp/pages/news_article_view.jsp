
<%@page import="com.imaginea.dc.entities.Cause"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title> DeathCluster </title>
	<script type="text/javascript">
	$(document).ready(function() {
		
	});
	</script>
</head>
<body>
	
	<div class="tert-header">
		<div class="wrapper">
			<ul>
				<li class="pressed">
					<a href="<%= request.getContextPath() %>/feed/list">All Articles</a>
				</li>
				<li>
					<a href="<%= request.getContextPath() %>/feed/unlabelled">Unlabelled Articles</a>
				</li>
				<li>
					<a href="<%= request.getContextPath() %>/feed/labelled">Labelled Articles</a>
				</li>
			</ul>
		</div>
	</div>
	
	

	<div id="main-cont">
		<div class="wrapper">
		
			<div class="mc-rounded">
				<div>
					<a href="<%= request.getContextPath() %>/feed/view?pkey=${newsArticle.pkey - 1}"> PrevArticle </a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					<a href="<%= request.getContextPath() %>/feed/edit?pkey=${newsArticle.pkey}"> Edit this Article </a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					<a href="<%= request.getContextPath() %>/feed/view?pkey=${newsArticle.pkey + 1}"> NextArticle </a>
				</div>
				
				<!-- Application Form -->
					
				<div class="general-form" id="newsArticle-form">
					<ul>
						<li>
							<label class="name">Article : </label>
							<label> ${newsArticle.source} / ${newsArticle.publisher} - <a href="${newsArticle.url}" target="_blank"> ${newsArticle.title} </a> </label>
						</li>
						<li>
							<label class="name">Description :</label>
							<label> ${newsArticle.description} </label>
						</li>
						
						
						<li>
							<label class="name">Is Positive :</label>
							<label> ${newsArticle.isPositive} </label>
							
							<!-- value="${newsArticle.isPositive}" -->
						</li>
						<li>
							<label class="name">Cause :</label>
							<label>${newsArticle.cause}</label>
						</li>
						<li>
							<label class="name">Location :</label>
							<label>${newsArticle.location}</label>
						</li>
						<li>
							<label class="name">Death Count : </label>
							<label>${newsArticle.deathCount}</label>
						</li>
						
						<li>
							<h4>Content :</h4>
							<p rows="15" cols="130" name="content"> ${newsArticle.content}</p>
						</li>
						
					</ul>
				</div>
					
					
					
			</div>

		</div>
	</div>
	
</body>
</html>