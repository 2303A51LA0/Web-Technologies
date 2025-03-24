import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Store registered users (Email -> Password)
    public static HashMap<String, String> users = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String department = request.getParameter("department");

        if (users.containsKey(email)) {
            out.println("<h3>User already registered. Try logging in.</h3>");
        } else {
            users.put(email, password);
            out.println("<h3>Registration Successful! <a href='login.html'>Login here</a></h3>");
        }
    }
}
