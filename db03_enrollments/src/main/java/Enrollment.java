import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Enrollment
 * Student(s) Name(s):Alejandro Rojas
 */

@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable{
    @EmbeddedId
    private EnrollmentPK key;
    public Enrollment(){}
    public Enrollment(int Id, String Code) {
        this.key = new EnrollmentPK(Id, Code);
    }

    public String getCode() {
        return key.getCode(); }
    public int getId() {
        return key.getId(); }
    public static Object getKey(int Id, String Code) {
        return new EnrollmentPK(Id, Code);	}

    public void setCode(String code) { key.setCode(code); }
    public void setId(int id) { key.setId(id); }
    public void setKey(int Id, String Code){
        key = new EnrollmentPK(Id, Code);
    }

    @Override public String toString() {
        return "Enrollment{" +
                "course=" + key.getCode() +
                ", id=" + key.getId() +
                '}';
    }
}