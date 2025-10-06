/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;
import model.User;
import userDao.UserDAO;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        // 🔹 Nếu chưa có session, kiểm tra Remember Me (cookie)
        if (currentUser == null) {
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

            // 🔹 Nếu có cookie hợp lệ → xác thực lại trong DB
            if (savedUser != null && savedPass != null) {
                User user = new UserDAO().login(savedUser, savedPass);
                if (user != null) {
                    // 🔹 Tạo session mới và lưu user
                    session = req.getSession(true);
                    session.setAttribute("currentUser", user);
                    currentUser = user;
                    System.out.println("Auto-login successful via Remember Me: " + user.getUsername());
                } else {
                    System.out.println("Invalid cookie credentials, redirecting to login...");
                    resp.sendRedirect("login");
                    return;
                }
            } else {
                resp.sendRedirect("login");
                return;
            }
        }

        System.out.println("👤 Logged in user: " + currentUser.getUsername());

        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
