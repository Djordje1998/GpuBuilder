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
        <title>Computer list</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">
            <h1 class="text-uppercase text-center mb-5">List of computers</h1>
            <hr>
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
            <div>
                <a href="/pcbuilder/computer/add" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-plus"></span> Add New Computer
                </a>
                <form class="navbar-form navbar-right" action="/pcbuilder/computer/search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search computer ..." name="search">
                        <div class="input-group-btn">
                            <button class="btn btn-default" type="submit">
                                <i class="glyphicon glyphicon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <br/>
            <table class="table table-hover table-striped">
                <thead>
                    <tr class="info">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Warranty</th>
                        <th>Total Price</th>
                        <th class="text-center">Items Count</th>
                        <th class="text-center">Actions</th>
                        <th class="text-center">Download</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${computers}" var="computer">
                        <tr>
                            <td>${computer.computerId}</td>
                            <td><b>${computer.computerName}</b></td>
                            <td>${computer.warranty}</td>
                            <td>${computer.totalPrice} <span class="glyphicon glyphicon-bitcoin"></span></td>
                            <td class="text-center"><span class="badge">${computer.items.size()} pcs</span></td>
                            <td class="text-center">
                                <a href="/pcbuilder/computer/details/${computer.computerId}" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-info-sign"></span> Details
                                </a>
                                <a href="/pcbuilder/computer/edit/${computer.computerId}" class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-pencil"></span> Edit
                                </a>

                                <% if (session.getAttribute("role").equals("ADMIN")) { %>
                                <a href="/pcbuilder/admin/computer/delete/${computer.computerId}" class="btn btn-danger btn-sm">
                                    <span class="glyphicon glyphicon-trash"></span> Delete
                                    <span class="glyphicon glyphicon-flash "></span>
                                </a>
                                <% } else { %>
                                <% }%>
                            </td>
                            <td class="text-center">
                                <a href="/pcbuilder/computer/download/excel/${computer.computerId}" class="btn btn-primary btn-sm"
                                   style="background-color:#1D6F42;">
                                    <i class="fa fa-file-excel-o"></i> Excel
                                </a>
                            </td>
                        </tr>                    
                    </c:forEach>
                </tbody>
            </table>
            <c:if test = "${info!=null}">
                <div class="alert alert-info">
                    <strong>Ooops!</strong> ${info}
                </div>
            </c:if>
            <hr>
        </div>
    </body>
</html>
