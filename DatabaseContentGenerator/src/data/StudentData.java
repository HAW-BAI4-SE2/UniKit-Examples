package data;

import net.unikit.database.external.interfaces.FieldOfStudy;

/**
 * Created by Andreas on 02.11.2015.
 */
public class StudentData {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String email;
    private FieldOfStudy fieldOfStudy;
    private int semester;

    public StudentData(String studentNumber, String firstName, String lastName, String email, FieldOfStudy fieldOfStudy, int semester) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fieldOfStudy = fieldOfStudy;
        this.semester = semester;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public int getSemester() {
        return semester;
    }
}
