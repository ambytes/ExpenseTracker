<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Login - Expense Tracker</title>
  <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container">
  <header><h1>Expense Tracker</h1><div class="nav"><a href="register.jsp">Register</a></div></header>

  <% if (request.getAttribute("error") != null) { %>
  <div class="notice"><%= request.getAttribute("error") %></div>
<% } %>

  <% if (request.getAttribute("error") != null) { %>
  <div class="notice"><%= request.getAttribute("error") %></div>
<% } %>


  <form action="auth" method="post">
    <input type="hidden" name="action" value="login">
    <div class="form-row"><label>Email</label><input type="email" name="email" required></div>
    <div class="form-row"><label>Password</label><input type="password" name="password" required></div>
    <button type="submit">Login</button>
  </form>
  <p style="margin-top:12px;color:#666">Demo: demo@example.com / demo123</p>
</div>
</body>
</html>
