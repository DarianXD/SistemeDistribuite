import ejb.StudentEntity;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obține ID-ul studentului din cerere
        int id = Integer.parseInt(request.getParameter("id"));

        // Pregătește EntityManager
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bazaDeDateSQLite");
        EntityManager em = factory.createEntityManager();

        // Începe tranzacția
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // Șterge studentul folosind JPQL
        String jpqlDelete = "DELETE FROM StudentEntity s WHERE s.id = :id";
        Query query = em.createQuery(jpqlDelete);
        query.setParameter("id", id);
        int rowsDeleted = query.executeUpdate();

        // Comite tranzacția
        transaction.commit();

        // Închide EntityManager
        em.close();
        factory.close();

        // Redirecționează înapoi la lista de studenți
        response.sendRedirect("./fetch-student-list");
    }
}
