<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>compose</title>
</head>
<body>
<h1>Compose Message...</h1>
<hr>
<form align="center" action="sendMail" method="post">
to:<input type="email" name="email"  required="required"><br>
Message:<input type="text" name="uinbox"  placeholder="Type Here....">
<input type="submit" name=send>
</form>
</body>
</html>