<%-- 
    Document   : user-add
    Created on : Aug 10, 2022, 9:20:47 AM
    Author     : LightPower
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Register new user</title>
    </head>
    <body>
        <jsp:include page="../../fragments/nav-bar.jsp" flush="true"/>
        <h1>Fill form to register new user!</h1>
        <div>
            ${errorMessage}
        </div>
        <form:form action="/pcbuilder/user/save" method="post" modelAttribute="userDto">
            <table>
                <tbody>
                    <tr>
                        <td>Name:</td>
                        <td>
                            <form:input  type="text" name="name" path="name"></form:input>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <form:errors path="name"></form:errors>
                            </td>
                        </tr>

                        <tr>
                            <td>Surname:</td>
                            <td>
                            <form:input  type="text" name="surname" path="surname"></form:input>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <form:errors path="surname"></form:errors>
                            </td>
                        </tr>

                        <tr>
                            <td>Username:</td>
                            <td>
                            <form:input  type="text" name="username" path="username"></form:input>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <form:errors path="username"></form:errors>
                            </td>
                        </tr>

                        <tr>
                            <td>Password:</td>
                            <td>
                            <form:input  type="password" name="password" path="password"></form:input>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <form:errors path="password"></form:errors>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="save">
                            </td>
                        </tr>
                    </tbody>
                </table>
        </form:form>
    </body>
</html>
