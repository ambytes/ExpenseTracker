<%@page import="com.expensetracker.model.User" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) { response.sendRedirect("index.jsp"); return; }
%>
<html>
<head><title>Add Expense</title><link rel="stylesheet" href="css/styles.css"></head>
<body>
<div class="container">
  <header><h1>Add Expense</h1><div class="nav"><a href="dashboard">Dashboard</a><a href="viewExpenses">View</a><a href="logout">Logout</a></div></header>

  <% if (request.getAttribute("msg") != null) { %>
    <div class="notice"><%= request.getAttribute("msg") %></div>
  <% } %>
  <% if (request.getAttribute("error") != null) { %>
    <div class="notice"><%= request.getAttribute("error") %></div>
  <% } %>

  <form method="post" action="addExpense">
    <div class="form-row"><label>Category</label><input type="text" name="category" required></div>
    <div class="form-row"><label>Amount</label><input type="number" step="0.01" name="amount" required></div>
    <div class="form-row"><label>Date</label><input type="date" name="date"></div>
    <div class="form-row"><label>Note</label><input type="text" name="note"></div>
    <button type="submit">Add</button>
  </form>
</div>
</body>
</html>
