package com.expensetracker.servlet;

import java.io.IOException;
import java.time.LocalDate;

import com.expensetracker.dao.ExpenseDAO;
import com.expensetracker.model.Expense;
import com.expensetracker.model.User;
import com.expensetracker.util.DateUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {
    private ExpenseDAO expenseDAO = new ExpenseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addExpense.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) { resp.sendRedirect("index.jsp"); return; }
        User u = (User) session.getAttribute("user");

        String cat = req.getParameter("category");
        double amt = Double.parseDouble(req.getParameter("amount"));
        String dateStr = req.getParameter("date");
        LocalDate date = DateUtil.parse(dateStr);
        String note = req.getParameter("note");

        Expense e = new Expense();
        e.setUserId(u.getId());
        e.setCategory(cat);
        e.setAmount(amt);
        e.setDate(date);
        e.setNote(note);

        int id = expenseDAO.save(e);
        if (id > 0) req.setAttribute("msg", "Expense added");
        else req.setAttribute("error", "Add failed");
        req.getRequestDispatcher("addExpense.jsp").forward(req, resp);
    }
}
