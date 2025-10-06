<%-- 
    Document   : listProduct
    Created on : Sep 28, 2025, 9:22:35 PM
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
    <head>
        <title>Product List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"> 
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center text-primary mb-4">Product List</h1>

            <c:set var="pageSize" value="10"/>
            <c:set var="currentPage" value="${param.page != null ? param.page : 1}"/>
            <c:set var="start" value="${(currentPage - 1) * pageSize}"/>
            <c:set var="end" value="${start + pageSize}"/>
            <c:set var="totalProducts" value="${fn:length(listProducts)}"/>
            <c:set var="totalPages" value="${(totalProducts + pageSize - 1) / pageSize}"/>
            <div class="d-flex justify-content-between align-items-center mb-3">
                <form action="products" method="get" class="d-flex">
                    <input type="hidden" name="action" value="search"/>
                    <input type="text" name="keyword" class="form-control me-2" placeholder="🔍 Search by name"/>
                    <button type="submit" class="btn btn-success btn-custom">Search</button>
                </form>
            </div>
            <div class="table-container">
                <table class="table table-hover align-middle text-center">
                    <caption class="caption-top text-center fs-4"><h2>List of Products</h2></caption>
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Stock</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${listProducts}" varStatus="status">
                            <c:if test="${status.index >= start && status.index < end}">
                                <tr>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.price}</td>
                                    <td>${product.description}</td>
                                    <td>${product.stock}</td>   
                                    <td>
                                        <a href="products?action=edit&id=${product.id}" class="btn btn-warning btn-sm btn-custom">Edit</a> |
                                        <a href="products?action=delete&id=${product.id}" class="btn btn-danger btn-sm btn-custom" onclick="return confirm('Are you sure?');">Delete</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>

                <div style="margin-top: 10px;">
                    <c:if test="${currentPage > 1}">
                        <a href="products?page=${currentPage - 1}">Previous</a>
                    </c:if>

                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <c:choose>
                            <c:when test="${i == currentPage}">
                                <b>[${i}]</b>
                            </c:when>
                            <c:otherwise>
                                <a href="products?page=${i}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <a href="products?page=${currentPage + 1}">Next</a>
                    </c:if>
                </div>

                <br/>
                <a href="products?action=create" class="d-flex justify-content-between align-items-center mb-3">➕ Add New Product</a>
                <br/>
                <a href="home" class="btn btn-success btn-custom">Back to Home</a>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
