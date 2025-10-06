<%-- 
    Document   : home
    Created on : Sep 23, 2025, 10:14:28 PM
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>

<%
    User current = (User) session.getAttribute("currentUser");
    if (current == null) {
        response.sendRedirect("login");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home | Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand fw-bold" href="#">🏠 Management System</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="users">👤 Users</a></li>
                    <li class="nav-item"><a class="nav-link" href="products">📦 Products</a></li>
                </ul>
                <span class="navbar-text text-white me-3">
                    👋 Hello, <strong><%= current.getUsername() %></strong>
                </span>
                <a href="logout" class="btn btn-outline-light btn-sm">Logout</a>
            </div>
        </div>
    </nav>

    <div class="container text-center mt-5">
        <h2>Welcome back, <%= current.getUsername() %>!</h2>
        <div class="d-flex justify-content-center mt-4 gap-3">
            <a href="users" class="btn btn-primary btn-lg px-4">👤 Manage Users</a>
            <a href="products" class="btn btn-success btn-lg px-4">📦 Manage Products</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
