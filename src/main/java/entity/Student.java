package entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

@Entity(name = "Student")
public class Student extends Person {
    
    private int matNr;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date matDate;

    public Student() {
    }

    public Student(int matNr, Date matDate, String firstName, String lastName, Date birthDate, int age, boolean isMarried) {
        super(firstName, lastName, birthDate, age, isMarried);
        this.matNr = matNr;
        this.matDate = matDate;
    }

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public Date getMatDate() {
        return matDate;
    }

    public void setMatDate(Date matDate) {
        this.matDate = matDate;
    }
    
}