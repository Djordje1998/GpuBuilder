<%-- 
    Document   : user-list
    Created on : Feb 15, 2022, 10:13:17 PM
    Author     : LightPower
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>List of Users</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">

            <h1>List of all users</h1>

            <c:if test = "${errorMessage!=null}">
                <div class="alert alert-danger">
                    <strong>Error!</strong> ${errorMessage}
                </div>
            </c:if>

            <c:if test = "${successMessage!=null}">
                <div class="alert alert-success">
                    <strong>Success!</strong> ${successMessage}
                </div>
            </c:if>

            <table class="table table-hover table-striped">
                <thead>
                    <tr class="info">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>E-mail</th>
                        <th>Role</th>
                        <th class="text-center">Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.userId}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td><b>${user.username}</b></td>
                            <td>${user.password}</td>
                            <td>${user.email}</td>
                            <td>
                                <span class="badge">
                                    ${user.profile.profileName}
                                    <c:if test="${user.profile.id == 2}">
                                        <span class="glyphicon glyphicon-flash "></span>
                                    </c:if>
                                </span>
                            </td>
                            <td class="text-center">
                                <c:set var="current" value="${sessionScope.principal.userId == user.userId}" />
                                <c:if test="${current}">
                                    <a disabled="true" href="#" class="btn btn-success btn-sm">
                                        <span class="glyphicon glyphicon-record"></span>
                                        Current
                                    </a>
                                </c:if>
                                <c:if test="${!current}">
                                    <a href="/pcbuilder/admin/user/delete/${user.userId}" class="btn btn-danger btn-sm">
                                        <span class="glyphicon glyphicon-ban-circle"></span>
                                    </a>
                                </c:if>
                            </td>

                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
