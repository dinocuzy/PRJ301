/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.Base64;
import model.User;
import userDao.IUserDAO;
import userDao.UserDAO;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private IUserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        String savedUser = null, savedPass = null;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    savedUser = URLDecoder.decode(c.getValue(), "UTF-8");
                }
                if ("password".equals(c.getName())) {
                    String decoded = URLDecoder.decode(c.getValue(), "UTF-8");
                    savedPass = new String(Base64.getDecoder().decode(decoded));
                }
            }
        }

        if (savedUser != null && savedPass != null) {
            User user = userDAO.login(savedUser, savedPass);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", user);
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        User user = userDAO.login(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);

            if ("on".equals(remember)) {
                String encodedUser = URLEncoder.encode(username, "UTF-8");
                String encodedPass = URLEncoder.encode(
                        Base64.getEncoder().encodeToString(password.getBytes()), "UTF-8"
                );

                Cookie u = new Cookie("username", encodedUser);
                Cookie p = new Cookie("password", encodedPass);

                u.setMaxAge(24 * 60 * 60);
                p.setMaxAge(24 * 60 * 60);

                resp.addCookie(u);
                resp.addCookie(p);
            }

            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("error", "Invalid username or password!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
