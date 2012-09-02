<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head><title>Hotel reservation console</title></head>
<body>
<h1>Message : ${message}</h1>

<h1>msg: <%=request.getAttribute("message")%></h1>
</body>
</html>