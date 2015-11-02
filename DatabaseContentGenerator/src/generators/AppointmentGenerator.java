package generators;

import data.AppointmentData;
import net.unikit.database.external.interfaces.Appointment;
import net.unikit.database.external.interfaces.AppointmentManager;
import net.unikit.database.external.interfaces.CourseGroup;
import net.unikit.database.external.interfaces.ImportDatabaseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andreas on 02.11.2015.
 */
public class AppointmentGenerator {
    private static List<AppointmentData> dataTemp = new ArrayList<>();
    private static List<Appointment> data = new ArrayList<>();

    public static List<Appointment> getData() {
        return data;
    }

    private static void initData(ImportDatabaseManager databaseManagerExternal) {
        AppointmentManager appointmentManager = databaseManagerExternal.getAppointmentManager();
        for (AppointmentData appointmentData : dataTemp) {
            Appointment appointment = appointmentManager.createAppointment();

            appointment.setCourseGroup(appointmentData.getCourseGroup());
            appointment.setStartDate(appointmentData.getStartDate());
            appointment.setEndDate(appointmentData.getEndDate());

            appointmentManager.addAppointment(appointment);
            data.add(appointment);
        }
    }

    private static void addDataTemp(CourseGroup courseGroup, Date startDate, Date endDate) {
        AppointmentData appointmentData = new AppointmentData(courseGroup, startDate, endDate);
        dataTemp.add(appointmentData);
    }

    private static void initDataTemp() {
        // TODO: generate data
    }

    public static void main(ImportDatabaseManager databaseManagerExternal) throws IOException {
        initDataTemp();
        initData(databaseManagerExternal);
    }
}
