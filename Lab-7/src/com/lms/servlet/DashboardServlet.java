package com.lms.servlet;

import com.lms.model.Student;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Validate session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userEmail") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String email = (String) session.getAttribute("userEmail");

        // Retrieve student details from ServletContext
        ServletContext context = getServletContext();
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");

        Student loggedInStudent = null;
        if (students != null) {
            for (Student student : students) {
                if (student.getEmail().equals(email)) {
                    loggedInStudent = student;
                    break;
                }
            }
        }

        if (loggedInStudent == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Retrieve student's name from cookies
        Cookie[] cookies = request.getCookies();
        String studentName = "Student";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("studentName")) {
                    studentName = cookie.getValue();
                    break;
                }
            }
        }

        // Generate response with Bootstrap-styled dashboard
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html lang='en'><head>");
        response.getWriter().println("<meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        response.getWriter().println("<title>Dashboard</title>");
        response.getWriter().println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
        response.getWriter().println("</head><body class='bg-light'>");

        response.getWriter().println("<div class='container mt-5'>");
        response.getWriter().println("<div class='card shadow p-4 mx-auto' style='max-width: 500px;'>");
        response.getWriter().println("<h2 class='text-center text-primary'>Welcome, " + studentName + "!</h2>");
        response.getWriter().println("<p class='text-center fs-5'>Course Enrolled: <strong>" + loggedInStudent.getCourse() + "</strong></p>");
        response.getWriter().println("<div class='text-center mt-4'>");
        response.getWriter().println("<a href='LogoutServlet' class='btn btn-danger'>Logout</a>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");
        response.getWriter().println("</div>");

        response.getWriter().println("</body></html>");
    }
}
