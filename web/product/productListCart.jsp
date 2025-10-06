<%-- 
    Document   : productListCart
    Created on : Oct 6, 2025, 2:16:16 PM
    Author     : ASUS
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh Sách Sản Phẩm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body class="bg-light">

        <div class="container py-4">
            <h2 class="text-center mb-4">🛒 Danh Sách Sản Phẩm</h2>
            <div class="text-end mb-3">
                <a href="cart" class="btn btn-primary">Xem Giỏ Hàng</a>
            </div>

            <div class="row">
                <c:forEach var="product" items="${products}">
                    <div class="col-md-3 mb-4">
                        <div class="card shadow-sm h-100">
                            <img src="${product.image}" class="card-img-top" alt="${product.name}" style="height:200px; object-fit:cover;">
                            <div class="card-body text-center">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text text-danger fw-bold">${product.price} VNĐ</p>
                                <form action="cart" method="post">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="number" name="quantity" value="1" min="1" class="form-control mb-2 text-center">
                                    <button type="submit" class="btn btn-success w-100">Thêm vào giỏ</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
