<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head><title>Register</title><link rel="stylesheet" href="css/styles.css"></head>
<body>
<div class="container">
  <header><h1>Register</h1><div class="nav"><a href="index.jsp">Login</a></div></header>
  <% if (request.getAttribute("error") != null) { %>
    <div class="notice"><%= request.getAttribute("error") %></div>
  <% } %>
  <form action="auth" method="post">
    <input type="hidden" name="action" value="register">
    <div class="form-row"><label>Name</label><input type="text" name="name" required></div>
    <div class="form-row"><label>Email</label><input type="email" name="email" required></div>
    <div class="form-row"><label>Password</label><input type="password" name="password" required></div>
    <button type="submit">Register</button>
  </form>
</div>
</body>
</html>
