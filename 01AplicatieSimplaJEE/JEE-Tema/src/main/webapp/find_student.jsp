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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista Studenți</title>
</head>
<body>
<h2>Lista Studenți</h2>

<p>Urmatoarele informatii au fost introduse:</p>
<%
    File file = new File("/home/darian/Documents/SD/Enachi_Vasile_Teme_SD/enaki_tema_1/Tema_1/JEE-Test/student_list.xml");
    // se returneaza un raspuns HTTP de tip 404 in cazul in care nu se gaseste fisierul cu date
    if (!file.exists()) {
        //response.sendError(404, "Nu a fost gasit niciun student serializat pe disc!");
        response.getWriter().println("<html><body>" +
                "Nu exista fisierul student_list.xml<br />" +
                "<a href=\".\">Inapoi</a>" +
                "</body></html>");
        return;
    }
    XmlMapper xmlMapper = new XmlMapper();
    Students student_list = xmlMapper.readValue(file, Students.class);
    StringBuilder syntax = new StringBuilder();
    StudentHtmlGenerator htmlGenerator = new StudentHtmlGenerator();
    for (StudentBean student : student_list.getStudent_list()){
        syntax.append(htmlGenerator.serialize(student));
    }
    syntax.append("<a href=\".\">Inapoi</a>");
    out.println(syntax.toString());
%>

<h3>Caută Student</h3>
<form action="./searchStudent" method="get">
    Nume sau Prenume: <input type="text" name="searchQuery">
    <button type="submit">Caută</button>
</form>
</body>
</html>

