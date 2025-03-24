package com.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Invalidate the current session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Clear all cookies
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/"); // Ensure it applies to the whole app
                response.addCookie(cookie);
            }
        }

        // Redirect to login page with logout success message
        response.sendRedirect("login.html?message=You have been logged out successfully.");
    }
}
