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
        <title>Add computer</title>
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
                                    <h1 class="text-uppercase text-center mb-5">Add new computer</h1>
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

                                        <form:form action="/pcbuilder/computer/save" method="post" modelAttribute="computerDto" class="form-horizontal">

                                            <div class="form-group">
                                                <label for="computerName" class="col-sm-3 control-label">Name</label>
                                                <div class="col-sm-6">
                                                    <form:input id="computerName" class="form-control" type="text" name="computerName" path="computerName" placeholder="Name input ..."/>
                                                    <form:errors path="computerName" class="form-control alert-danger"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="usage" class="col-sm-3 control-label">Usage</label>
                                                <div class="col-sm-6">
                                                    <form:textarea id="usage" class="form-control" name="usage" path="usage" rows="4" cols="20" placeholder="Usage input ..."/>
                                                    <form:errors path="usage" class="form-control alert-danger"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label for="warranty" class="col-sm-3 control-label">Warranty</label>
                                                <div class="col-sm-5">
                                                    <form:input id="warranty" class="form-control" type="date" name="warranty" path="warranty"/>
                                                    <form:errors path="warranty" class="form-control alert-danger"/>
                                                </div>
                                            </div>

                                            <div class="form-group has-feedback">
                                                <label for="totalPrice" class="col-sm-3 control-label">Total Price</label>
                                                <div class="col-sm-3">
                                                    <form:input id="totalPrice" class="form-control" readonly="true" type="text" name="totalPrice" path="totalPrice"/>
                                                    <span class="glyphicon glyphicon-bitcoin form-control-feedback"></span>
                                                </div>
                                                <form:errors path="totalPrice" class="form-control alert-danger"/>
                                            </div>
                                            <hr>
                                            <div class="form-group text-center" >
                                                <label for="button" class="col-sm-3 control-label"></label>
                                                <button class="btn btn-success col-sm-6"><i class="glyphicon glyphicon-floppy-save"></i> Save Computer</button>
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
