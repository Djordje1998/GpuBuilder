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
            <h2>Choose Component</h2>
            <div class="container">

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
                    <form class="navbar-form navbar-right" action="/pcbuilder/computer/${computerDto.computerId}/addItem">
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

                <form:form action="/pcbuilder/computer/addItem" method="post" modelAttribute="computerItemDto" class="form-horizontal">
                    <div>
                        

                    <form:input type="hidden" name="computerId" path="computerId"/>
                    <div class="form-group">
                        <label for="serialNumber" class="col-sm-2 control-label">Serial Number</label>
                        <div class="col-sm-1">
                            <form:input id="serialNumber" class="form-control" readonly="true" type="text" name="serialNumber" path="serialNumber"/>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label for="itemPrice" class="col-sm-2 control-label">Price Per Unit</label>
                        <div class="col-sm-2">
                            <form:input id="itemPrice" class="form-control" type="text" name="itemPrice" path="itemPrice" placeholder="Select component"/>
                            <span class="glyphicon glyphicon-bitcoin form-control-feedback"></span>
                            <form:errors path="itemPrice" class="form-control alert-danger"/>
                        </div>
                    </div>
                        
                    <div class="form-group has-feedback">
                        <label for="amount" class="col-sm-2 control-label">Amount</label>
                        <div class="col-sm-2">
                            <form:input id="amount" class="form-control input-number" type="text" name="amount" path="amount" value="1" min="1" max="10" placeholder="Amount input ..."/>
                            <span class="form-control-feedback"><b>pcs</b></span>
                            <form:errors path="amount" class="form-control alert-danger"/>
                        </div>
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="amount">
                                <span class="glyphicon glyphicon-plus"></span>
                            </button>
                            <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="amount">
                                <span class="glyphicon glyphicon-minus"></span>
                            </button>
                        </span>
                    </div>

                    <div class="form-group text-center" >
                        <button class="btn btn-success col-sm-4 col-md-offset-4">
                            Add Component
                        </button>
                    </div>
                        
                    <table class="table table-hover table-striped">
                        <thead>
                            <tr class="info">
                                <th>Component Name</th>
                                <th>Type</th>
                                <th>Frequency</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${components}" var="component">
                                <tr>
                                    <td>
                                        <b><label> <form:radiobutton onclick="alertText(${component.componentId})" id="radio" path="component.componentId" value="${component.componentId}"/> ${component.componentName}</label> </b>
                                    </td>
                                    <td>${component.componentType.typeName}</td>
                                    <td>${component.frequency} <b>Hz</b></td>
                                    <td><p id="price-${component.componentId}" style="display:inline-block">${component.price}</p>&nbsp;<span class="glyphicon glyphicon-bitcoin"></span></td>
                                </tr>                    
                            </c:forEach>

                        </tbody>
                    </table>
                    <c:if test = "${info!=null}">
                        <div class="alert alert-info">
                            <strong>Ooops!</strong> ${info}
                        </div>
                    </c:if>

                </form:form>
                <hr>
            </div>
        </div>
    </body>
    <script language="javascript">
        function alertText(id) {
            document.getElementById("itemPrice").value = document.getElementById("price-" + id).innerHTML;
        }

        $('.btn-number').click(function (e) {
            e.preventDefault();

            fieldName = $(this).attr('data-field');
            type = $(this).attr('data-type');
            var input = $("input[name='" + fieldName + "']");
            var currentVal = parseInt(input.val());
            if (!isNaN(currentVal)) {
                if (type == 'minus') {

                    if (currentVal > input.attr('min')) {
                        input.val(currentVal - 1).change();
                    }
                    if (parseInt(input.val()) == input.attr('min')) {
                        $(this).attr('disabled', true);
                    }

                } else if (type == 'plus') {

                    if (currentVal < input.attr('max')) {
                        input.val(currentVal + 1).change();
                    }
                    if (parseInt(input.val()) == input.attr('max')) {
                        $(this).attr('disabled', true);
                    }

                }
            } else {
                input.val(0);
            }
        });
        $('.input-number').focusin(function () {
            $(this).data('oldValue', $(this).val());
        });
        $('.input-number').change(function () {

            minValue = parseInt($(this).attr('min'));
            maxValue = parseInt($(this).attr('max'));
            valueCurrent = parseInt($(this).val());

            name = $(this).attr('name');
            if (valueCurrent >= minValue) {
                $(".btn-number[data-type='minus'][data-field='" + name + "']").removeAttr('disabled')
            } else {
                alert('Sorry, the minimum value was reached');
                $(this).val($(this).data('oldValue'));
            }
            if (valueCurrent <= maxValue) {
                $(".btn-number[data-type='plus'][data-field='" + name + "']").removeAttr('disabled')
            } else {
                alert('Sorry, the maximum value was reached');
                $(this).val($(this).data('oldValue'));
            }


        });
        $(".input-number").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
                    // Allow: Ctrl+A
                            (e.keyCode == 65 && e.ctrlKey === true) ||
                            // Allow: home, end, left, right
                                    (e.keyCode >= 35 && e.keyCode <= 39)) {
                        // let it happen, don't do anything
                        return;
                    }
                    // Ensure that it is a number and stop the keypress
                    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                        e.preventDefault();
                    }
                });
    </script>
</html>
