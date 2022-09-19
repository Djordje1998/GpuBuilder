<%-- 
    Document   : login
    Created on : Aug 10, 2022, 11:44:30 AM
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
                background-color: #000000;
                background-position: cover;
                background-repeat: no-repeat;
                background-size: cover;
                position: relative;
                width: 100%;
                height: auto;
            }
        </style>
        <title>Login</title>
    </head>
    <body>
        <div class="vertical-center">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3">
                        <div class="panel panel-login">
                            <div class="panel-heading">
                                <div class="row">
                                    <h1 class="text-uppercase text-center mb-5">Login Form</h1>
                                </div>
                                <hr>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-12">
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
                                        <form action="/pcbuilder/loginDo" method="post" class="form-horizontal">
                                            <div class="form-group">
                                                <label for="username" class="col-sm-3 control-label">Username</label>
                                                <div class="col-sm-8">
                                                    <input id="username" class="form-control" type="text" name="username" placeholder="Username input ..."/>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="password" class="col-sm-3 control-label">Password</label>
                                                <div class="col-sm-8">
                                                    <input id="password" class="form-control" type="password" name="password" placeholder="Password input ..."/>
                                                </div>
                                            </div>
                                            <br/>
                                            <div class="form-group text-center" >
                                                <label for="button" class="col-sm-3 control-label"></label>
                                                <button class="btn btn-primary btn-lg col-sm-6"><i class="glyphicon glyphicon-lock"></i> Login</button>
                                            </div>
                                            <br/>
                                            <p class="text-center text-muted mt-5 mb-0">Don't have an account? Register
                                                <a href="/pcbuilder/register" class="fw-bold text-body"><u> here!</u></a>
                                            </p>
                                        </form>
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
