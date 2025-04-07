import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class RegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String course = req.getParameter("course");

        Student student = new Student(name, email, password, course);
        ServletContext context = getServletContext();

        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(student);
        context.setAttribute("students", students);
        res.sendRedirect("login.jsp");
    }
}
