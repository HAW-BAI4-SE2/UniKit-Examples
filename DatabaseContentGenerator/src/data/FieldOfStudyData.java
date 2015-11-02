package data;

/**
 * Created by Andreas on 02.11.2015.
 */
public class FieldOfStudyData {
    private String name;
    private String abbreviation;

    public FieldOfStudyData(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
