<%-- 
    Document   : searchUser
    Created on : Sep 23, 2025, 10:15:27 PM
    Author     : ASUS
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>🌐 Search User</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <h1 class="text-center text-primary">User Management</h1>
            <div class="text-center mb-4">
                <a href="users?action=list" class="btn btn-secondary">📋 List All Users</a>
                <a href="users?action=create" class="btn btn-success">➕ Add New User</a>
            </div>

            <!-- Search Form -->
            <div class="card shadow mx-auto mb-4" style="max-width: 600px;">
                <div class="card-header bg-info text-white text-center">
                    🔍 Search User
                </div>
                <div class="card-body">
                    <form action="users" method="get" class="d-flex">
                        <input type="hidden" name="action" value="search"/>
                        <input type="text" name="keyword" class="form-control me-2" placeholder="Enter name/email/country"/>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </form>
                </div>
            </div>

            <!-- Search Results -->
            <c:if test="${not empty searchResult}">
                <table class="table table-striped table-hover text-center shadow">
                    <caption class="caption-top text-center fs-4 fw-bold">Search Results</caption>
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>👤Username</th>
                            <th>📧Email</th>
                            <th>🌍Country</th>
                            <th>🎂 Date of Birth</th>
                            <th>⚙️Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${searchResult}">
                            <tr>
                                <td>${u.id}</td>
                                <td>${u.username}</td>
                                <td>${u.email}</td>
                                <td>${u.country}</td>
                                <td>${u.dob}</td>
                                <td>
                                    <a href="users?action=edit&id=${u.id}" class="btn btn-warning btn-sm">✏️ Edit</a>
                                    <a href="users?action=delete&id=${u.id}" class="btn btn-danger btn-sm" onclick="return confirm('Delete this user?')">🗑️ Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty searchResult}">
                <div class="alert alert-warning text-center mt-3">⚠️ No result found.</div>
            </c:if>
        </div>

        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
