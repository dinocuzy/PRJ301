/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.CartItem;
import model.Order;
import model.Product;
import service.OrderService;
import service.OrderServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("cart/cart2.jsp"); // ví dụ nếu user truy cập trực tiếp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // ✅ Kiểm tra giỏ hàng rỗng
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart/cart2.jsp");
            return;
        }

        int userId = 1;
        double totalPrice = cart.getTotal();

        Order order = new Order(0, userId, totalPrice, "Pending");

        try {
            int orderId = orderService.createOrder(order);
            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem item = entry.getValue();
                Product product = productService.selectProduct(entry.getKey());

                orderService.addOrderDetail(orderId,
                        product.getId(),
                        item.getQuantity(),
                        product.getPrice());
            }

            session.removeAttribute("cart");
            response.sendRedirect("cart/success.jsp");

        } catch (SQLException ex) {
            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Lỗi khi xử lý đơn hàng: " + ex.getMessage());
            request.getRequestDispatcher("cart/cart2.jsp").forward(request, response);
        }
    }
}
