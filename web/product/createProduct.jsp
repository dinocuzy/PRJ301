<%-- 
    Document   : createProduct
    Created on : Sep 28, 2025
    Author     : ASUS
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create Product</title>
    </head>
    <body>
        <h2>Create New Product</h2>

        <form action="products?action=create" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" required></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="number" step="0.01" name="price" required></td>
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><textarea name="description" rows="4" cols="30"></textarea></td>
                </tr>
                <tr>
                    <td>Stock:</td>
                    <td><input type="number" name="stock" value="0" required></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Save">
            <a href="products">Cancel</a>
        </form>
    </body>
</html>
