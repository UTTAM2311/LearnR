
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
	</div>


	<div id="main-cont">
		<div class="wrapper">

		</div>
	</div>

</body>
</html>