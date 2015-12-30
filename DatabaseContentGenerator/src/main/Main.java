package main;

import generators.StudentGenerator;
import net.unikit.database.interfaces.DatabaseManager;
import utils.DatabaseConfiguration;
import utils.DatabaseUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Andreas on 29.12.2015.
 */
public class Main {
    // FOLDERS
    private static final String CONFIGURATION_FOLDER = "conf" + File.separator;
    private static final String ASSETS_FOLDER = "assets" + File.separator;

    // FILES (CONFIGURATION)
    public static final String EXTERNAL_DATABASE_CONFIGURATION_FILE = CONFIGURATION_FOLDER + "database_external.properties";
    public static final String INTERNAL_DATABASE_CONFIGURATION_FILE = CONFIGURATION_FOLDER + "database_internal.properties";

    // FILES (INITIALIZATION)
    public static final String DATABASE_INITIALIZE_TEMPLATE_FILE = ASSETS_FOLDER + "initialize_template.sql";
    public static final String DATABASE_INITIALIZE_TEMPORARY_FILE = ASSETS_FOLDER + "initialize.sql";

    // FILES (INPUT)
    public static final String FIRSTNAMES_MALE_FILE = ASSETS_FOLDER + "firstnames_male.txt";
    public static final String FIRSTNAMES_FEMALE_FILE = ASSETS_FOLDER + "firstnames_female.txt";
    public static final String LASTNAMES_FILE = ASSETS_FOLDER + "lastnames.txt";

    // PARAMETERS
    public static final String TEMPLATE_EXTERNAL_SCHEMA_DUMMY = "EXTERNAL_DATABASE_SCHEMA";
    public static final String TEMPLATE_INTERNAL_SCHEMA_DUMMY = "INTERNAL_DATABASE_SCHEMA";

    /**
     * Creates and fills a database schema with the given configuration.
     * @param databaseConfiguration The configuration of the database schema and the content.
     */
    private static void createDatabase(DatabaseConfiguration databaseConfiguration) {
        // Create database schema
        DatabaseManager databaseManager = DatabaseUtils.createDatabaseManager(databaseConfiguration);

        // Fill database schema with content
        System.err.println("Generating content...");
        StudentGenerator.create(databaseConfiguration, databaseManager).run();
    }

    /**
     * Applies some static configuration data, based on statistics.
     * @param databaseConfiguration Database configuration to be filled
     */
    private static void applyStaticConfigurations(DatabaseConfiguration databaseConfiguration) {
        // University specific data
        databaseConfiguration.setEmailHost("haw-hamburg.de");
        databaseConfiguration.setFirstStudentNumber(2000000);

        /*
         * Sources for names:
         * - https://de.wiktionary.org/wiki/Verzeichnis:Deutsch/Liste_der_h%C3%A4ufigsten_Nachnamen_Deutschlands
         * - https://de.wiktionary.org/wiki/Verzeichnis:Deutsch/Liste_der_h%C3%A4ufigsten_m%C3%A4nnlichen_Vornamen_Deutschlands
         * - https://de.wiktionary.org/wiki/Verzeichnis:Deutsch/Liste_der_h%C3%A4ufigsten_weiblichen_Vornamen_Deutschlands
         */

        // Source: http://www.bpb.de/nachschlagen/zahlen-und-fakten/soziale-situation-in-deutschland/61669/studierende
        databaseConfiguration.setPercentageMale(0.857f);

        // Source: https://www.kit.edu/downloads/Statistik_WS13.pdf (page 58)
        SortedMap<Integer, Integer> studentsPerSemester = new TreeMap<>();
        studentsPerSemester.put(1, 519);
        studentsPerSemester.put(2, 11);
        studentsPerSemester.put(3, 386);
        studentsPerSemester.put(4, 10);
        studentsPerSemester.put(5, 330);
        studentsPerSemester.put(6, 11);
        studentsPerSemester.put(7, 192);
        studentsPerSemester.put(8, 8);
        studentsPerSemester.put(9, 87);
        studentsPerSemester.put(10, 2);
        studentsPerSemester.put(11, 25);
        databaseConfiguration.setStudentsPerSemester(studentsPerSemester);

        // Source: http://www.haw-hamburg.de/fileadmin/user_upload/Presse_und_Kommunikation/Downloads/C_1_Web_SoSe_15_Studierende_gesamt_20150612.pdf (page 3)
        Map<String, Integer> studentsPerFieldOfStudy = new HashMap<>();
        studentsPerFieldOfStudy.put("B-AI", 443);
        studentsPerFieldOfStudy.put("B-ECS", 14);
        studentsPerFieldOfStudy.put("B-TI", 348);
        studentsPerFieldOfStudy.put("B-WI", 165);
        studentsPerFieldOfStudy.put("M-INF", 118);
        databaseConfiguration.setStudentsPerFieldOfStudy(studentsPerFieldOfStudy);
    }

    public static void main(String[] args) {
        DatabaseConfiguration databaseConfiguration = DatabaseConfiguration.create();

        // Configuration for database schema
        databaseConfiguration.setExternalSchema("external_database");
        databaseConfiguration.setInternalSchema("internal_database");

        // Configuration for dimension of database
        databaseConfiguration.setStudentCount(100);

        // Apply static configuration data
        applyStaticConfigurations(databaseConfiguration);

        // Process generation
        createDatabase(databaseConfiguration);
    }
}
