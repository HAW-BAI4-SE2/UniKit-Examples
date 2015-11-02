package data;

/**
 * Created by Andreas on 02.11.2015.
 */
public class CourseData {
    private String name;
    private String abbreviation;
    private Integer semester;
    private int minTeamSize;
    private int maxTeamSize;

    public CourseData(String name, String abbreviation, int minTeamSize, int maxTeamSize) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.semester = null;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    public CourseData(String name, String abbreviation, int semester, int minTeamSize, int maxTeamSize) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.semester = semester;
        this.minTeamSize = minTeamSize;
        this.maxTeamSize = maxTeamSize;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public Integer getSemester() {
        return semester;
    }

    public int getMinTeamSize() {
        return minTeamSize;
    }

    public int getMaxTeamSize() {
        return maxTeamSize;
    }
}
