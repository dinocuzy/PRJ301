/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import service.UserService;
import service.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() {
        userService = new UserServiceImpl();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>UserServlet running at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                try {
                    deleteUser(req, resp);
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
                break;
            case "search":
                searchUser(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
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
                    insertUser(req, resp);
                    break;
                case "edit":
                    updateUser(req, resp);
                    break;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            throw new ServletException(e);
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> list = userService.selectAllUsers();
        req.setAttribute("listUsers", list);
        req.getRequestDispatcher("user/listUser.jsp").forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("user/createUser.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User existing = userService.selectUser(id);
        req.setAttribute("user", existing);
        req.getRequestDispatcher("user/editUser.jsp").forward(req, resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String country = req.getParameter("country");
        String role = "user";
        boolean status = true;
        String password = "123456";
        String dobStr = req.getParameter("dob");
        LocalDate dob = (dobStr != null && !dobStr.isEmpty()) ? LocalDate.parse(dobStr) : null;

        User u = new User(0, username, email, country, role, status, password, dob);
        userService.insertUser(u);

        List<User> list = userService.selectAllUsers();
        req.setAttribute("listUsers", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("user/listUser.jsp");
        dispatcher.forward(req, resp);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String country = req.getParameter("country");

        String dobStr = req.getParameter("dob");
        LocalDate dob = (dobStr != null && !dobStr.isEmpty()) ? LocalDate.parse(dobStr) : null;

        User oldUser = userService.selectUser(id);

        oldUser.setUsername(username);
        oldUser.setEmail(email);
        oldUser.setCountry(country);
        oldUser.setDob(dob);

        userService.updateUser(oldUser);

        List<User> list = userService.selectAllUsers();
        req.setAttribute("listUsers", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("user/listUser.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);

        List<User> list = userService.selectAllUsers();
        req.setAttribute("listUsers", list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("user/listUser.jsp");
        dispatcher.forward(req, resp);
    }

    private void searchUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<User> result = userService.searchUsers(keyword);
        req.setAttribute("searchResult", result);
        req.getRequestDispatcher("searchUser.jsp").forward(req, resp);
    }
}
