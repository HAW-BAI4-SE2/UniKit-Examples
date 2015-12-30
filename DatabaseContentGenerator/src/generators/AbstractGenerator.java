package generators;

import data.AbstractData;
import data.StudentData;
import net.unikit.database.interfaces.DatabaseManager;
import utils.DatabaseConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 30.12.2015.
 */
abstract class AbstractGenerator<T extends AbstractData> {
    private DatabaseConfiguration databaseConfiguration;
    private DatabaseManager databaseManager;
    private List<T> dataList;

    protected AbstractGenerator(DatabaseConfiguration databaseConfiguration, DatabaseManager databaseManager) {
        this.databaseConfiguration = databaseConfiguration;
        this.databaseManager = databaseManager;
        this.dataList = new ArrayList<>();
    }

    protected DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    protected DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    protected List<T> getDataList() {
        return dataList;
    }

    public abstract void run();
}
