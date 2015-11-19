import models.studentComponent.StudentDatabaseUtils;
import models.studentComponent.TeamDatabaseUtils;
import net.unikit.database.external.interfaces.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;
import play.test.FakeRequest;
import play.test.TestServer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class IntegrationTest {
    private TestServer testServer;

    @Before
    public void init() {
        testServer = testServer(9001, fakeApplication());
    }

    @Test
    public void testIntegration() {
        running(testServer, HTMLUNIT, (browser) -> {
            // Login
            FakeRequest fakeRequest = new FakeRequest(GET, "/courses/overview").
                    withSession("username", "2055120").
                    withSession("timeout", String.valueOf(System.currentTimeMillis()));
            Result result = routeAndCall(fakeRequest);
            assertThat(status(result)).isEqualTo(OK);

            String html = contentAsString(result);
            assertThat(html.contains("Datenbanken")).isTrue();
        });
    }

    @Test
    public void testLogic() {
        running(testServer, () -> {
            int teamId = StudentDatabaseUtils.createTeam("2055120", 1);
            List<Student> allStudents = TeamDatabaseUtils.getAllStudents(TeamDatabaseUtils.getTeamByID(teamId));
            boolean found = false;
            for (Student student : allStudents) {
                if (student.getStudentNumber().equals("2055120"))
                    found = true;
            }
            assertThat(found).isTrue();
        });
    }

    @After
    public void exit() {
        testServer = null;
    }
}
