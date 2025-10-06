<%-- 
    Document   : editProduct
    Created on : Sep 28, 2025, 9:30:34 PM
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Edit Product</title>
    </head>
    <body>
        <h2>Edit Product</h2>

        <form action="products?action=edit" method="post">
            <input type="hidden" name="id" value="${product.id}">

            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" value="${product.name}" required></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="number" step="0.01" name="price" value="${product.price}" required></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea name="description" rows="4" cols="30">${product.description}</textarea></td>
                </tr>
                <tr>
                    <td>Stock:</td>
                    <td><input type="number" name="stock" value="${product.stock}" required></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Update">
            <a href="products">Cancel</a>
        </form>
    </body>
</html>

