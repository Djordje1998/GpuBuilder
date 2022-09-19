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
        <title>Grade component</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <div class="vertical-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row">
                                    <h1 class="text-uppercase text-center mb-5">Grade component</h1>
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

                                        <form:form action="/pcbuilder/grade/save" method="post" modelAttribute="gradeDto" class="form-horizontal">
                                            <br/>
                                            <div class="form-group ">
                                                <label for="componentSelect" class="col-sm-4 control-label">Component for evaluation</label>
                                                <div class="col-sm-5">
                                                    <form:select id="componentSelect" class="form-control" path="component.componentId">
                                                        <c:forEach items="${components}" var="component">
                                                            <form:option 
                                                                value="${component.componentId}" 
                                                                label="${component.componentName} - ${component.componentType.typeName}" />
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                            </div>
                                            <br/>
                                            <table  class="table table-hover">
                                                <thead>
                                                    <tr>
                                                        <th colspan="2"> <b>Select Stress Test</b></th>
                                                    </tr>
                                                    <tr class="info">
                                                        <th>Name</th>
                                                        <th>Description</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    
                                                    <c:forEach items="${tests}" var="test">
                                                        <tr>
                                                            <td>
                                                                <div>
                                                                    <label> <form:radiobutton checked="checked" path="stressTest.testId" value="${test.testId}"/> ${test.testName}</label>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div>
                                                                    ${test.testDescription}
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    <form:errors path="stressTest.testId" />
                                                </tbody>
                                            </table>
                                            <hr>
                                            <div class="form-group text-center" >
                                                <button class="btn btn-success col-sm-4 col-md-offset-4">
                                                    <span class="glyphicon glyphicon-dashboard"></span>
                                                     Calculate Grade
                                                </button>
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
