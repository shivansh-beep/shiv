<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<center><h1>Sent Mails</h1></center>
<h3><center><table border='' ><tr><th style="color: red">Email ID</th><th style="color: red">Messages</th></tr>
<c:forEach var="mdto" items="${mlist}">
<tr><td><center>${mdto.getUto() }</center></td>
<td><center>${mdto.getUinbox() }</center></td></tr>
</c:forEach>
</table>


</body>
</html>