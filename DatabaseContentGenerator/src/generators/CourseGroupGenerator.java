package generators;

import data.CourseGroupData;
import net.unikit.database.external.interfaces.Course;
import net.unikit.database.external.interfaces.CourseGroup;
import net.unikit.database.external.interfaces.CourseGroupManager;
import net.unikit.database.external.interfaces.ImportDatabaseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 02.11.2015.
 */
public class CourseGroupGenerator {
    private static final int DEFAULT_MAX_GROUP_SIZE = 20;

    private static List<CourseGroupData> dataTemp = new ArrayList<>();
    private static List<CourseGroup> data = new ArrayList<>();

    public static List<CourseGroup> getData() {
        return data;
    }

    private static void initData(ImportDatabaseManager databaseManagerExternal) {
        CourseGroupManager courseGroupManager = databaseManagerExternal.getCourseGroupManager();
        for (CourseGroupData courseGroupData : dataTemp) {
            CourseGroup courseGroup = courseGroupManager.createCourseGroup();

            courseGroup.setCourse(courseGroupData.getCourse());
            courseGroup.setGroupNumber(courseGroupData.getGroupNumber());
            courseGroup.setMaxGroupSize(courseGroupData.getMaxGroupSize());

            courseGroupManager.addCourseGroup(courseGroup);
            data.add(courseGroup);
        }
    }

    private static void addDataTemp(Course course, int groupNumber, int maxGroupSize) {
        CourseGroupData courseGroupData = new CourseGroupData(course, groupNumber, maxGroupSize);
        dataTemp.add(courseGroupData);
    }

    private static void initDataTemp() {
        // TODO: generate data
    }

    public static void main(ImportDatabaseManager databaseManagerExternal) throws IOException {
        initDataTemp();
        initData(databaseManagerExternal);
    }
}
