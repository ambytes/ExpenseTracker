package com.expensetracker.dao;

import com.expensetracker.model.Budget;
import com.expensetracker.util.DBConnection;

import java.sql.*;

public class BudgetDAO {
    public boolean saveOrUpdate(Budget b) {
        String insert = "INSERT INTO expense_tracker.budgets (user_id, month_year, monthly_budget) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE monthly_budget = VALUES(monthly_budget)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(insert)) {
            ps.setInt(1, b.getUserId());
            ps.setString(2, b.getMonthYear());
            ps.setDouble(3, b.getMonthlyBudget());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); }
        return false;
    }

    public Budget findByUserAndMonth(int userId, String monthYear) {
        String sql = "SELECT * FROM expense_tracker.budgets WHERE user_id = ? AND month_year = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, monthYear);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Budget b = new Budget();
                    b.setId(rs.getInt("budget_id"));
                    b.setUserId(rs.getInt("user_id"));
                    b.setMonthYear(rs.getString("month_year"));
                    b.setMonthlyBudget(rs.getDouble("monthly_budget"));
                    return b;
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return null;
    }
}
