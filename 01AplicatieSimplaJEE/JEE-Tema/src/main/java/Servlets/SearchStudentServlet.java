package Servlets;

import Helpers.StudentSerializer;
import beans.StudentBean;
import beans.Students;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String varsta = request.getParameter("varsta");
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");

        File file = new File("/home/darian/Documents/SD/SistemeDistribuite/01AplicatieSimplaJEE/JEE-Tema/students.xml");
        XmlMapper mapper = new XmlMapper();

        if(!file.exists())
        {
            response.getWriter().println("<html><body>" + "Nu exista fisierul student.xml" + "<a href='./index.jsp'>Inapoi</a>" + "</body></html>");
        }
        Students Students_List = mapper.readValue(file, Students.class);
        StringBuilder htmlGen = new StringBuilder("<head>" + "<title>Informatii student</title>" +
                "</head>\n" + "<body>\n" + "<h3> Informatii student</h3>");
        StudentSerializer htmlSerializer = new StudentSerializer();
        if(varsta != null && !varsta.isEmpty()){

            for(StudentBean student : Students_List.getStudentList())
            {
                if(student.getVarsta() == Integer.parseInt(varsta))
                {
                    htmlGen.append(htmlSerializer.Serialize(student));
                }
            }
        }else if(nume != null && !nume.isEmpty())
        {
            for(StudentBean student : Students_List.getStudentList())
            {
                if(student.getNume().equals(nume))
                {
                    htmlGen.append(htmlSerializer.Serialize(student));
                }
            }
        }else if(prenume != null && !prenume.isEmpty())
        {
            for(StudentBean student : Students_List.getStudentList())
            {
                if(student.getPrenume().equals(prenume))
                {
                    htmlGen.append(htmlSerializer.Serialize(student));
                }
            }
        }
        htmlGen.append("<a href=./index.jsp>Inapoi pe pagina principala</a>");
        htmlGen.append("</body>");
        htmlGen.append("</html");
        PrintWriter writer = response.getWriter();
        writer.println(htmlGen);
    }
}
