<%-- 
    Document   : nav-bar
    Created on : Aug 23, 2022, 4:56:40 PM
    Author     : LightPower
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" style=" background : url(https://th.bing.com/th/id/R.2eb4d5795a03097394a99121cb032d0d?rik=lU7yBBGgWDmaiA&riu=http%3a%2f%2fgetwallpapers.com%2fwallpaper%2ffull%2f1%2fe%2fe%2f367299.jpg&ehk=8f8kJPQQbcjbr1Jr%2bntU%2b8RT0MC%2bJV8UDwWPB9%2bE6S4%3d&risl=&pid=ImgRaw&r=0)" 
               href="/pcbuilder/home">PcBuilder</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Computer <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/pcbuilder/computer">
                            <span class="glyphicon glyphicon-list-alt"></span>
                            Show All
                        </a>
                    </li>
                    <li><a href="/pcbuilder/computer/add">
                            <span class="glyphicon glyphicon-plus"></span>
                            Create New
                        </a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Components <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/pcbuilder/component">
                            <span class="glyphicon glyphicon-list-alt"></span>
                            Show All
                        </a>
                    </li>
                    <li><a href="/pcbuilder/component/add">
                            <span class="glyphicon glyphicon-plus"></span>
                            Add New
                        </a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Component Grades <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/pcbuilder/grade">
                            <span class="glyphicon glyphicon-list-alt"></span>
                            Show All
                        </a>
                    </li>
                    <li>
                        <a href="/pcbuilder/grade/add">
                            <span class="glyphicon glyphicon-plus"></span>
                            Create New
                        </a>
                    </li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    Pc Benchmarks <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/pcbuilder/benchmark">
                            <span class="glyphicon glyphicon-list-alt"></span>
                            Show All
                        </a>
                    </li>
                    <li><a href="/pcbuilder/benchmark/add">
                            <span class="glyphicon glyphicon-plus"></span>
                            Create New
                        </a>
                    </li>
                </ul>
            </li>
            <% if (session.getAttribute("role").equals("ADMIN")) { %>
            <li>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="glyphicon glyphicon-cog"></span> Admin Panel<span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/pcbuilder/admin/user">
                            <span class="glyphicon glyphicon-tasks"></span>
                            List Users
                        </a>
                    </li>
                    <li><a href="/pcbuilder/admin/type">
                            <span class="glyphicon glyphicon-tasks"></span>
                            List Component Types
                        </a>
                    </li>
                    <li><a href="/pcbuilder/admin/item">
                            <span class="glyphicon glyphicon-tasks"></span>
                            List Computer Items
                        </a>
                    </li>
                    <li><a href="/pcbuilder/register">
                            <span class="glyphicon glyphicon-user"></span>
                            Register New User
                        </a>
                    </li>
                </ul>
            </li>
            <%}%>
        </ul>

        <ul class="nav navbar-nav navbar-right">
            <button class="btn btn-link navbar-left navbar-btn" onclick="window.print()">
                <span class="glyphicon glyphicon-print"></span>
            </button>
            <li> 
                <a href="#">
                    Authority <span class="badge ">
                        <strong> 
                            <%=session.getAttribute("role")%> 
                            <% if (session.getAttribute("role").equals("ADMIN")) { %>
                            <span class="glyphicon glyphicon-flash "></span>
                            <%}%>
                        </strong>
                    </span>
                </a>
            </li>
            <li> 
                <a href="#">
                    <i class="glyphicon glyphicon-user"></i>
                    <%=session.getAttribute("name")%> <%=session.getAttribute("surname")%> (<%=session.getAttribute("email")%>)
                </a>
            </li>
            <li><a href="/pcbuilder/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
    </div>
</nav>
