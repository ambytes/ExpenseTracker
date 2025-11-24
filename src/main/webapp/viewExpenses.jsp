<%@page import="java.util.List"%>
<%@page import="com.expensetracker.model.Expense"%>
<%@page import="com.expensetracker.model.User"%>
<%
    User u = (User) session.getAttribute("user");
    if (u == null) { response.sendRedirect("index.jsp"); return; }
    List<Expense> list = (List<Expense>) request.getAttribute("expenses");
%>
<html>
<head><title>View Expenses</title><link rel="stylesheet" href="css/styles.css"></head>
<body>
<div class="container">
  <header><h1>Expenses</h1><div class="nav"><a href="dashboard">Dashboard</a><a href="addExpense">Add</a><a href="logout">Logout</a></div></header>

  <form action="viewExpenses" method="get">
    <label>Filter by month (YYYY-MM)</label>
    <input type="text" name="month" placeholder="2025-10">
    <button type="submit">Filter</button>
  </form>

  <table class="table">
    <thead><tr><th>ID</th><th>Date</th><th>Category</th><th>Amount</th><th>Note</th></tr></thead>
    <tbody>
      <% if (list != null) {
           for (Expense e : list) { %>
             <tr>
               <td><%= e.getId() %></td>
               <td><%= e.getDate() %></td>
               <td><%= e.getCategory() %></td>
               <td>₹<%= e.getAmount() %></td>
               <td><%= e.getNote() %></td>
             </tr>
      <%     }
         } %>
    </tbody>
  </table>
</div>
</body>
</html>
