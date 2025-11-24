<%@page import="com.expensetracker.model.User"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User u = (User) session.getAttribute("user");
    if (u == null) { response.sendRedirect("index.jsp"); return; }
%>
<html>
<head><title>Set Budget</title><link rel="stylesheet" href="css/styles.css"></head>
<body>
<div class="container">
  <header><h1>Set Budget</h1><div class="nav"><a href="dashboard">Dashboard</a><a href="viewExpenses">View</a><a href="logout">Logout</a></div></header>

  <% if (request.getAttribute("msg") != null) { %>
    <div class="notice"><%= request.getAttribute("msg") %></div>
  <% } %>

  <form method="post" action="setBudget">
    <div class="form-row"><label>Month (YYYY-MM)</label><input type="text" name="month" value="<%= java.time.LocalDate.now().toString().substring(0,7) %>" required></div>
    <div class="form-row"><label>Monthly Budget</label><input type="number" step="0.01" name="monthlyBudget" required></div>
    <button type="submit">Save</button>
  </form>
</div>
</body>
</html>
