package data;

import net.unikit.database.external.interfaces.Course;

/**
 * Created by Andreas on 02.11.2015.
 */
public class CourseGroupData {
    private Course course;
    private int groupNumber;
    private int maxGroupSize;

    public CourseGroupData(Course course, int groupNumber, int maxGroupSize) {
        this.course = course;
        this.groupNumber = groupNumber;
        this.maxGroupSize = maxGroupSize;
    }

    public Course getCourse() {
        return course;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public int getMaxGroupSize() {
        return maxGroupSize;
    }
}
