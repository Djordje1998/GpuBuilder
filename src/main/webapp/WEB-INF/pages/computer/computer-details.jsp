<%-- 
    Document   : computer-list
    Created on : Feb 16, 2022, 2:38:32 AM
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Computer Details</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">
            <h1>Computer Details And Computer Items</h1>
            <c:if test = "${errorMessage!=null}">
                <div class="alert alert-danger">
                    <strong>Form error!</strong> ${errorMessage}
                </div>
            </c:if>
            <c:if test = "${successMessage!=null}">
                <div class="alert alert-success">
                    <strong>Success!</strong> ${successMessage}
                </div>
            </c:if>
            <table class="table table-hover table-striped">
                <div class="navbar-form navbar-right">
                    <a href="/pcbuilder/computer/download/excel/${computerDto.computerId}" class="btn btn-primary btn-sm"
                       style="background-color:#1D6F42;">
                        Download Excel <i class="fa fa-file-excel-o"></i>
                    </a>
                </div>

                <thead>
                    <tr>
                        <th>
                            <b>Computer Details</b>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> <b>Computer ID: </b> ${computerDto.computerId}</td>
                    </tr>
                    <tr>
                        <td><b>Name: </b> ${computerDto.computerName}</td>
                    </tr>
                    <tr>
                        <td><b>Usage: </b> ${computerDto.usage}</td>
                    </tr>
                    <tr>
                        <td><b>Warranty: </b> ${computerDto.warranty}</td>
                    </tr>
                    <tr>
                        <td><b>Total Price: </b> ${computerDto.totalPrice} <span class="glyphicon glyphicon-bitcoin"></span></td>
                    </tr>
                    <tr>
                        <td>
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th> <b> <span class="glyphicon glyphicon-list"></span> Item List:</b></th>
                                    </tr>
                                    <tr>
                                        <th>Serial Number</th>
                                        <th>Component Name</th>
                                        <th>Price</th>
                                        <th></th>
                                        <th>Amount</th>
                                        <th></th>
                                        <th>Total Item Price</th>
                                        <th>Date of Creation</th>
                                        <th>Creator Username</th>
                                            <% if (session.getAttribute("role").equals("ADMIN")) { %>
                                        <th>Action</th>
                                            <% } %>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${computerDto.items}" var="item">
                                        <tr>
                                            <td>${item.serialNumber}</td>
                                            <td>${item.component.componentName}</td>
                                            <td>${item.itemPrice}<span class="glyphicon glyphicon-bitcoin"></span></td>
                                            <td>x</td>
                                            <td><span class="badge">${item.amount} pcs</span></td>
                                            <td>=</td>
                                            <td>${item.totalItemPrice}<span class="glyphicon glyphicon-bitcoin"></span></td>
                                            <td>${item.createdDate}</td>
                                            <td>${item.createdBy.username}</td>
                                            <% if (session.getAttribute("role").equals("ADMIN")) { %>
                                            <td>
                                                <a href="/pcbuilder/admin/computer/deleteItem/${item.computerId}/${item.serialNumber}" class="btn btn-danger btn-xs">
                                                    <span class="glyphicon glyphicon-trash "></span> Delete Item
                                                    <span class="glyphicon glyphicon-flash "></span>
                                                </a>
                                            </td>
                                            <% }%>
                                        </tr>                    
                                    </c:forEach>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br/>
            <a href="/pcbuilder/computer/${computerDto.computerId}/addItem" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span> Add Item
            </a>
        </div>
    </body>
</html>
