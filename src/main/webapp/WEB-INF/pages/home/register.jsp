<%-- 
    Document   : user-add
    Created on : Aug 10, 2022, 9:20:47 AM
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
        <style>
            .vertical-center {
                min-height: 85vh; 

                display: flex;
                align-items: center;
            }
            body {
                margin: 0;
                background-image: url("https://maxonnet.imgix.net/images/maxon/Universe_Screen_Text_Hero.jpg?crop=focalpoint&domain=maxonnet.imgix.net&fit=crop&fm=png&fp-x=0.5&fp-y=0.5&h=540&ixlib=php-3.3.0&q=82&w=960&s=2072c4c91875f6622ae80e55fb85e89f");
                background-color: #000;
                background-position: cover;
                background-repeat: no-repeat;
                background-size: cover;
                position: relative;
                width: 100%;
                height: auto;
            }
        </style>
        <title>Registration</title>
    </head>
    <body>
        <% if (session.getAttribute("role") != null && session.getAttribute("role").equals("ADMIN")) { %>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true" />
        <% }%>
        <div class="vertical-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row">
                                    <h1 class="text-uppercase text-center mb-5">Registration Form</h1>
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
                                        <form:form action="/pcbuilder/registerDo" method="post" modelAttribute="userDto" class="form-horizontal">

                                            <div class="form-group">
                                                <label for="name" class="col-sm-4 control-label">First Name</label>
                                                <div class="col-sm-7">
                                                    <form:input id="name" class="form-control" type="text" name="name" path="name" placeholder="First Name input ..."/>
                                                    <form:errors path="name" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="surname" class="col-sm-4 control-label">Last Name</label>
                                                <div class="col-sm-7">
                                                    <form:input id="surname" class="form-control" type="text" name="surname" path="surname" placeholder="Last Name input ..."/>
                                                    <form:errors path="surname" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="email" class="col-sm-4 control-label">E-mail</label>
                                                <div class="col-sm-7">
                                                    <form:input id="email" class="form-control" type="text" name="email" path="email" placeholder="E-mail input ..."/>
                                                    <form:errors path="email" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="username" class="col-sm-4 control-label">Username</label>
                                                <div class="col-sm-7">
                                                    <form:input id="username" class="form-control" type="text" name="username" path="username" placeholder="Username input ..."/>
                                                    <form:errors path="username" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="password" class="col-sm-4 control-label">Password</label>
                                                <div class="col-sm-7">
                                                    <form:input id="password" class="form-control" type="password" name="password" path="password" placeholder="Password input ..."/>
                                                    <form:errors path="password" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="repeatPassword" class="col-sm-4 control-label">Repeat Password</label>
                                                <div class="col-sm-7">
                                                    <form:input id="repeatPassword" class="form-control" type="password" name="repeatPassword" path="repeatPassword" placeholder="Repeat Password input ..."/>
                                                    <form:errors path="repeatPassword" class="form-control alert-danger"/>
                                                </div>
                                            </div>
                                            <% if (session.getAttribute("role") != null && session.getAttribute("role").equals("ADMIN")) { %>

                                            <div class="form-group">
                                                <label for="role" class="col-sm-4 control-label">
                                                    User Role
                                                    <span class="glyphicon glyphicon-flash "></span>
                                                </label>
                                                <div class="col-sm-7">
                                                    <form:select id="role" class="form-control" path="profile.id">
                                                        <c:forEach items="${roles}" var="role">
                                                            <form:option value="${role.id}" label="${role.profileName}" />
                                                        </c:forEach>
                                                    </form:select>
                                                </div>
                                                <form:errors path="profile.id"/>
                                            </div>
                                            <%} else {%>

                                            <div class="form-group">
                                                <label for="role" class="col-sm-4 control-label">User Role</label>
                                                <div class="col-sm-5">
                                                    <form:select disabled="true" id="role" class="form-control" path="profile.id">
                                                        <form:option value="1" label="USER" />
                                                    </form:select>
                                                </div>
                                                <form:errors path="profile.id"/>
                                            </div>
                                            <%}%>
                                            <br/>
                                            <div class="form-group text-center" >
                                                <label for="button" class="col-sm-2 control-label"></label>
                                                <button class="btn btn-primary btn-lg col-sm-8"><i class="glyphicon glyphicon-user"></i> Register</button>
                                            </div>
                                            <br/>
                                            <% if (session.getAttribute("role") != null && session.getAttribute("role").equals("ADMIN")) { %>
                                            <%} else {%>
                                            <p class="text-center text-muted mt-5 mb-0">Have already an account? Login
                                                <a href="/pcbuilder/login" class="fw-bold text-body"><u> here!</u></a>
                                            </p>
                                            <%}%>
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
