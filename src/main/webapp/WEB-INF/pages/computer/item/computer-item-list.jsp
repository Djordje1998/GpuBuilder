<%-- 
    Document   : computer-item-list
    Created on : Feb 16, 2022, 12:29:39 PM
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
        <title>Computer Items</title>
    </head>
    <body>
        <jsp:include page="../../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">
            <h1>List of Computer Items</h1>
            
            <c:if test = "${errorMessage!=null}">
                <div class="alert alert-danger">
                    <strong>Form error!</strong> ${errorMessage}
                </div>
            </c:if>
            
            <table class="table table-hover table-striped">
                <thead>
                    <tr class="info">
                        <th>Computer ID</th>
                        <th>Serial number</th>
                        <th>Component name</th>
                        <th>Price per unit</th>
                        <th>Amount</th>
                        <th>Total item price</th>
                        <th>Date of creation</th>
                        <th>Creator username</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${items}" var="item">
                        <tr>
                            <td>${item.computerId}</td>
                            <td>${item.serialNumber}</td>
                            <td>${item.component.componentName} [${item.component.componentId}]</td>
                            <td>${item.itemPrice}</td>
                            <td>${item.amount}</td>
                            <td>${item.totalItemPrice}</td>
                            <td>${item.createdDate}</td>
                            <td>${item.createdBy.username} [${item.createdBy.userId}]</td>
                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
