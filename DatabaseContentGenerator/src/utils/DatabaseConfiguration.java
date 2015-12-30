package utils;

import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Andreas on 29.12.2015.
 */
public class DatabaseConfiguration {
    private String externalSchema;
    private String internalSchema;

    private int studentCount;
    private float percentageMale;

    private String emailHost;
    private int firstStudentNumber;

    private SortedMap<Integer, Integer> studentsPerSemester;
    private Map<String, Integer> studentsPerFieldOfStudy;

    private DatabaseConfiguration() {
    }

    public static DatabaseConfiguration create() {
        return new DatabaseConfiguration();
    }

    public String getExternalSchema() {
        return externalSchema;
    }

    public void setExternalSchema(String externalSchema) {
        this.externalSchema = externalSchema;
    }

    public String getInternalSchema() {
        return internalSchema;
    }

    public void setInternalSchema(String internalSchema) {
        this.internalSchema = internalSchema;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public float getPercentageMale() {
        return percentageMale;
    }

    public void setPercentageMale(float percentageMale) {
        this.percentageMale = percentageMale;
    }

    public String getEmailHost() {
        return emailHost;
    }

    public void setEmailHost(String emailHost) {
        this.emailHost = emailHost;
    }

    public int getFirstStudentNumber() {
        return firstStudentNumber;
    }

    public void setFirstStudentNumber(int firstStudentNumber) {
        this.firstStudentNumber = firstStudentNumber;
    }

    public SortedMap<Integer, Integer> getStudentsPerSemester() {
        return studentsPerSemester;
    }

    public void setStudentsPerSemester(SortedMap<Integer, Integer> studentsPerSemester) {
        this.studentsPerSemester = studentsPerSemester;
    }

    public Map<String, Integer> getStudentsPerFieldOfStudy() {
        return studentsPerFieldOfStudy;
    }

    public void setStudentsPerFieldOfStudy(Map<String, Integer> studentsPerFieldOfStudy) {
        this.studentsPerFieldOfStudy = studentsPerFieldOfStudy;
    }
}
