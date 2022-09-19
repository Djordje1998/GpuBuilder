<%-- 
    Document   : computer-item-list
    Created on : Feb 16, 2022, 12:29:39 PM
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
        <title>Computer item add</title>
    </head>
    <body>
        <jsp:include page="../../../fragments/nav-bar.jsp" flush="true"/>
        <div class="container">
            <h1>Computer Info</h1>

            <table class="table table-hover table-striped">
                <thead>
                    <tr>
                        <th><b>Computer ID </b> ${computerDto.computerId}</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>Name: </b> ${computerDto.computerName}</td>
                    </tr>
                    <tr>
                        <td><b>Usage: </b> ${computerDto.usage}</td>
                    </tr>
                    <tr>
                        <td><b>Warranty: </b> ${computerDto.warranty} </td>
                    </tr>
                    <tr>
                        <td><b>Total Price: </b> ${computerDto.totalPrice}<span class="glyphicon glyphicon-bitcoin"></span></td>
                    </tr>
                </tbody>
            </table>
            <h2>Add Item</h2>
            <c:if test = "${errorMessage!=null}">
                <div class="alert alert-danger">
                    <strong>Form error!</strong> ${errorMessage}
                </div>
            </c:if>
            <form:form action="/pcbuilder/computer/addItem" method="post" modelAttribute="computerItemDto" class="form-horizontal">

                <form:input type="hidden" name="computerId" path="computerId"/>

                <div class="form-group">
                    <label for="serialNumber" class="col-sm-2 control-label">Serial Number</label>
                    <div class="col-sm-1">
                        <form:input id="serialNumber" class="form-control" readonly="true" type="text" name="serialNumber" path="serialNumber"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="componentSelect" class="col-sm-2 control-label">Component</label>
                    <div class="col-sm-4">
                        <form:select id="componentSelect" class="form-control" path="component.componentId">
                            <c:forEach items="${components}" var="component">
                                <form:option 
                                    value="${component.componentId}" 
                                    label="${component.componentName} 
                                    (${component.componentType.typeName}) - ${component.price}₿" />
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="itemPrice" class="col-sm-2 control-label">Price Per Unit</label>
                    <div class="col-sm-2">
                        <form:input id="itemPrice" class="form-control" type="text" name="itemPrice" path="itemPrice" placeholder="Price per unit ..."/>
                        <span class="glyphicon glyphicon-bitcoin form-control-feedback"></span>
                        <form:errors path="itemPrice" class="form-control alert-danger"/>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="amount" class="col-sm-2 control-label">Amount</label>
                    <div class="col-sm-2">
                        <form:input id="amount" class="form-control" type="text" name="amount" path="amount" placeholder="Amount input ..."/>
                        <span class="form-control-feedback"><b>pcs</b></span>
                        <form:errors path="amount" class="form-control alert-danger"/>
                    </div>
                </div>
                <div class="form-group" >
                    <button class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> Add Item</button>
                </div>

            </form:form>
        </div>
    </body>
</html>
