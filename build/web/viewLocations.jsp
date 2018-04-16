<%-- 
    Document   : viewLocations.jsp
    Created on : Apr 11, 2018, 1:39:48 PM
    Authors    : Zachary Boyer, Pavel Kirillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Location"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Locations</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    </head>
    <body>
    <center><br><a class="button ripple" href="newLoc">Add New Location</a></center>
    <h1 class="h1TableHeader">List of locations</h1>
    <table class="tableEdited" cellpadding="5" border="1">
        <thead>
        <th>Id</th>
        <th>Name</th>
        <th>Cap</th>
    </thead>
    <tbody>
        <c:forEach var="location" items="${locList}">
            <tr>
                <td><c:out value="${location.id}"/></td>
                <td><c:out value="${location.locationName}"/></td>
                <td><c:out value="${location.distrCap}"/></td>
                <td>
                    <a class="button ripple" style="margin-right: 16px" href="editLoc?id=<c:out value='${location.id}'/>">
                        Edit
                    </a>
                    <a class="button ripple" href="deleteLoc?id=<c:out value='${location.id}'/>">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
