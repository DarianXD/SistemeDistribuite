<%@ page import="java.io.File" %>
<%@ page import="com.fasterxml.jackson.dataformat.xml.XmlMapper" %>
<%@ page import="beans.Students" %>
<%@ page import="Helpers.StudentSerializer" %>
<%@ page import="beans.StudentBean" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Informatii student</title>
    <meta charset="UTF-8" />
</head>
<body>
<h3>Informatii student</h3>
<!--
 populare bean cu informatii din cererea HTTP -->

<!-- folosirea bean-ului pentru afisarea informatiilor -->
<p>Urmatoarele informatii au fost introduse:</p>
<%
    File file = new File("/home/darian/Documents/SD/SistemeDistribuite/01AplicatieSimplaJEE/JEE-Tema/students.xml");
    if(!file.exists()) {
        response.getWriter().println("<html><body>" + "Nu exista fisierul student.xml" + "<a href='./index.jsp'>Inapoi</a>" + "</body></html>");
        return;
    }
    XmlMapper mapper = new XmlMapper();
    Students Students_List = mapper.readValue(file, Students.class);
    StringBuilder htmlGen = new StringBuilder();
    StudentSerializer htmlSerializer = new StudentSerializer();
    for(StudentBean student : Students_List.getStudentList())
    {
        htmlGen.append(htmlSerializer.Serialize(student));
    }
    out.println(htmlGen.toString());
%>

<p>
    <a href="./index.jsp">Inapoi pe pagina principala</a>
</p>


</body>
</html>
