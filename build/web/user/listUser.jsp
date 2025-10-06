<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>🌐User Management Application</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center text-primary mb-4">🌐 User Management</h1> 

            <div class="d-flex justify-content-between align-items-center mb-3">
                <a href="users?action=create" class="btn btn-primary btn-custom">➕ Add New User</a>

                <form action="users" method="get" class="d-flex">
                    <input type="hidden" name="action" value="search"/>
                    <input type="text" name="keyword" class="form-control me-2" placeholder="🔍 Search by name/email/country"/>
                    <button type="submit" class="btn btn-success btn-custom">Search</button>
                </form>
            </div>

            <div class="table-container">
                <table class="table table-hover align-middle text-center">
                    <caption class="caption-top text-center fs-4"><h2>List of Users</h2></caption>
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>👤 Username</th>
                            <th>📧 Email</th>
                            <th>🌍 Country</th>
                            <th>🎂 Date of Birth</th>
                            <th>⚙️ Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${listUsers}">
                            <tr>
                                <td>${u.id}</td>
                                <td>${u.username}</td>
                                <td>${u.email}</td>
                                <td>${u.country}</td>
                                <td>${u.dob}</td>
                                <td>
                                    <a href="users?action=edit&id=${u.id}" class="btn btn-warning btn-sm btn-custom">✏️ Edit</a>
                                    <a href="users?action=delete&id=${u.id}" class="btn btn-danger btn-sm btn-custom"
                                       onclick="return confirm('Are you sure to delete this user?')">🗑️ Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="home" class="btn btn-success btn-custom">Back to Home</a>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
