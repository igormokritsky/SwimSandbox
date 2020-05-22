<%--
  Created by IntelliJ IDEA.
  User: ihormokrytskyi
  Date: 16.05.2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add/Edit New Student</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
</head>
<body>
<div class="container container-default">

<h1>Add/Edit Coach Page</h1>
    <div class="well">
        <form action="CoachServlet" method="post">
            <div class="form-group row">
                <label for="id" class="col-2 col-form-label">Coach ID</label>
                <div class="col-10">
                    <input type="text" class="form-control"
                           name="id" value="<c:out value="${coach.id}" />"
                           readonly="readonly" placeholder="Read only field" />
                </div>
            </div>
            <div class="form-group row">
                <label for="name" class="col-2 col-form-label">Name</label>
                <div class="col-10">
                    <input class="form-control" type="text" name="name"
                           value="<c:out value="${coach.name}"/>" id="name"
                           placeholder="Write here">
                </div>
            </div>
            <div class="form-group row">
                <label for="awards" class="col-2 col-form-label">Awards</label>
                <div class="col-10">
                    <input class="form-control" type="text" name="awards"
                           value="<c:out value="${coach.awards}"/>" id="awards"
                           placeholder="Write here">
                </div>
            </div>

            <div class="form-group row">
                <label for="countryId" class="col-2 col-form-label">Country ID</label>
                <div class="col-10">
                    <input class="form-control" type="text" name="countryId"
                           value="<c:out value="${coach.countryId}"/>" id="countryId"
                           placeholder="Write here">
                </div>
            </div>
            <div class="form-group row">
                <label for="userId" class="col-2 col-form-label">User ID</label>
                <div class="col-10">
                    <input class="form-control" type="text" name="userId"
                           value="<c:out value="${coach.userId}"/>" id="userId"
                           placeholder="Write here">
                </div>
            </div>

            <div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>

        </form>
    </div>
</div>
</body>
</html>
