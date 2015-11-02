package generators;

import data.StudentData;
import net.unikit.database.external.interfaces.FieldOfStudy;
import net.unikit.database.external.interfaces.Student;
import net.unikit.database.external.interfaces.StudentManager;
import net.unikit.database.external.interfaces.ImportDatabaseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 02.11.2015.
 */
public class StudentGenerator {
    private static List<StudentData> dataTemp = new ArrayList<>();
    private static List<Student> data = new ArrayList<>();

    public static List<Student> getData() {
        return data;
    }

    private static void initData(ImportDatabaseManager databaseManagerExternal) {
        StudentManager studentManager = databaseManagerExternal.getStudentManager();
        for (StudentData studentData : dataTemp) {
            Student student = studentManager.createStudent();

            student.setStudentNumber(studentData.getStudentNumber());
            student.setFirstName(studentData.getFirstName());
            student.setLastName(studentData.getLastName());
            student.setEmail(studentData.getEmail());
            student.setFieldOfStudy(studentData.getFieldOfStudy());
            student.setSemester(studentData.getSemester());

            studentManager.addStudent(student);
            data.add(student);
        }
    }

    private static void addDataTemp(String studentNumber, String firstName, String lastName, String email, FieldOfStudy fieldOfStudy, int semester) {
        StudentData studentData = new StudentData(studentNumber, firstName, lastName, email, fieldOfStudy, semester);
        dataTemp.add(studentData);
    }

    private static void initDataTemp() {
        // TODO: generate data
    }

    public static void main(ImportDatabaseManager databaseManagerExternal) throws IOException {
        initDataTemp();
        initData(databaseManagerExternal);
    }
}
