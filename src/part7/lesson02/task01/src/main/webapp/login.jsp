<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="login" placeholder="login"><br/>
    <input type="text" name="password" placeholder="password"><br/>
    <input type="hidden" name="from" value="${LOGIN_REDIRECT}">
    <input type="submit"/>
</form>
<span style="color:red">${ERROR_MESSAGE}</span>

</body>
</html>
