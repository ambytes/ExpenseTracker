package com.expensetracker.dao;

import com.expensetracker.model.User;
import com.expensetracker.util.DBConnection;

import java.sql.*;

public class UserDAO {
    public int save(User u) {
        String sql = "INSERT INTO expense_tracker.users (name, email, password) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM expense_tracker.users WHERE email = ? AND password = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("user_id"));
                    u.setName(rs.getString("name"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    return u;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
