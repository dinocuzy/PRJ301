/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import model.Product;
import model.CartItem;
import model.Cart;
import service.ProductService;
import service.ProductServiceImpl;
import service.CartService;
import service.CartServiceImpl;

@WebServlet(name = "CartServlet", urlPatterns = {"/carts"})
public class CartServlet extends HttpServlet {

    private ProductService productService;
    private CartService cartService;

    @Override
    public void init() {
        productService = new ProductServiceImpl();
        cartService = new CartServiceImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        request.setAttribute("cart", cart);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart/cart.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("productId"));
        switch (action) {
            case "add":
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                Product product = productService.selectProduct(productId);
                if (product != null) {
                    cartService.addToCart(session, product, quantity);
                }
                break;
            case "update":
                quantity = Integer.parseInt(request.getParameter("quantity"));
                cartService.updateQuantity(session, productId, quantity);
                break;
            case "remove":
                cartService.removeFromCart(session, productId);
                break;
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart/cart.jsp");
    }
}
