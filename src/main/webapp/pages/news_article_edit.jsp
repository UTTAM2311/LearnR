
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
			</ul>
		</div>
	</div>
	
	

	<div id="main-cont">
		<div class="wrapper">
		
			<div class="mc-rounded">
				<div>
					<a href="<%= request.getContextPath() %>/feed/edit?pkey=${newsArticle.pkey - 1}"> PrevArticle </a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
					<a href="<%= request.getContextPath() %>/feed/edit?pkey=${newsArticle.pkey + 1}"> NextArticle </a>
				</div>
				
				<!-- Application Form -->
				<c:url value="/feed/save" var="feedSaveURL"></c:url>
				<form:form modelAttribute="newsArticle" method="post" action="${feedSaveURL}" id="newsArticle-edit-form">
					<input type="hidden" value="${newsArticle.pkey}" name="pkey"/>
					<input type="hidden" value="${newsArticle.version}" name="version"/>
					
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
								<h4>Content :</h4>
								<textarea rows="15" cols="130" name="content"> ${newsArticle.content}</textarea>
							</li>
							
							<li>
								<div>
									<label class="name" for="isPos-true"> +Ve Example </label>
									<input type="radio" id="isPos-true" name="isPositive" value="true">
								</div>
								<div>
									<label class="name" for="isPos-false"> -Ve Example </label>
									<input type="radio" id="isPos-false" name="isPositive" value="false">
								</div>
								
								<!-- value="${newsArticle.isPositive}" -->

							</li>
							<li>
								<label class="name">Cause </label>
								<select name="cause">
									<c:forEach items="<%= Cause.values() %>" var="cause">
										<option value="${cause}">${cause}</option>
									</c:forEach>								
								</select>
							</li>
							<li>
								<label class="name">Location : </label>
								<input type="text" name="location" value="${newsArticle.location}" >
							</li>
							<li>
								<label class="name">Death Count : </label>
								<input type="text" name="deathCount" value="${newsArticle.deathCount}" >
							</li>
							
						</ul>
					</div>
					
					<div class="btn-cont">
						<input type="submit" value="save" name="save" class="btn-color" id="app-save-btn"/>
						<a class="btn-gray" href="<%= request.getContextPath() %>/feed/unlabelled"> Cancel </a>
					</div>
					
				</form:form>
					
			</div>

		</div>
	</div>
	
</body>
</html>