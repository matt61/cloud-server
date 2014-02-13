<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring 3 MVC Series - Contact Manager</title>
</head>
<body>
	<h2>Contact Manager</h2>

	<form:form method="POST" commandname="user">
			<form:label path="firstName">Name:</form:label>
			<form:input path="firstName"></form:input>
		<input type="submit" value="Add Contact" />
	</form:form>
</body>
</html>