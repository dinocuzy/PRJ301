<%-- 
    Document   : login
    Created on : Sep 23, 2025, 10:14:49 PM
    Author     : ASUS
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    // 🔹 Đọc cookie để gợi ý username nếu có Remember Me
    String savedUser = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("username".equals(c.getName())) {
                savedUser = c.getValue();
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card shadow-lg border-0" style="width: 400px;">
            <div class="card-header bg-dark text-white text-center">
                <h3 class="my-2">🔐 User Login</h3>
            </div>

            <div class="card-body p-4">
                <form method="post" action="login">
                    <!-- Username -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Username</label>
                        <input type="text" name="username" class="form-control" 
                               placeholder="Enter your username"
                               value="<%= savedUser.isEmpty() ? (request.getParameter("username") == null ? "" : request.getParameter("username")) : savedUser %>" 
                               required>
                    </div>

                    <!-- Password -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Password</label>
                        <input type="password" name="password" class="form-control" 
                               placeholder="Enter your password" required>
                    </div>

                    <!-- Remember Me -->
                    <div class="form-check mb-3">
                        <input class="form-check-input" type="checkbox" name="remember" id="remember">
                        <label class="form-check-label" for="remember">
                            Remember me
                        </label>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </form>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center mt-3 py-2">${error}</div>
                </c:if>

                <!-- Extra Links -->
                <div class="d-flex justify-content-between mt-3">
                    <a href="register.jsp" class="text-decoration-none">Create Account</a>
                    <a href="forgotPassword.jsp" class="text-decoration-none">Forgot Password?</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
