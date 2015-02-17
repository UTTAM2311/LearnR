
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
					<a href="<%= request.getContextPath() %>/feed/list?page=${page}&size=${size}">All Articles</a>
				</li>
				<li>
					<a href="<%= request.getContextPath() %>/feed/unlabelled?page=${page}&size=${size}">Unlabelled Articles</a>
				</li>
				<li>
					<a href="<%= request.getContextPath() %>/feed/labelled?page=${page}&size=${size}">Labelled Articles</a>
				</li>
			</ul>
		</div>
		<div class="wrapper">
			<label>Unlabelled : </label> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<a href="<%= request.getContextPath() %>/feed/unlabelled?page=${page - 1}&size=${size}"> PrevPage </a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<a href="<%= request.getContextPath() %>/feed/unlabelled?page=${page + 1}&size=${size}"> NextPage </a>
		</div>
	</div>


	<div id="main-cont">
		<div class="wrapper">
			<table>
				<thead>
					<tr>
						<td> Pkey </td>
						<td> Label </td>
						<td> Death count </td>
						<td> Location </td>
						<td> HeadLine </td>
						<td> News Source </td>
						<td> Options </td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${newsArticles}" var="article">
						<tr>
							<td> ${article.pkey} </td>
							<td> ${article.isPositive} </td>
							<td> ${article.deathCount} </td>
							<td> ${article.location} </td>
							<td> <a href="${article.url}" target="_blank"> ${article.title} </a> </td>
							<td> ${article.source} </td>
							<td> <a href="<%= request.getContextPath() %>/feed/edit?pkey=${article.pkey}"> edit </a> </td>
							<td> <a href="<%= request.getContextPath() %>/feed/view?pkey=${article.pkey}"> view </a> </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>