package utils;

import main.Main;
import net.unikit.database.implementations.DatabaseManagerFactory;
import net.unikit.database.interfaces.DatabaseConfiguration;
import net.unikit.database.interfaces.DatabaseManager;

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

        DatabaseConfiguration configurationExternal = null;
        try {
            configurationExternal = createDatabaseConfigurationFromProperties(Main.EXTERNAL_DATABASE_CONFIGURATION_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatabaseConfiguration configurationInternal = null;
        try {
            configurationInternal = createDatabaseConfigurationFromProperties(Main.INTERNAL_DATABASE_CONFIGURATION_FILE);
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
     * Resets the test database using the initialize template.
     */
    private static void resetDatabase(DatabaseManager databaseManager, utils.DatabaseConfiguration databaseConfiguration) {
        Path inputPath = Paths.get(Main.DATABASE_INITIALIZE_TEMPLATE_FILE);
        Path outputPath = Paths.get(Main.DATABASE_INITIALIZE_TEMPORARY_FILE);

        // Read content of template file
        Charset charset = StandardCharsets.UTF_8;
        String content = null;
        try {
            content = new String(Files.readAllBytes(inputPath), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Replace template variables
        content = content.replaceAll(Pattern.quote(Main.TEMPLATE_EXTERNAL_SCHEMA_DUMMY), databaseConfiguration.getExternalSchema());
        content = content.replaceAll(Pattern.quote(Main.TEMPLATE_INTERNAL_SCHEMA_DUMMY), databaseConfiguration.getInternalSchema());

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
