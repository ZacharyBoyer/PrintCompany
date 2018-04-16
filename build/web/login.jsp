<%-- 
    Document   : login
    Created on : 16.04.2018, 18:01:19
    Author     : Pavel Kirillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    </head>
    <body>
        <h1 class="h1TableHeader">Welcome to the Print Management System</h1>
        <br/><br/>
        <form id="loginForm" action="login" method="post" name="loginForm">
            Login:<input class="inputField ripple" type="text" name="login" required/><br/><br/>  
            Password:<input class="inputField ripple" type="password" name="password" required/><br/><br/>  
            <input class="button ripple" style="float:right" type="submit" name="submit" value="login"/> 
        </form>
    </body>
</html>
