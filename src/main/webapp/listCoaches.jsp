<%--
  Created by IntelliJ IDEA.
  User: ihormokrytskyi
  Date: 16.05.2020
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All Coaches</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
</head>
<body>
<div class="container container-default">

    <h1>Coaches List Page</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Awards</th>
            <th>Country ID</th>
            <th>User ID</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${coaches}" var="coach">
            <tr>
                <td><c:out value="${coach.id}" /></td>
                <td><c:out value="${coach.name}" /></td>
                <td><c:out value="${coach.awards}" /></td>
                <td><c:out value="${coach.countryId}" /></td>
                <td><c:out value="${coach.userId}" /></td>
                <td><a class="btn btn-primary" role="button"
                       href="CoachServlet?action=edit&id=<c:out value="${coach.id }"/>">Update</a>
                    <a class="btn btn-primary" role="button"
                       href="CoachServlet?action=delete&id=<c:out value="${coach.id }"/>">Delete</a>

                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
        <a href="CoachServlet?action=insert" class="btn btn-primary" role="button">Add Coach</a>
    </p>
</div>
</body>
</html>
