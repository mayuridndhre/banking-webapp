<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Error</title></head>
<body>
<h2 style="color:red;"><%= request.getAttribute("message") %></h2>
<a href="register.jsp">Try Again</a>
</body>
</html>
