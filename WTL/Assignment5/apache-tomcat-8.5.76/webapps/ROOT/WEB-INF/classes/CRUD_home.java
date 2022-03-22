import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CRUD_home extends HttpServlet {

    private Connection conn;

    public void init() throws ServletException {

        // Initialize DB connection
        try {
            String dbURL = "jdbc:mysql://localhost:3306/books?characterEncoding=utf8";
            String dbUsername = "root";
            String dbPassword = "Root123#";

            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void redirectTo(String servlet, HttpServletResponse response) {
        // Set response content type
        response.setContentType("text/html");

        // New location to be redirected
        // String site = new String("http://www.photofuntoos.com");

        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", servlet);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String task = request.getParameter("task");
        if (task == "select") {

        }
        switch (task) {
            case "select":
                redirectTo("select", response);
                break;
            case "insert":
                redirectTo("insert", response);
                break;
            case "update":
                redirectTo("update", response);
                break;
            case "delete":
                redirectTo("delete", response);
                break;
            default:
                out.println(task + " not found on this server.");
        }

        // req.getRequestDispacher("servlet2").forward(request, response);
    }
}
