package com.expensetracker.dao;

import com.expensetracker.model.Expense;
import com.expensetracker.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    public int save(Expense e) {
        String sql = "INSERT INTO expense_tracker.expenses (user_id, category, amount, expense_date, note) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getUserId());
            ps.setString(2, e.getCategory());
            ps.setDouble(3, e.getAmount());
            ps.setDate(4, Date.valueOf(e.getDate()));
            ps.setString(5, e.getNote());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return -1;
    }

    public List<Expense> findByUserAndMonth(int userId, String monthYear) {
        String sql = "SELECT * FROM expense_tracker.expenses WHERE user_id = ? AND DATE_FORMAT(expense_date, '%Y-%m') = ? ORDER BY expense_date DESC";
        List<Expense> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, monthYear);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense e = new Expense();
                    e.setId(rs.getInt("expense_id"));
                    e.setUserId(rs.getInt("user_id"));
                    e.setCategory(rs.getString("category"));
                    e.setAmount(rs.getDouble("amount"));
                    e.setDate(rs.getDate("expense_date").toLocalDate());
                    e.setNote(rs.getString("note"));
                    list.add(e);
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public double sumByUserAndMonth(int userId, String monthYear) {
        String sql = "SELECT IFNULL(SUM(amount),0) FROM expense_tracker.expenses WHERE user_id = ? AND DATE_FORMAT(expense_date, '%Y-%m') = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, monthYear);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble(1);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return 0.0;
    }

    public List<Expense> findByUser(int userId) {
        String sql = "SELECT * FROM expense_tracker.expenses WHERE user_id = ? ORDER BY expense_date DESC";
        List<Expense> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expense e = new Expense();
                    e.setId(rs.getInt("expense_id"));
                    e.setUserId(rs.getInt("user_id"));
                    e.setCategory(rs.getString("category"));
                    e.setAmount(rs.getDouble("amount"));
                    e.setDate(rs.getDate("expense_date").toLocalDate());
                    e.setNote(rs.getString("note"));
                    list.add(e);
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }
}
