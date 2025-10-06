<%-- 
    Document   : createUser
    Created on : Sep 23, 2025, 10:12:37 PM
    Author     : ASUS
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>🌐User Management Application</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="text-center text-primary">🌐User Management</h1>
    <div class="text-center mb-4">
        <a href="users?action=list" class="btn btn-secondary">⬅ Back to List</a>
    </div>

    <div class="card shadow mx-auto" style="max-width: 600px;">
        <div class="card-header bg-success text-white text-center">
            ➕ Add New User
        </div>
        <div class="card-body">
            <form method="post" action="users?action=create">
                <div class="mb-3">
                    <label class="form-label">👤Username</label>
                    <input type="text" name="username" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">📧Email</label>
                    <input type="email" name="email" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">🌍Country</label>
                    <input type="text" name="country" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">🎂Date of Birth</label>
                    <input type="date" name="dob" class="form-control"/>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">💾 Save</button>
                    <a href="users?action=list" class="btn btn-outline-danger">Cancel</a>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
