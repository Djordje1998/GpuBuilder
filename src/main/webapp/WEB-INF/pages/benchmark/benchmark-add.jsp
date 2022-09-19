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
        <title>Computer Benchmark</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="vertical-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row">
                                    <h1 class="text-uppercase text-center mb-5">Compare two computers</h1>
                                </div>
                                <hr>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-12">
                                        <c:if test = "${errorMessage!=null}">
                                            <div class="alert alert-danger">
                                                <strong>Form error!</strong> ${errorMessage}
                                            </div>
                                        </c:if>

                                        <form:form action="/pcbuilder/benchmark/save" method="post" modelAttribute="benchmarkDto" class="form-horizontal">
                                            <br/>
                                            <div class="form-group ">
                                                <label for="firstPcSelect" class="col-sm-4 control-label">First Computer</label>
                                                <div class="col-sm-7">
                                                    <form:select id="firstPcSelect" class="form-control" path="firstPc.computerId">
                                                        <c:forEach items="${computers}" var="computer">
                                                            <form:option 
                                                                value="${computer.computerId}" 
                                                                label="${computer.computerName} - ${computer.totalPrice}$" />
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <br/>
                                            <div class="form-group ">
                                                <label for="secondPcSelect" class="col-sm-4 control-label">Second Computer</label>
                                                <div class="col-sm-7">
                                                    <form:select id="secondPcSelect" class="form-control" path="secondPc.computerId">
                                                        <c:forEach items="${computers}" var="computer">
                                                            <form:option 
                                                                value="${computer.computerId}" 
                                                                label="${computer.computerName} - ${computer.totalPrice}$" />
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <br/>
                                            <div class="form-group ">
                                                <label for="test" class="col-sm-4 control-label">Stress Test</label>
                                                <div class="col-sm-7">
                                                    <form:select id="test" class="form-control" path="stressTest.testId">
                                                        <c:forEach items="${tests}" var="test">
                                                            <form:option value="${test.testId}" label="${test.testName}" />
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="form-group text-center" >
                                                 <label for="button" class="col-sm-3 control-label"></label>
                                                <button class="btn btn-success col-sm-6"><i class="glyphicon glyphicon-retweet"></i> Compare Computers</button>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
