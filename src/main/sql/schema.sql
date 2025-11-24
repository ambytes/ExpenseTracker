
CREATE DATABASE IF NOT EXISTS expense_tracker;
USE expense_tracker;

CREATE TABLE IF NOT EXISTS users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS expenses (
  expense_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  category VARCHAR(100),
  amount DECIMAL(10,2) NOT NULL,
  expense_date DATE NOT NULL,
  note VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS budgets (
  budget_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  month_year VARCHAR(7) NOT NULL,
  monthly_budget DECIMAL(10,2) NOT NULL,
  UNIQUE KEY user_month (user_id, month_year),
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);


INSERT INTO users (name, email, password) VALUES
('Demo User', 'demo@example.com', 'demo123');

INSERT INTO expenses (user_id, category, amount, expense_date, note) VALUES
(1, 'Food', 120.50, '2025-10-01', 'Lunch'),
(1, 'Transport', 40.00, '2025-10-02', 'Auto'),
(1, 'Groceries', 500.00, '2025-09-15', 'Monthly groceries');

INSERT INTO budgets (user_id, month_year, monthly_budget) VALUES
(1, '2025-10', 15000.00);
