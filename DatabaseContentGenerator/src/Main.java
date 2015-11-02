import generators.*;
import net.unikit.database.common.implementations.DatabaseConfigurationUtils;
import net.unikit.database.common.interfaces.DatabaseConfiguration;
import net.unikit.database.external.implementations.ImportDatabaseManagerFactory;
import net.unikit.database.external.interfaces.ImportDatabaseManager;

import java.io.IOException;

/**
 * Created by Andreas on 02.11.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        DatabaseConfiguration databaseConfigurationExternal =
                DatabaseConfigurationUtils.createDatabaseConfigurationFromProperties("database_external.properties");
        ImportDatabaseManager databaseManagerExternal =
                ImportDatabaseManagerFactory.createDatabaseManager(databaseConfigurationExternal);

        FieldOfStudyGenerator.main(databaseManagerExternal);
        CourseGenerator.main(databaseManagerExternal);
        CourseGroupGenerator.main(databaseManagerExternal);
        AppointmentGenerator.main(databaseManagerExternal);
        StudentGenerator.main(databaseManagerExternal);
    }
}
