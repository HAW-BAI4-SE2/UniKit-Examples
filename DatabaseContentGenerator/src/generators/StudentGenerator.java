package generators;

import data.StudentData;
import main.Main;
import net.unikit.database.interfaces.DatabaseManager;
import utils.DatabaseConfiguration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Andreas on 30.12.2015.
 */
public class StudentGenerator extends AbstractGenerator<StudentData> {
    private StudentGenerator(DatabaseConfiguration databaseConfiguration, DatabaseManager databaseManager) {
        super(databaseConfiguration, databaseManager);
    }

    public static StudentGenerator create(DatabaseConfiguration databaseConfiguration, DatabaseManager databaseManager) {
        return new StudentGenerator(databaseConfiguration, databaseManager);
    }

    @Override
    public void run() {
        // Create empty students
        for (int i = 0; i < getDatabaseConfiguration().getStudentCount(); i++) {
            getDataList().add(StudentData.create());
        }

        // Generate content
        generateNames();
        generateEmails();
        generateStudentNumbers();
        generateSemester();

        System.out.println(getDataList());
    }

    /**
     * Fills the attributes firstname, lastname and male.
     */
    private void generateNames() {
        int studentCount = getDatabaseConfiguration().getStudentCount();
        int maleCount = (int) (studentCount * getDatabaseConfiguration().getPercentageMale());
        int femaleCount = studentCount - maleCount;

        // Generate last name list
        List<String> lastnames = generateNameList(Main.LASTNAMES_FILE, studentCount);
        Iterator<String> lastnamesIterator = lastnames.iterator();
        Iterator<StudentData> studentIterator = getDataList().iterator();

        // Generate male students
        List<String> firstnames = generateNameList(Main.FIRSTNAMES_MALE_FILE, maleCount);
        Iterator<String> firstnamesIterator = firstnames.iterator();
        while (firstnamesIterator.hasNext()) {
            String firstname = firstnamesIterator.next();
            String lastname = lastnamesIterator.next();
            StudentData studentData = studentIterator.next();

            studentData.setFirstname(firstname);
            studentData.setLastname(lastname);
            studentData.setMale(true);
        }

        // Generate female students
        firstnames = generateNameList(Main.FIRSTNAMES_FEMALE_FILE, femaleCount);
        firstnamesIterator = firstnames.iterator();
        while (firstnamesIterator.hasNext()) {
            String firstname = firstnamesIterator.next();
            String lastname = lastnamesIterator.next();
            StudentData studentData = studentIterator.next();

            studentData.setFirstname(firstname);
            studentData.setLastname(lastname);
            studentData.setMale(false);
        }

        // Shuffle males and females
        Collections.shuffle(getDataList());
    }

    private List<String> generateNameList(String filename, int count) {
        List<String> list = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(Pattern.quote("   "));

                String name = splitted[0];
                int frequency = Integer.parseInt(splitted[1]);

                // Adding name to the result list dependent on the frequency of the name
                for (int i = 0; i < frequency; i++) {
                    list.add(name);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get required count of duplicates of the list
        int requiredListCount = (count / list.size()) + 1;

        // If necessary duplicate entries of the list
        List<String> result;
        if (requiredListCount > 1) {
            result = new LinkedList<>();
            for (int i = 0; i < requiredListCount; i++) {
                result.addAll(list);
            }
        } else {
            result = list;
        }

        // Shuffle the result, so a new name can be picked randomly by picking items of the list in sequence
        Collections.shuffle(result);

        // Crop list the requested name count
        result = result.subList(0, count);

        return result;
    }

    private void generateEmails() {
        List<String> takenEmails = new LinkedList<>();
        for (StudentData studentData : getDataList()) {
            String baseEmail = studentData.getFirstname().toLowerCase();
            baseEmail = baseEmail + "." + studentData.getLastname().toLowerCase();

            int count = 1;
            String email = baseEmail;
            while (takenEmails.contains(email)) {
                count++;
                email = baseEmail + count;
            }

            takenEmails.add(email);
            email = email + "@" + getDatabaseConfiguration().getEmailHost();
            studentData.setEmail(email);
        }
    }

    private void generateStudentNumbers() {
        int studentNumber = getDatabaseConfiguration().getFirstStudentNumber();
        for (StudentData studentData : getDataList()) {
            studentData.setStudentNumber(String.valueOf(studentNumber));
            studentNumber++;
        }
    }

    private void generateSemester() {
        int studentCount = getDatabaseConfiguration().getStudentCount();
        SortedMap<Integer, Integer> studentsPerSemesterOld = getDatabaseConfiguration().getStudentsPerSemester();
        SortedMap<Integer, Integer> studentsPerSemester = new TreeMap<>();

        // Get overall count old of students per semester map
        int countOld = 0;
        for (int value : studentsPerSemesterOld.values()) {
            countOld += value;
        }

        // Calculate multiplier
        float multiplier = 1.0f * studentCount / countOld;

        // Apply multiplier
        for (Map.Entry<Integer, Integer> entry : studentsPerSemesterOld.entrySet()) {
            int key = entry.getKey();
            int value = (int) (entry.getValue() * multiplier);
            studentsPerSemester.put(key, value);
        }

        // Correct rounding errors
        studentsPerSemester.put(1, 0);
        int count = 0;
        for (int value : studentsPerSemester.values()) {
            count += value;
        }
        studentsPerSemester.put(1, (studentCount - count));

        // Apply semester to students
        Iterator<StudentData> studentIterator = getDataList().iterator();
        for (Map.Entry<Integer, Integer> entry : studentsPerSemester.entrySet()) {
            int semester = entry.getKey();
            for (int i = 0; i < entry.getValue(); i++) {
                StudentData studentData = studentIterator.next();
                studentData.setSemester(semester);
            }
        }
    }
}
