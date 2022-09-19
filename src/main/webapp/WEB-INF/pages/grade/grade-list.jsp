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
        <title>Component Grade List</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">

            <h1 class="text-uppercase text-center mb-5">List of component grades</h1>
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
                <a href="/pcbuilder/grade/add" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-plus"></span> Add New Component Grade
                </a>
                <form class="navbar-form navbar-right" action="/pcbuilder/grade/search">
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

            <div>
                <table class="table table-hover table-striped">
                    <thead>
                        <tr class="info">
                            <th>Component Name</th>
                            <th>Test Name</th>
                            <th>Score</th>
                                <% if (session.getAttribute("role").equals("ADMIN")) { %>
                            <th class="text-center">Action</th>
                                <% }%>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${grades}" var="grade">
                            <tr class="${((latest.component.componentId eq grade.component.componentId) and 
                                         (latest.stressTest.testId eq grade.stressTest.testId)) ? 'success' : ''}">
                                <td>${((latest.component.componentId eq grade.component.componentId) and 
                                      (latest.stressTest.testId eq grade.stressTest.testId)) ? 
                                      '<span class="glyphicon glyphicon-chevron-right"></span>' : ''}
                                      <b>${grade.component.componentName}</b>
                                    </td>
                                    <td>${grade.stressTest.testName}</td>
                                    <td><b>${grade.gradeValue}</b> points</td>
                                    <% if (session.getAttribute("role").equals("ADMIN")) { %>
                                    <td class="text-center">
                                        <a href="/pcbuilder/admin/grade/delete/${grade.component.componentId}/${grade.stressTest.testId}" 
                                           class="btn btn-danger btn-xs">
                                            <span class="glyphicon glyphicon-trash "></span> Delete
                                            <span class="glyphicon glyphicon-flash "></span>
                                        </a>
                                    </td>
                                    <% }%>
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
            </div>
        </body>
    </html>
