package Servlets;

import beans.StudentBean;
import beans.Students;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.Year;
public class ProcessStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String filePath = "/home/darian/Documents/SD/SistemeDistribuite/01AplicatieSimplaJEE/JEE-Tema/students.xml";
        File file = new File(filePath);
        XmlMapper xmlMapper = new XmlMapper();
        Students Students_List;

        if(file.exists())
        {
            Students_List = xmlMapper.readValue(file, Students.class);
        } else
        {
            Students_List = new Students();
        }

        StudentBean newStudent = new StudentBean();
        newStudent.setNume(request.getParameter("nume"));
        newStudent.setPrenume(request.getParameter("prenume"));
        newStudent.setVarsta(Integer.parseInt(request.getParameter("varsta")));

        Students_List.getStudentList().add(newStudent);
        xmlMapper.writeValue(file, Students_List);

        //response.sendRedirect("find_student.jsp");
        request.getRequestDispatcher("./find_student.jsp").forward(request, response);

       // request.getRequestDispatcher("./info-student.jsp").forward(request, response);
    }
}
