package utils;

import net.unikit.database.implementations.DatabaseManagerFactory;
import net.unikit.database.interfaces.DatabaseConfiguration;
import net.unikit.database.interfaces.DatabaseManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import static net.unikit.database.implementations.DatabaseConfigurationUtils.createDatabaseConfigurationFromProperties;

public final class DatabaseUtils {
    private DatabaseUtils() {}

    /**
     * Getter for the manager of the test database.
     * @return The manager of the test database.
     */
    public static DatabaseManager createDatabaseManager(utils.DatabaseConfiguration databaseConfiguration) {
        DatabaseManager databaseManager = init(databaseConfiguration);
        return databaseManager;
    }

    /**
     * Creates the internal and external database configurations.
     * Creates a DatabaseManager with these configurations and resets the database using the @link{resetDatabase()} method.
     * @throws Exception
     */
    private static DatabaseManager init(utils.DatabaseConfiguration databaseConfiguration) {
        System.err.println("Loading configurations...");

        String externalConfigurationFile = "conf" + File.separator + "database_external.properties";
        DatabaseConfiguration configurationExternal = null;
        try {
            configurationExternal = createDatabaseConfigurationFromProperties(externalConfigurationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String internalConfigurationFile = "conf" + File.separator + "database_internal.properties";
        DatabaseConfiguration configurationInternal = null;
        try {
            configurationInternal = createDatabaseConfigurationFromProperties(internalConfigurationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println("Creating database...");

        DatabaseManager databaseManager = DatabaseManagerFactory.createDatabaseManager(configurationInternal, configurationExternal);
        resetDatabase(databaseManager, databaseConfiguration);

        configurationInternal.setSchema(databaseConfiguration.getInternalSchema());
        configurationExternal.setSchema(databaseConfiguration.getExternalSchema());
        databaseManager = DatabaseManagerFactory.createDatabaseManager(configurationInternal, configurationExternal);

        return databaseManager;
    }

    /**
     * Resets the test database using the <i>all_in_one.sql</i> template.
     */
    private static void resetDatabase(DatabaseManager databaseManager, utils.DatabaseConfiguration databaseConfiguration) {
        Path inputPath = Paths.get("assets" + File.separator + "all_in_one.sql");
        Path outputPath = Paths.get("assets" + File.separator + "all_in_one_temp.sql");

        // Read content of template file
        Charset charset = StandardCharsets.UTF_8;
        String content = null;
        try {
            content = new String(Files.readAllBytes(inputPath), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace template variables
        content = content.replaceAll(Pattern.quote("EXTERNAL_DATABASE_SCHEMA"), databaseConfiguration.getExternalSchema());
        content = content.replaceAll(Pattern.quote("INTERNAL_DATABASE_SCHEMA"), databaseConfiguration.getInternalSchema());

        // Write content to temporary file
        try {
            Files.write(outputPath, content.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reset database
        DatabaseResetUtils.resetDatabase(databaseManager, outputPath.toString());

        // Delete temporary file
        try {
            Files.delete(outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
