package data;

import net.unikit.database.external.interfaces.CourseGroup;

import java.util.Date;

/**
 * Created by Andreas on 02.11.2015.
 */
public class AppointmentData {
    private CourseGroup courseGroup;
    private Date startDate;
    private Date endDate;

    public AppointmentData(CourseGroup courseGroup, Date startDate, Date endDate) {
        this.courseGroup = courseGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
