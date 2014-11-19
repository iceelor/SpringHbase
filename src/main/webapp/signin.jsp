<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<title>Sign In</title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />" >
<!-- Latest compiled and minified JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />" ></script>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"],.form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}

.form-signin a {
	font-size: 14px;
	height: auto;
	margin-left: 20px;
}
</style>
</head>
<body>
	<div class="container">
		<form:form method="post" modelAttribute="loginUser" class="form-signin">
			<h1 class="form-signin-heading">User Sign In</h1>
			<form:label path="mode">mode :</form:label>
			<form:select path="mode">
				<form:option value="O">Operation Mode</form:option>
				<form:option value="P">Performance Test Mode</form:option>
				<form:option value="T">Troubleshooting Mode</form:option>
				<form:option value="S">Service Manager Mode</form:option>
			</form:select>
			<form:label path="name">name :</form:label>
			<form:input path="name" class="input-block-level" />
			<form:label path="password">Password :</form:label>
			<form:password path="password" class="input-block-level" />
			<c:if test="${not empty error}">
				<form:label path="">Incorrect username or password.</form:label>
			</c:if>
			<input type="submit" value="Sign in" class="btn btn-large btn-success">
			<a href="signup">New to Blog ? Register Now</a>
		</form:form>
	</div>
</body>
</html>
