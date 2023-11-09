<%--
  User: hum
  Date: 11/9/23
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.assetmanager.app.view.toolbars.Header" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<% response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<!doctype html>
<html lang=en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }

        .asset-container {
            margin: 0;
            padding: 2px;
            max-height: calc(100vh - 10px);
        }

        .logo {
            text-align: left;
            background-color: #343a40;
            color: white;
            padding: 2px;
        }

        .tm {
            font-size: 8px;
            vertical-align: inherit;
        }

        .card {
            border-radius: 10px;
            height: 200px;
        }

        .card:hover {
            transform: scale(1.1);
            transition: transform 0.3s ease;
            background-color: #f2f2f2;
            color: #000;

        }

        .card:hover ul {
            background-color: #f2f2f2;
            color: #000;
        }

        .navbar .navbar-nav > li > a {
            line-height: 45px;
        }

        body {
            background-color: #f5f5f5;
        }

        .profile-dropdown-menu {
            max-height: 100px;
            overflow-y: auto;
        }

        .profile-dropdown-menu .dropdown-item {
            font-size: 14px;
            padding: 4px 10px;
        }
    </style>
    <title></title>
</head>
<body>
<% if (StringUtils.isNotBlank((String) session.getAttribute("loggedInId"))) { %>
<%= new Header().menu(String.valueOf(request.getAttribute("activeUrl")))%>
<%= request.getAttribute("content")%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
<%} else response.sendRedirect("./");%>
</html>
                   
