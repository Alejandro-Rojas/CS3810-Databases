/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - EnrollmentPK
 * Student(s) Name(s):Alejandro Rojas
 */

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class EnrollmentPK implements Serializable {
    private String code;
    private int id;

    public EnrollmentPK(int Id, String Code) {
        super();
        this.code = Code;
        this.id = Id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public EnrollmentPK() {
    }
    @Override public String toString() {
        return "EnrollmentPK{" +
                "code='" + code + '\'' +
                ", id=" + id +
                '}';
    }
}