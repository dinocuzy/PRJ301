/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName()) || "password".equals(c.getName())) {
                    c.setMaxAge(0);
                    resp.addCookie(c);
                }
            }
        }

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
