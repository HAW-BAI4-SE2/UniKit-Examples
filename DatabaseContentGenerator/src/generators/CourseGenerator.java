package generators;

import data.CourseData;
import net.unikit.database.external.interfaces.Course;
import net.unikit.database.external.interfaces.CourseManager;
import net.unikit.database.external.interfaces.ImportDatabaseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 02.11.2015.
 */
public class CourseGenerator {
    private static final int DEFAULT_MIN_TEAM_SIZE = 2;
    private static final int DEFAULT_MAX_TEAM_SIZE = 2;

    private static List<CourseData> dataTemp = new ArrayList<>();
    private static List<Course> data = new ArrayList<>();

    public static List<Course> getData() {
        return data;
    }

    private static void initData(ImportDatabaseManager databaseManagerExternal) {
        CourseManager courseManager = databaseManagerExternal.getCourseManager();
        for (CourseData courseData : dataTemp) {
            Course course = courseManager.createCourse();

            course.setName(courseData.getName());
            course.setAbbreviation(courseData.getAbbreviation());
            course.setSemester(courseData.getSemester());
            course.setMinTeamSize(courseData.getMinTeamSize());
            course.setMaxTeamSize(courseData.getMaxTeamSize());

            courseManager.addCourse(course);
            data.add(course);
        }
    }

    private static void addDataTemp(String name, String abbreviation, int minTeamSize, int maxTeamSize) {
        CourseData courseData = new CourseData(name, abbreviation, minTeamSize, maxTeamSize);
        dataTemp.add(courseData);
    }

    private static void addDataTemp(String name, String abbreviation, int semester, int minTeamSize, int maxTeamSize) {
        CourseData courseData = new CourseData(name, abbreviation, semester, minTeamSize, maxTeamSize);
        dataTemp.add(courseData);
    }

    private static void initDataTemp() {
        // TODO: generate data
    }

    public static void main(ImportDatabaseManager databaseManagerExternal) throws IOException {
        initDataTemp();
        initData(databaseManagerExternal);
    }
}
