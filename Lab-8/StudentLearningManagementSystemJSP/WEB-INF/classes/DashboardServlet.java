import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DashboardServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("student") == null) {
            res.sendRedirect("login.jsp?error=Please+login+first");
            return;
        }

        Student student = (Student) session.getAttribute("student");
        Cookie[] cookies = req.getCookies();
        String name = student.getName();

        req.setAttribute("name", name);
        req.setAttribute("course", student.getCourse());
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        rd.forward(req, res);
    }
}
