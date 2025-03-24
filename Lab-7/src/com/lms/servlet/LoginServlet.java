package com.lms.servlet;

import com.lms.model.Student;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ServletContext context = getServletContext();
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");

        if (students != null) {
            for (Student student : students) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", email);

                    // Fix: Encode student name to prevent invalid characters
                    String encodedName = URLEncoder.encode(student.getName(), StandardCharsets.UTF_8.toString());
                    Cookie nameCookie = new Cookie("studentName", encodedName);
                    response.addCookie(nameCookie);

                    response.sendRedirect("DashboardServlet");
                    return;
                }
            }
        }

        response.sendRedirect("login.html?error=Invalid credentials");
    }
}
