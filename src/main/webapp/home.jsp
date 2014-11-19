<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
<!-- Latest compiled and minified JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 40px;
}

/* Custom container */
.container-narrow {
	margin: 0 auto;
	max-width: 700px;
}

.container-narrow>hr {
	margin: 30px 0;
}

</style>
</head>
<body>
	<div class="container-narrow">
		<div class="masthead">
			<ul class="nav nav-pills pull-right">
				<li class="active"><a href="<c:url value="/home" />" >Home</a></li>
				<li><a href="#">About</a></li>
				<c:if test="${empty sessionScope.loggedInUser}">
					<li><a href="<c:url value="/signin"/>">Sign In</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.loggedInUser}">
					<li><a href="<c:url value="/signout"/>">Sign out</a></li>
				</c:if>
			</ul>
			<h3 class="muted">
				<a href="<c:url value="/" />">Spring Data Hbase</a>
			</h3>
		</div>
	</div>
</body>
</html>
