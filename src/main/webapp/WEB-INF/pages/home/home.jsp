<%-- 
    Document   : home
    Created on : Aug 10, 2022, 12:41:20 PM
    Author     : LightPower
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body {
                margin: 0;
                background-image: url("https://cdna.pcpartpicker.com/static/img/downloads/wallpapers/PCPartPickerCube_3840x2160.jpg");
                background-color: #000000;
                background-position: cover;
                background-repeat: no-repeat;
                background-size: cover;
                position: relative;
                width: 100%;
                height: auto;
            }
        </style>
        <title>Home Page</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true" />
        <div class="container">
            <!--<h1>Welcome to home page!</h1>-->

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

<!--            <table class="table table-striped">
                <tr>
                    <td>
                        <a href="/pcbuilder/component">List Components</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/component/add">Add Components</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/type" >List Component Type</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/computer" >List Computers</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/computer/add" >Add Computer</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/item" >List Computer Items</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/user" >List Users</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/user/add" >Add User</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/grade/add" >Add Grade</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/grade" >List Grade</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/benchmark/add" >Add Benchmark</a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <a href="/pcbuilder/benchmark" >List Benchmark</a>
                    </td>
                </tr>
            </table>-->
        </div>
    </body>
</html>
