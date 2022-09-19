<%-- 
    Document   : component-add
    Created on : Aug 8, 2022, 4:29:55 PM
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
        <title>Component</title>
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
                                    <h1 class="text-uppercase text-center mb-5">Component Details</h1>
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

                                        <c:if test = "${successMessage!=null}">
                                            <div class="alert alert-success">
                                                <strong>Success!</strong> ${successMessage}
                                            </div>
                                        </c:if>

                                        <form:form action="/pcbuilder/component/update" method="post" modelAttribute="componentDto" class="form-horizontal">

                                            <div class="form-group">
                                                <label for="componentName" class="col-sm-4 control-label">Name</label>
                                                <div class="col-sm-6">
                                                    <form:input disabled="true" id="componentName" class="form-control" type="text" name="componentName" path="componentName" placeholder="Name input ..."/>
                                                    <form:errors path="componentName" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <form:input type="hidden" name="componentId" path="componentId"/>
                                            <div class="form-group">
                                                <label for="componentTypeSelect" class="col-sm-4 control-label">Component Type</label>
                                                <div class="col-sm-3">
                                                    <form:select  disabled="true" id="componentTypeSelect" class="form-control" path="componentType.typeId">
                                                        <form:option value="${type.typeId}" label="${type.typeName}" />
                                                    </form:select>
                                                </div>
                                                <form:errors path="componentType.typeId"/>
                                            </div>
                                            <div class="form-group has-feedback">
                                                <label for="frequency" class="col-sm-4 control-label">Frequency</label>
                                                <div class="col-sm-6">
                                                    <form:input disabled="true" id="frequency" class="form-control" type="text" name="frequency" path="frequency" placeholder="Frequency input ..."/>
                                                    <span class="form-control-feedback"><b>Hz</b></span>
                                                    <form:errors path="frequency" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="description" class="col-sm-4 control-label">Description</label>
                                                <div class="col-sm-6">
                                                    <form:textarea disabled="true" id="description" class="form-control" name="description" path="description" rows="4" cols="20" placeholder="Descripton input ..."/> 
                                                    <form:errors path="description" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group has-feedback">
                                                <label for="price" class="col-sm-4 control-label">Price</label>
                                                <div class="col-sm-5">
                                                    <form:input disabled="true" id="price" class="form-control" type="text" name="price" path="price" placeholder="Price input ..."/>
                                                    <span class="glyphicon glyphicon-bitcoin form-control-feedback"></span>
                                                    <form:errors path="price" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <br>
                                            <a href="/pcbuilder/component" class="btn btn-default"><span class="glyphicon glyphicon-list-alt"></span> All Components</a>


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
