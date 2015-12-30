package data;

/**
 * Created by Andreas on 30.12.2015.
 */
public class StudentData extends AbstractData {
    String studentNumber;
    String firstname;
    String lastname;
    String email;
    boolean male;
    int semester;

    private StudentData() {
    }

    public static StudentData create() {
        return new StudentData();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "studentNumber='" + studentNumber + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", male=" + male +
                ", semester=" + semester +
                '}';
    }
}
