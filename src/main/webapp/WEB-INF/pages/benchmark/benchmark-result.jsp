<%-- 
    Document   : computer-list
    Created on : Feb 16, 2022, 2:38:32 AM
    Author     : LightPower
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Benchmark Details</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">
            <h1 class="text-uppercase text-center mb-5">Details for Computer Benchmark</h1>
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

            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th><b>Computer Benchmark <span class="badge">ID ${benchmark.benchmarkId}</span></b></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th><b>First Computer:</b></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td> <b>ID: </b> ${benchmark.firstPc.computerId}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Name: </b> ${benchmark.firstPc.computerName}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Item Count: </b> ${benchmark.firstPc.items.size()} pcs</td>
                                    </tr>
                                    <tr>
                                        <td><b>Warranty: </b> ${benchmark.firstPc.warranty}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Total Price: </b> ${benchmark.firstPc.totalPrice} <span class="glyphicon glyphicon-bitcoin"></span></td>
                                    </tr>
                                    <tr>
                                        <td><b>Score: </b> <span class="badge">${benchmark.scoreFirstPc}</span></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a href="/pcbuilder/computer/details/${benchmark.firstPc.computerId}" class="btn btn-info btn-sm">
                                                <span class="glyphicon glyphicon-info-sign"></span> Details
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th><b>Second Computer:</b></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td> <b>ID: </b> ${benchmark.secondPc.computerId}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Name: </b> ${benchmark.secondPc.computerName}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Item Count: </b> ${benchmark.secondPc.items.size()}  pcs</td>
                                    </tr>
                                    <tr>
                                        <td><b>Warranty: </b> ${benchmark.secondPc.warranty}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Total Price: </b> ${benchmark.secondPc.totalPrice} <span class="glyphicon glyphicon-bitcoin"></span></td>
                                    </tr>
                                    <tr>
                                        <td><b>Score: </b><span class="badge">${benchmark.scoreSecondPc}</span></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a href="/pcbuilder/computer/details/${benchmark.secondPc.computerId}" class="btn btn-info btn-sm">
                                                <span class="glyphicon glyphicon-info-sign"></span> Details
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th><b>Stress Test:</b></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td> <b>Name: </b> ${benchmark.stressTest.testName}</td>
                                    </tr>
                                    <tr>
                                        <td><b>Description: </b> ${benchmark.stressTest.testDescription}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th><b>Benchmark Conclusion:</b></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="info">
                                        <td>${conclusion}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <hr>
            <a href="/pcbuilder/benchmark" class="btn btn-primary">
                <span class="glyphicon glyphicon-list-alt"></span> Benchmark List
            </a>
        </div>      
    </body>
</html>
