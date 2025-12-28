package com.student.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    private static final String DB_URL =
        "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String yearStr = request.getParameter("year");

        if (name == null || email == null || yearStr == null ||
            name.isEmpty() || email.isEmpty()) {
            response.getWriter().println("Invalid input");
            return;
        }

        int year = Integer.parseInt(yearStr);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn =
                DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql =
                "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, year);

            ps.executeUpdate();

            ps.close();
            conn.close();

            response.getWriter().println(
                "Student Registered Successfully<br>");
            response.getWriter().println(
                "<a href='index.html'>Back</a>");

        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
