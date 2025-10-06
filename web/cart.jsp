<%-- 
    Document   : cart
    Created on : Sep 23, 2025, 10:14:08 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<htm1>
    <head>
        <title>Cart</title>
    </head>
    <body>
        <h2>Your Cart</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="$ {cart} ">
                    <tr>
                        <td>${item.product.name}</td>
                        <td>${item.product.price}</td>
                        <td>${item.quantity}</td>
                        <td>${item.product.price * item. quantity}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <form action="checkout" method="post">
            <input type="submit" value="Payment in progress....">
        </form>
    </body>
</html>
