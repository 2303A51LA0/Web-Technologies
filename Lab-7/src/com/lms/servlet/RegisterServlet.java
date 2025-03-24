package com.lms.servlet;

import com.lms.model.Student;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String course = request.getParameter("course");

        Student student = new Student(name, email, password, course);

        ServletContext context = getServletContext();
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
        
        if (students == null) {
            students = new ArrayList<>();
        }
        
        students.add(student);
        context.setAttribute("students", students);

        response.sendRedirect("login.html");
    }
}
