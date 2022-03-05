import java.io.*;
import java.lang.*;
import javax.servlet.*;

public class First extends GenericServlet {
    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {

        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        String a = req.getParameter("a");
        String b = req.getParameter("b");

        out.print("<html><body>");
        out.print("<b>hello generic servlet</b>");
        out.print("<p>" + a + b + "</p>");
        out.print("</body></html>");

    }
}