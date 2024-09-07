<%@ page import="beans.StudentBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.File" %>
<%@ page import="com.fasterxml.jackson.dataformat.xml.XmlMapper" %>
<%@ page import="beans.Students" %><%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 01/09/2024
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Student Search</title>
</head>
<body>

    <h1>Search Student</h1>

    <!-- Form for Age -->
    <form action="./search-student" method="post">
        <label for="varsta">Enter Age:</label>
        <input type="number" id="varsta" name="varsta" required>
        <input type="submit" value="Search by Age">
    </form>

    <hr>

    <!-- Form for Name -->
    <form action="./search-student" method="post">
        <label for="nume">Enter Name:</label>
        <input type="text" id="nume" name="nume" required>
        <input type="submit" value="Search by Name">
    </form>

    <hr>

    <!-- Form for Surname -->
    <form action="./search-student" method="post">
        <label for="prenume">Enter Surname</label>
        <input type="text" id="prenume" name="prenume" required>
        <input type="submit" value="Search by Surname">
    </form>

</body>
</html>

