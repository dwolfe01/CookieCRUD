<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CookieCreateReadUpdateDelete</title>
        <link type="text/css" rel="stylesheet" href="resources/style.css">
    </head>
    <body>
        <h4>Cookie Create Read Update Delete</h4>
        <c:forEach items="${listOfCookies}" var="cookieAsString">
        <div class="cookieAsString">
			${cookieAsString}
		</div>
		<br />
		</c:forEach>
    </body>
</html>