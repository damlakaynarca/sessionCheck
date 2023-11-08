WebServlet=""
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1>Login Page</h1>
<form action="SessionCheck" method="post">
    <p>Username: <input type="text" name="username"></p>
    <p>Password: <input type="password" name="password"></p>
    <p><input type="submit" value="Login"></p>
</form>
</body>
</html>

