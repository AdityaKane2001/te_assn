import java.io.*;
import java.lang.*;
import javax.servlet.*;

public class Calculator extends GenericServlet {
    public void service(ServletRequest req, ServletResponse res) throws IOException, ServletException {

        res.setContentType("text/html");

        PrintWriter out = res.getWriter();

        // String a = req.getParameter("a");
        // String b = req.getParameter("b");

        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));
        int c = a + b;

        out.print("<html><body>");
        out.print("<b>hello calculator</b>");
        out.print("<p>" + a + " + " + b + " = " + c + "</p>");
        out.print("</body></html>");

    }
}
