package com.expensetracker.servlet;

import java.io.IOException;

import com.expensetracker.dao.BudgetDAO;
import com.expensetracker.model.Budget;
import com.expensetracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/setBudget")
public class SetBudgetServlet extends HttpServlet {
    private BudgetDAO budgetDAO = new BudgetDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("setBudget.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) { resp.sendRedirect("index.jsp"); return; }
        User u = (User) session.getAttribute("user");

        String month = req.getParameter("month"); // yyyy-MM
        double amount = Double.parseDouble(req.getParameter("monthlyBudget"));

        Budget b = new Budget();
        b.setUserId(u.getId());
        b.setMonthYear(month);
        b.setMonthlyBudget(amount);

        boolean ok = budgetDAO.saveOrUpdate(b);
        req.setAttribute("msg", ok ? "Budget saved" : "Failed to save");
        req.getRequestDispatcher("setBudget.jsp").forward(req, resp);
    }
}
