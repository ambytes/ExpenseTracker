package com.expensetracker.servlet;

import java.io.IOException;
import java.time.LocalDate;

import com.expensetracker.dao.BudgetDAO;
import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private ExpenseDAO expenseDAO = new ExpenseDAO();
    private BudgetDAO budgetDAO = new BudgetDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("index.jsp"); return;
        }
        User user = (User) session.getAttribute("user");
        String month = req.getParameter("month");
        String monthYear = (month == null || month.isEmpty()) ? LocalDate.now().toString().substring(0,7) : month;

        double total = expenseDAO.sumByUserAndMonth(user.getId(), monthYear);
        req.setAttribute("total", total);
        req.setAttribute("month", monthYear);

        // budget
        req.setAttribute("budget", budgetDAO.findByUserAndMonth(user.getId(), monthYear));
        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
