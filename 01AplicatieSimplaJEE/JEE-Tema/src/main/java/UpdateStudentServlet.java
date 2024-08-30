import beans.StudentBean;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.File;


public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        File file = new File("/home/darian/opt/1307A/Popescu Ion/student.xml");
        XmlMapper xmlMapper = new XmlMapper();

        if ("Update".equals(action) && file.exists()) {
            //actualizare student
            StudentBean bean = xmlMapper.readValue(file, StudentBean.class);
            bean.setNume(request.getParameter("nume"));
            bean.setPrenume(request.getParameter("prenume"));
            bean.setVarsta(Integer.parseInt(request.getParameter("varsta")));
            xmlMapper.writeValue(file, bean);
        } else if ("Delete".equals(action) && file.exists()) {
            file.delete();
        }
        response.sendRedirect("index.jsp");
    }
}
