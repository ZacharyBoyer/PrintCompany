<%-- 
    Document   : EditLocation
    Created on : Apr 11, 2018, 5:53:32 PM
    Authors    : Zachary Boyer, Pavel Kirillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
    </head>
    <body>
        <h1 class="h1TableHeader">Edit Location</h1>
        <form action="updateLoc" method="post" name="editForm">
            <table class="tableEdited" cellpadding="5" border="1">
                <tr>
                    <th>Location Id</th>
                    <td>
                        <input class="inputField ripple" type="text" readonly="true" name="id" id="id" value="${location.id}"/>
                    </td>
                </tr>
                <tr>
                    <th>Location name</th>
                    <td>
                        <input class="inputField ripple" type="text" name="name" id="name" value="${location.locationName}">
                    </td>
                </tr>
                <tr>
                    <th>Capacity</th>
                    <td><input class="inputField ripple" type="text" name="Capacity" id="Capacity" value="${location.distrCap}"></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="button ripple" type="submit" name="submit" value="Save"></td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
