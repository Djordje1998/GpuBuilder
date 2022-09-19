<%-- 
    Document   : component-type-list
    Created on : Feb 15, 2022, 11:17:03 PM
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
        <title>Component Types</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container col-md-4 col-md-offset-4">
            <h1>List of All Component Types</h1>
            <table class="table table-hover table-striped">
                <thead>
                    <tr class="info">
                        <th>ID</th>
                        <th>Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${types}" var="type">
                        <tr>
                            <td>${type.typeId}</td>
                            <td>${type.typeName}</td>
                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
