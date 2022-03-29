import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.nio.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.util.*;
// import org.apache.commons.io.*;

public class Insert extends HttpServlet {
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
            out.println("        <form action='/insert' method='post'>");
            out.println("            <label for='id'>Book Name</label>");
            out.println("            <input type='text' name='book_name' id='book_name'> <br>");
            out.println("            <label for='author_name'>Author Name</label>");
            out.println("            <input type='text' id='author_name' name='author_name'><br>");
            out.println("            <label for='price'>Price</label>");
            out.println("            <input type='text' id='price' name='price'><br>");
            out.println("            <label for='quantity'>Quantity</label>");
            out.println("            <input type='text' id='quantity' name='quantity'><br>");
            out.println("            <input type='submit' value='Insert'>");
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
                            "insert into ebookshop(book_title, book_author, book_price, quantity) values(?, ?, ?, ?);");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer

            st.setString(1, request.getParameter("book_name"));
            st.setString(2, request.getParameter("author_name"));
            st.setInt(3, Integer.parseInt(request.getParameter("price")));
            st.setInt(4, Integer.parseInt(request.getParameter("quantity")));
            // Same for second parameter
            // st.setString(2, request.getParameter("string"));

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();
            st.close();
            conn.close();

            redirectTo("crud_home?task=select", response);

        } catch (Exception e) {
            e.printStackTrace();
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
