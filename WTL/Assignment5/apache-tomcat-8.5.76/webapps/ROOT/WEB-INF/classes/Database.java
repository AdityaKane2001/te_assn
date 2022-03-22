// package com.hubberspot.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.SQLException;

public class Database extends HttpServlet {
    private String mymsg;
    private Connection conn;

    protected void initializeDatabase()
            throws SQLException, ClassNotFoundException {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/books?characterEncoding=utf8";
        // Database name to access
        // String dbName = "books";
        String dbUsername = "root";
        String dbPassword = "Root123#";

        Class.forName(dbDriver);
        this.conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

    }

    public void init() throws ServletException {
        mymsg = "Http Servlet Demo";
        try {
            this.initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting up the content type of web page
        response.setContentType("text/html");

        // Writing the message on the web page
        PrintWriter out = response.getWriter();
        out.println("<h1>" + mymsg + "</h1>");
        out.println("<p>" + "Hello doGet!" + "</p>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Setting up the content type of web page
        response.setContentType("text/html");

        // Writing the message on the web page
        PrintWriter out = response.getWriter();
        out.println("<h1>" + mymsg + "</h1>");
        out.println("<p>" + "Hello doPost!" + "</p>");

        try {

            // Initialize the database

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = this.conn.prepareStatement("select * from ebookshop;");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer

            // Execute the insert command using executeUpdate()
            // to make changes in database
            ResultSet results = st.executeQuery();

            // Close all the connections

            out.println("<html><body><h2>The Select query has following results : </h2>");
            out.println("<hr></br><table cellspacing='0' cellpadding='5' border='1'>");
            out.println("<tr>");
            out.println("<td><b>Book ID</b></td>");
            out.println("<td><b>Book Name</b></td>");
            out.println("<td><b>Author name</b></td>");
            out.println("<td><b>Book price</b></td>");
            out.println("<td><b>Quantity</b></td>");
            out.println("</tr>");

            while (results.next()) {
                out.println("<tr>");
                out.println("<td>" + results.getString(1) + "</td>");
                out.println("<td>" + results.getString(2) + "</td>");
                out.println("<td>" + results.getString(3) + "</td>");
                out.println("<td>" + results.getString(4) + "</td>");
                out.println("<td>" + results.getString(5) + "</td>");
                out.println("</tr>");

            }
            st.close();
            this.conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}
