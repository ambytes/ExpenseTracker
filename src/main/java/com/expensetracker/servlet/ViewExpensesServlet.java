package com.expensetracker.servlet;

import java.io.IOException;
import java.util.List;

import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewExpenses")
public class ViewExpensesServlet extends HttpServlet {
    private ExpenseDAO expenseDAO = new ExpenseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) { resp.sendRedirect("index.jsp"); return; }
        User u = (User) session.getAttribute("user");
        String month = req.getParameter("month");
        List<Expense> list;
        if (month == null || month.isEmpty()) list = expenseDAO.findByUser(u.getId());
        else list = expenseDAO.findByUserAndMonth(u.getId(), month);
        req.setAttribute("expenses", list);
        req.getRequestDispatcher("viewExpenses.jsp").forward(req, resp);
    }
}
