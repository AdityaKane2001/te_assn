import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
// import org.apache.commons.io.*;

public class Delete extends HttpServlet {
    private String mymsg;

    public void init() throws ServletException {
        mymsg = "Http Servlet Demo";

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting up the content type of web page
        try {
            // Initialize all the information regarding
            // Database Connection

            response.setContentType("text/html");
            // Path filepath = Path.get();
            // String insert_html = Files.readString(path, StandardCharsets.US_ASCII);
            // Writing the message on the web page
            PrintWriter out = response.getWriter();
            // out.println("<h1>" + mymsg + "</h1>");
            // out.print(insert_html);
            out.println("<html>  ");
            out.println("    <body>");
            out.println("        <form action='/delete' method='post'>");
            out.println("            <label for='id'>Book ID</label>");
            out.println("            <input type='text' name='book_id' id='book_id'> <br>");
            out.println("            <input type='submit' value='Delete'>");
            out.println("        </form>");
            out.println("    </body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting up the content type of web page
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/books?characterEncoding=utf8";
            // Database name to access
            // String dbName = "books";
            String dbUsername = "root";
            String dbPassword = "Root123#";
            Class.forName(dbDriver);

            Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            PrintWriter out = response.getWriter();
            PreparedStatement st = conn
                    .prepareStatement(
                            "delete from ebookshop where book_id=?;");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer

            // st.setString(1, request.getParameter("book_name"));
            // st.setString(2, request.getParameter("author_name"));
            st.setInt(1, Integer.parseInt(request.getParameter("book_id")));
            // st.setInt(4, Integer.parseInt(request.getParameter("quantity")));
            // Same for second parameter
            // st.setString(2, request.getParameter("string"));

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();
            st.close();
            conn.close();

            redirectTo("crud_home?task=select", response);

        } catch (Exception e) {

            PrintWriter out = response.getWriter();
            out.println("<p style='color:red;'>Operation failed. Return home and try again</p>");
            out.println("<a href='/index.html'>Home</a>");
            // out.println(e.printStackTrace())
        }
    }

    public void redirectTo(String servlet, HttpServletResponse response) {
        // Set response content type
        response.setContentType("text/html");

        // New location to be redirected
        // String site = new String("http://www.photofuntoos.com");

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", servlet);
    }

    public void destroy() {
    }
}
