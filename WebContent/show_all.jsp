<%@ page import="java.sql.ResultSet" %>

<html>
<head>
    <title>All Students</title>
</head>
<body>

<h2>Registered Students</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Year</th>
</tr>

<%
    ResultSet rs =
        (ResultSet) request.getAttribute("students");

    while (rs.next()) {
%>
<tr>
    <td><%= rs.getInt("id") %></td>
    <td><%= rs.getString("name") %></td>
    <td><%= rs.getString("email") %></td>
    <td><%= rs.getInt("year") %></td>
</tr>
<%
    }
%>

</table>

<br>
<a href="index.html">Register New Student</a>

</body>
</html>
