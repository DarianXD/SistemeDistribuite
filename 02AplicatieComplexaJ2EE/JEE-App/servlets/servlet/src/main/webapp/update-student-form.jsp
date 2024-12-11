<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="ejb.StudentEntity" %><%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 12/09/2024
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //obtine id student din cerere
    String idParam = request.getParameter("id");
    int id = Integer.parseInt(idParam);

    //pregateste entity manager
    EntityManagerFactory factory= Persistence.createEntityManagerFactory("bazaDeDateSQLite");
    EntityManager em = factory.createEntityManager();

    //Gaseste studentul dupa id
    StudentEntity student = em.find(StudentEntity.class, id);

    em.close();
    factory.close();

%>
<html>
<head>
    <title>Actualizare Student</title>
    <meta charset="UTF-8" />
</head>
<body>
    <h3>Actualizare Student</h3>
    <form action="./update-student" method="post">
        <input type="hidden" name="id" value="<%= student.getId() %>" />
        Nume: <input type="text" name="nume" value="<%= student.getNume() %>" />
        <br />
        Prenume: <input type="text" name="prenume" value="<%= student.getPrenume() %>" />
        <br/>
        Vârstă: <input type="number" name="varsta" value="<%= student.getVarsta() %>" />
        <br/>
        <br/>
        <button type="submit" name="submit">Actualizează</button>
    </form>
</body>
</html>
