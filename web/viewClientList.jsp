<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Client"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Clients</title>
    </head>
    <body>
        <center>
            <a href="/newClient">Add New User</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="clientList">View Users</a>
        </center>
        <br/>
        <center>
            <h1>List of Clients</h1>
            <table cellpadding="5" border="1">
                <thead>
                    <th>Id</th>
                    <th>Agent Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Street Number</th>
                    <th>Street Name</th>
                    <th>City</th>
                    <th>Province</th>
                    <th>Postal Code</th>
                    <th>Telephone Number Office</th>
                    <th>Telephone Number Cell</th>
                    <th>Email</th>
                    <th>Company</th>
                    <th>Company Type</th>
                </thead>
                <tbody>
                
                <c:forEach var="client" items="${clientList}">
                    <tr>
                        <td><c:out value="${client.getId()}"/></td>
                        <td><c:out value="${client.getAgentId()}"/></td>
                        <td><c:out value="${client.getFirstName()}"/></td>
                        <td><c:out value="${client.getLastName()}"/></td>
                        <td><c:out value="${client.getStreetNumber()}"/></td>
                        <td><c:out value="${client.getStreetName()}"/></td>
                        <td><c:out value="${client.getCity()}"/></td>
                        <td><c:out value="${client.getProvince()}"/></td>
                        <td><c:out value="${client.getPostalCode()}"/></td>
                        <td><c:out value="${client.getTelOffice()}"/></td>
                        <td><c:out value="${client.getTelCell()}"/></td>
                        <td><c:out value="${client.getEmail()}"/></td>
                        <td><c:out value="${client.getCompany()}"/></td>
                        <td><c:out value="${client.getCompanyType()}"/></td>
                        <td>
                            <a href="editClient?id=<c:out value='${client.getId()}'/>">
                                Edit
                            </a>
                                &nbsp;&nbsp;&nbsp;
                            <a href="deleteClient?id=<c:out value='${client.getId()}'/>">
                                Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </center>
    </body>
</html>
