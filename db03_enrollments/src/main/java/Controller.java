/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Controller
 * Student(s) Name(s):Alejandro Rojas
 */

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Controller {
    private EntityManager em;
    private Session session;
    public Controller() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        em = emf.createEntityManager();
        session = em.unwrap(Session.class);
    }
    // TODO: return a Student entity from the given id (or null if the entity does not exist)
    public Student getStudent(int Id) {
        return em.find(Student.class,
                Id);
    }
    // TODO: add the given student entity, returning true/false depending whether the operation was successful or not
    public boolean addStudent(final Student student) throws Exception {
        var trans = em.getTransaction();
        trans.begin();
        em.persist(student);
        trans.commit();
        return true;
    }
    // TODO: return a list of all Course entities
    public List<Course> getCourses() {
        var courses = em.createQuery("From Course", Course.class).getResultList();
        for (int i = 0; i < courses.size(); i++) {
            Course x = courses.get(i);
            session.evict(x);
        }
        return courses;
    }
    // TODO: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
    public boolean enrollStudent(String Code, int Id) throws Exception {
        EntityTransaction entityTransaction;
        entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.find(Course.class, Code);
        var enrollment = new Enrollment(Id, Code);
        em.persist(enrollment);
        entityTransaction.commit();
        Boolean aBoolean = true;
        if (aBoolean) return true;
        else return false;
    }
    // TODO: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
    public boolean dropStudent(String Code, int Id) throws Exception {
        Enrollment enrollment;
        enrollment = em.find(Enrollment.class, Enrollment.getKey(Id, Code));
        EntityTransaction trans;
        trans = em.getTransaction();
        trans.begin();
        em.remove(enrollment);
        trans.commit();
        return true;
    }
    // TODO: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
    public List<Student> getStudentsEnrolled(String courseCode) {
        var course_Code = session.createSQLQuery(
                "CALL list_students(:code)").addEntity(Student.class)
                .setParameter("code", courseCode).list();
        return course_Code;
    }
}
