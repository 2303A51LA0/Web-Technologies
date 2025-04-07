import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) session.invalidate();

        Cookie cookie = new Cookie("studentName", "");
        cookie.setMaxAge(0);  // Delete cookie
        res.addCookie(cookie);

        res.sendRedirect("login.jsp?error=You+have+logged+out+successfully");
    }
}



