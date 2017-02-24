package starter;

import entity.Employee;
import entity.Person;
import entity.Student;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Tester {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public static void main(String[] args) {
        Tester t = new Tester();
        
        t.addPerson("Asger", "lundblad", new Date(), 21, false);
        t.addStudent(1, new Date(), "Jakob", "Lundblad", new Date(), 20, false);
        t.addEmployee(1, 2, "something", new Date(), "Hans", "Hansen", new Date(), 43, true);
        
        System.out.println(t.findPerson(2));
        t.deletePerson(2);
        
        System.out.println(t.findPerson(1));
        t.editPerson(1);
        System.out.println(t.findPerson(1));
        
        
    }

    public void addPerson(String firstName, String lastName, Date birthDate, int age, boolean isMarriged) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            Person p = new Person(firstName, lastName, birthDate, age, isMarriged);
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void addStudent(int matNr, Date matDate, String firstName, String lastName, Date birthDate, int age, boolean isMarriged) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            Student s = new Student(matNr, matDate, firstName, lastName, birthDate, age, isMarriged);
            em.persist(s);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void addEmployee(int soSecNr, float wage, String taxClass, Date matDate, String firstName, String lastName, Date birthDate, int age, boolean isMarriged) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            Employee e = new Employee(soSecNr, wage, taxClass, firstName, lastName, birthDate, age, isMarriged);
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Person findPerson(int personId){
        EntityManager em = getManager();
        Person p = new Person();
        try {
            em.getTransaction().begin();
            p = em.find(Person.class, personId);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return p;
    }
    
    public void deletePerson(int personId) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(Person.class, personId));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void editPerson(int personId) {
        EntityManager em = getManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, personId);
            p.setFirstName("Bob");
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    private EntityManager getManager() {
        return emf.createEntityManager();
    }
}
