<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>client</title>
        <link type="text/css" rel="stylesheet" href="resources/style.css">
    </head>
    <body>
    	<div class="about">
    		${aboutMe}
    	</div>
        <div class="value">
			${cookieName} has shared value: ${key} = ${value}
		</div>
    </body>
</html>
