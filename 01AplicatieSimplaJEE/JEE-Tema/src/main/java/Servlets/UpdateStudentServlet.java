package Servlets;

import beans.StudentBean;
import beans.Students;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));
        String action = request.getParameter("action");

        File file = new File("/home/darian/Documents/SD/SistemeDistribuite/01AplicatieSimplaJEE/JEE-Tema/students.xml");
        XmlMapper mapper = new XmlMapper();

        Students Students_List = mapper.readValue(file, Students.class);

        List<StudentBean> updatedList = new ArrayList<>();

        if ("Update".equals(action) && file.exists()) {
            //actualizare student
            for (StudentBean student : Students_List.getStudentList()) {
                if (student.getNume().equals(nume) && student.getPrenume().equals(prenume) && student.getVarsta() == varsta) {
                    student.setNume(request.getParameter("newNume"));
                    student.setPrenume(request.getParameter("newPrenume"));
                    student.setVarsta(Integer.parseInt(request.getParameter("newVarsta")));
                }
                updatedList.add(student);
            }
        } else if("Delete".equals(action) && file.exists())
        {
            for(StudentBean student : Students_List.getStudentList()) {
                if(!(student.getNume().equals(nume) && student.getPrenume().equals(prenume) && student.getVarsta() == varsta))
                {
                    updatedList.add(student);
                }
            }
        }
        Students updatedStudents = new Students();
        updatedStudents.setStudentList(updatedList);
        mapper.writeValue(file, updatedStudents);
        response.sendRedirect("./info-student.jsp");
    }
}
