package generators;

import data.FieldOfStudyData;
import net.unikit.database.external.interfaces.FieldOfStudy;
import net.unikit.database.external.interfaces.FieldOfStudyManager;
import net.unikit.database.external.interfaces.ImportDatabaseManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 02.11.2015.
 */
public class FieldOfStudyGenerator {
    private static List<FieldOfStudyData> dataTemp = new ArrayList<>();
    public static List<FieldOfStudy> data = new ArrayList<>();

    public static List<FieldOfStudy> getData() {
        return data;
    }

    private static void initData(ImportDatabaseManager databaseManagerExternal) {
        FieldOfStudyManager fieldOfStudyManager = databaseManagerExternal.getFieldOfStudyManager();
        for (FieldOfStudyData fieldOfStudyData : dataTemp) {
            FieldOfStudy fieldOfStudy = fieldOfStudyManager.createFieldOfStudy();

            fieldOfStudy.setName(fieldOfStudyData.getName());
            fieldOfStudy.setAbbreviation(fieldOfStudyData.getAbbreviation());

            fieldOfStudyManager.addFieldOfStudy(fieldOfStudy);
            data.add(fieldOfStudy);
        }
    }

    private static void addDataTemp(String name, String abbreviation) {
        FieldOfStudyData fieldOfStudyData = new FieldOfStudyData(name, abbreviation);
        dataTemp.add(fieldOfStudyData);
    }

    private static void initDataTemp() {
        addDataTemp("Bachelor Angewandte Informatik", "B-AI");
        addDataTemp("Bachelor European Computer Science", "B-ECS");
        addDataTemp("Bachelor Technische Informatik", "B-TI");
        addDataTemp("Bachelor Wirtschaftsinformatik", "B-WI");
        addDataTemp("Master Informatik", "M-INF");
    }

    public static void main(ImportDatabaseManager databaseManagerExternal) throws IOException {
        initDataTemp();
        initData(databaseManagerExternal);
    }
}
