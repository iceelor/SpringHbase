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
		<c:if test="${not empty serverList}">
			<c:forEach items="${serverList}" var="server">
				<p>${server.hashCode}</p>
				<p>${serverInfo.ip }</p>
				<p>${serverInfo.ports}</p>
				<hr>
			</c:forEach>
		</c:if>
	</div>
</body>
</html>