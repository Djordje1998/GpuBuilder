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
        <title>Benchmark List</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">
            <h1 class="text-uppercase text-center mb-5">List of Benchmarks</h1>
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
                <a href="/pcbuilder/benchmark/add" class="btn btn-success btn-lg">
                    <span class="glyphicon glyphicon-plus"></span> Add New Benchmark
                </a>
                <form class="navbar-form navbar-right" action="/pcbuilder/benchmark/search">
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
                        <th>I Computer Name</th>
                        <th>I Pc Score</th>
                        <th>II Computer Name</th>
                        <th>II Pc Score</th>
                        <th>Stress Test</th>
                        <th class="text-center">Benchmark Date</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${benchmarks}" var="benchmark">
                        <tr>
                            <td>${benchmark.benchmarkId}</td>
                            <td>${benchmark.firstPc.computerName}</td>
                            <td><b>${benchmark.scoreFirstPc}</b> pts</td>
                            <td>${benchmark.secondPc.computerName}</td>
                            <td><b>${benchmark.scoreSecondPc}</b> pts</td>
                            <td>${benchmark.stressTest.testName}</td>
                            <td class="text-center">${benchmark.createdDate}</td>
                            <td class="text-center">
                                <a href="/pcbuilder/benchmark/result/${benchmark.benchmarkId}" class="btn btn-info btn-sm">
                                    <span class="glyphicon glyphicon-info-sign"></span> Details
                                </a>
                                <% if (session.getAttribute("role").equals("ADMIN")) { %>
                                <a href="/pcbuilder/admin/benchmark/delete/${benchmark.benchmarkId}" class="btn btn-danger btn-sm">
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
