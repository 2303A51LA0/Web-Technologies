import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        ServletContext context = getServletContext();
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");

        if (students != null) {
            for (Student student : students) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    HttpSession session = req.getSession();
                    session.setAttribute("student", student);

                    Cookie cookie = new Cookie("studentName", student.getName());
                    res.addCookie(cookie);

                    res.sendRedirect("DashboardServlet");
                    return;
                }
            }
        }
        res.sendRedirect("login.jsp?error=Invalid+credentials");
    }
}
