/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import utils.Validate;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init() {
        productService = new ProductServiceImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req
     * @param resp
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showCreateForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteProduct(req, resp);
                    break;
                case "search":
                    searchProduct(req, resp);
                    break;
                default:
                    listProducts(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    insertProduct(req, resp);
                    break;
                case "edit":
                    updateProduct(req, resp);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> list = productService.selectAllProducts();
        req.setAttribute("listProducts", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/listProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/createProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product existing = productService.selectProduct(id);
        req.setAttribute("product", existing);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/editProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertProduct(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");
        String stockStr = req.getParameter("stock");

        if (!Validate.checkString(name, 2, 100)
                || !Validate.checkPositiveDouble(priceStr)
                || !Validate.checkPositiveInt(stockStr)) {

            req.setAttribute("error", "Invalid input for product!");
            req.getRequestDispatcher("product/createProduct.jsp").forward(req, resp);
            return;
        }

        double price = Double.parseDouble(priceStr);
        int stock = Integer.parseInt(stockStr);

        Product p = new Product(name, price, description, stock);
        productService.insertProduct(p);

        resp.sendRedirect("products");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String description = req.getParameter("description");
        String stockStr = req.getParameter("stock");

        if (!Validate.checkString(name, 2, 100)
                || !Validate.checkPositiveDouble(priceStr)
                || !Validate.checkPositiveInt(stockStr)) {

            req.setAttribute("error", "Invalid input for product!");
            req.setAttribute("product", productService.selectProduct(id));
            req.getRequestDispatcher("product/editProduct.jsp").forward(req, resp);
            return;
        }

        double price = Double.parseDouble(priceStr);
        int stock = Integer.parseInt(stockStr);

        Product existing = productService.selectProduct(id);
        existing.setName(name);
        existing.setPrice(price);
        existing.setDescription(description);
        existing.setStock(stock);

        productService.updateProduct(existing);

        resp.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id);
        resp.sendRedirect("products");
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Product> result = productService.searchProducts(keyword);
        req.setAttribute("searchResult", result);
        req.getRequestDispatcher("product/listProduct.jsp").forward(req, resp);
    }

}
