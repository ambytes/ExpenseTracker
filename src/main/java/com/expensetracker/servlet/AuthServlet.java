package com.expensetracker.servlet;

import java.io.IOException;

import com.expensetracker.dao.UserDAO;
import com.expensetracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            User u = userDAO.findByEmailAndPassword(email, pass);
            if (u != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", u);
                resp.sendRedirect("dashboard");
            } else {
                req.setAttribute("error", "Invalid credentials");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else if ("register".equals(action)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String pass = req.getParameter("password");
            User u = new User();
            u.setName(name); u.setEmail(email); u.setPassword(pass);
            int id = userDAO.save(u);
            if (id > 0) {
                req.setAttribute("msg", "Registered. Please login.");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Registration failed (maybe duplicate email)");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
