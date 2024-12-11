<%--
  Created by IntelliJ IDEA.
  User: darian
  Date: 12/09/2024
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<html xmlns:jsp="http:java.sun.com/JSP/Page">
    <head>
        <title>Formular Student</title>
        <meta charset="UTF-8" />
    </head>
    <body>
        <h3>Formular Student</h3>
        Introduceti datele despre student:
        <form action="./process-student" method="post">
            Nume: <input type="text" name="nume" />
            <br />
            Prenume: <input type="text" name="prenume" />
            <br/>
            Varsta: <input type="number" name="varsta" />
            <br/>
            <br/>
            <button type="submit" name="submit">Trimite</button>
        </form>
    </body>
</html>