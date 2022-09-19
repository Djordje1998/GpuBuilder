<%-- 
    Document   : component-list
    Created on : Feb 15, 2022, 12:14:04 PM
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
        <title>Component List</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">

            <h1 class="text-uppercase text-center mb-5">List of Components</h1>
            <hr>
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

            <div>
                <a href="/pcbuilder/component/add" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-plus"></span> Add New Component
                </a>
                <form class="navbar-form navbar-right" action="/pcbuilder/component/search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Search component ..." name="search">
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
                        <th>Component Name</th>
                        <th>Type</th>
                        <th>Frequency</th>
                        <th class="text-center">Description</th>
                        <th>Price</th>
                        <th class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${components}" var="component">
                        <tr class="${(latest.componentId eq component.componentId)? 'success' : ''}">
                            <td>
                                ${(latest.componentId eq component.componentId)? 
                                  '<span class="glyphicon glyphicon-chevron-right"></span>' : ''}
                                  ${component.componentId}
                                </td>
                                <td><b>${component.componentName}</b></td>
                                <td>${component.componentType.typeName}</td>
                                <td>${component.frequency} <b>Hz</b></td>
                                <td>${component.description}</td>
                                <td>${component.price} <span class="glyphicon glyphicon-bitcoin"></span></td>
                                <td class="text-center">
                                    <a href="/pcbuilder/component/detail/${component.componentId}" class="btn btn-info btn-xs">
                                        <span class="glyphicon glyphicon-info-sign"></span> Details
                                    </a>
                                    <a href="/pcbuilder/component/edit/${component.componentId}" class="btn btn-primary btn-xs">
                                        <span class="glyphicon glyphicon-pencil"></span> Edit
                                    </a>
                                    <% if (session.getAttribute("role").equals("ADMIN")) { %>
                                    <a href="/pcbuilder/admin/component/delete/${component.componentId}" class="btn btn-danger btn-xs">
                                        <span class="glyphicon glyphicon-trash"></span> Delete
                                        <span class="glyphicon glyphicon-flash "></span>
                                    </a>
                                    <% }%>
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
