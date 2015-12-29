package utils;

/**
 * Created by Andreas on 29.12.2015.
 */
public class DatabaseConfiguration {
    private String externalSchema;
    private String internalSchema;

    private DatabaseConfiguration() {
    }

    public static DatabaseConfiguration create() {
        return new DatabaseConfiguration();
    }

    public String getExternalSchema() {
        return externalSchema;
    }

    public void setExternalSchema(String externalSchema) {
        this.externalSchema = externalSchema;
    }

    public String getInternalSchema() {
        return internalSchema;
    }

    public void setInternalSchema(String internalSchema) {
        this.internalSchema = internalSchema;
    }
}
