<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head><title>Hello :: Spring Application</title></head>
  <body>
    <c:forEach var="user" items="${users}" varStatus="counter">
	   Item <c:out value="${user.getFirstName()}"/><p>
	</c:forEach>
  </body>
</html>