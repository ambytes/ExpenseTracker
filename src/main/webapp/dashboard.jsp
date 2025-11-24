<%@page import="com.expensetracker.model.User"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect("index.jsp"); return; }
    Double total = (Double) request.getAttribute("total");
    if (total == null) total = 0.0;
    Object budget = request.getAttribute("budget");
%>
<html>
<head><title>Dashboard</title><link rel="stylesheet" href="css/styles.css"></head>
<body>
<div class="container">
  <header><h1>Welcome, <%= user.getName() %></h1>
    <div class="nav"><a href="addExpense">Add Expense</a><a href="viewExpenses">View Expenses</a><a href="setBudget">Set Budget</a><a href="logout">Logout</a></div>
  </header>

  <div>
    <h3>Summary for <%= request.getAttribute("month") %></h3>
    <p>Total spent: <strong>₹<%= String.format("%.2f", total) %></strong></p>
    <% if (budget != null) { %>
       <%
         com.expensetracker.model.Budget b = (com.expensetracker.model.Budget) budget;
         double percent = (total / b.getMonthlyBudget()) * 100;
       %>
       <p>Budget: ₹<%= b.getMonthlyBudget() %></p>
       <p>Spent: <%= String.format("%.2f", percent) %> %</p>
       <% if (percent >= 100) { %>
         <div class="notice">ALERT: You have exceeded your budget!</div>
       <% } else if (percent >= 75) { %>
         <div class="notice">Warning: You have reached 75% of your budget.</div>
       <% } %>
    <% } else { %>
      <p>No budget set for this month.</p>
    <% } %>
  </div>
</div>
</body>
</html>
