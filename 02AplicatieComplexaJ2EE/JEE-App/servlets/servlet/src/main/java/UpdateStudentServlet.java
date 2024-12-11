import ejb.StudentEntity;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obține parametrii din cerere
        int id = Integer.parseInt(request.getParameter("id"));
        String nume = request.getParameter("nume");
        String prenume = request.getParameter("prenume");
        int varsta = Integer.parseInt(request.getParameter("varsta"));

        // Pregătește EntityManager
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        // Începe tranzacția
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // Actualizează datele studentului folosind JPQL
        String jpqlUpdate = "UPDATE StudentEntity s SET s.nume = :nume, s.prenume = :prenume, s.varsta = :varsta WHERE s.id = :id";
        Query query = em.createQuery(jpqlUpdate);
        query.setParameter("nume", nume);
        query.setParameter("prenume", prenume);
        query.setParameter("varsta", varsta);
        query.setParameter("id", id);
        int rowsUpdated = query.executeUpdate();

        // Comite tranzacția
        transaction.commit();

        // Închide EntityManager
        em.close();
        factory.close();

        // Redirecționează înapoi la lista de studenți
        response.sendRedirect("./fetch-student-list");
    }
}
