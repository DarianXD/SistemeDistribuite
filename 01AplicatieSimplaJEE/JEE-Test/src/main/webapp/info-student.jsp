<html xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Informatii student</title>
    <meta charset="UTF-8" />
</head>
<body>
<h3>Informatii student</h3>

<!-- populare bean cu informatii din cererea HTTP -->
<jsp:useBean id="studentBean" class="beans.StudentBean" scope="request" />
<jsp:setProperty name="studentBean" property="nume" value='<%= request.getAttribute("nume") %>'/>
<jsp:setProperty name="studentBean" property="prenume" value='<%= request.getAttribute("prenume") %>'/>
<jsp:setProperty name="studentBean" property="varsta" value='<%= request.getAttribute("varsta") %>'/>

<!-- folosirea bean-ului pentru afisarea informatiilor -->
<p>Urmatoarele informatii au fost introduse:</p>
<ul type="bullet">
    <li>Nume: <jsp:getProperty name="studentBean" property="nume" /></li>
    <li>Prenume: <jsp:getProperty name="studentBean" property="prenume" /></li>
    <li>Varsta: <jsp:getProperty name="studentBean" property="varsta" /></li>

    <!-- anul nasterii nu face parte din bean, il afisam separat (daca exista) -->
    <li>Anul nasterii: <%
        Object anNastere = request.getAttribute("anNastere");
        if (anNastere != null) {
            out.print(anNastere);
        } else {
            out.print("necunoscut");
        }
    %></li>
</ul>

<!-- Formular pentru actualizare si stergerre -->
<form action="./update-student" method="post">
    Nume: <input type="text" name="nume" value="<jsp:getProperty name="studentBean" property="nume"/>" /><br />
    Prenume: <input type="text" name="prenume" value="<jsp:getProperty name="studentBean" property="prenume"/>" /><br />
    Varsta: <input type="number" name="varsta" value="<jsp:getProperty name="studentBean" property="varsta"/>" /><br />
    <input type="submit" name="action" value="Update" />
    <input type="submit" name="action" value="Delete" />
</form>


<p>
    <a href="./index.jsp">Inapoi pe pagina principala</a>
</p>
</body>
</html>
