import net.unikit.database.interfaces.DatabaseManager;
import utils.DatabaseConfiguration;
import utils.DatabaseUtils;

/**
 * Created by Andreas on 29.12.2015.
 */
public class Main {
    public static void createDatabase(DatabaseConfiguration databaseConfiguration) {
        DatabaseManager databaseManager = DatabaseUtils.createDatabaseManager(databaseConfiguration);
    }

    public static void main(String[] args) {
        DatabaseConfiguration databaseConfiguration = DatabaseConfiguration.create();
        databaseConfiguration.setExternalSchema("external_database23");
        databaseConfiguration.setInternalSchema("internal_database23");

        createDatabase(databaseConfiguration);
    }
}
