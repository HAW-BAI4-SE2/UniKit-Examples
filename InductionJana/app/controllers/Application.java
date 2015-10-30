package controllers;

import java.util.ArrayList;
import java.util.List;

import com.ning.http.client.Request;

import anmelden.anmelden;
import play.Routes;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import veranstaltungen.implementations.VeranstaltungenUtils;
import veranstaltungen.interfaces.Veranstaltung;
import se2.benutzer.BenutzerUtils;
import se2.studenten.implementations.StudentenUtils;
import se2.studenten.interfaces.Student;

public class Application extends Controller {

	anmelden an;

	public Application() {
		super();
		an = new anmelden();
	}

	public Result index() {
		Student currentUser = BenutzerUtils.getCurrentUser();

		List<Veranstaltung> veranstaltungsList = VeranstaltungenUtils.getVeranstaltungen();

		return ok(views.html.index.render(veranstaltungsList, currentUser));
	}

	public Result takepart() {

		DynamicForm requestData = Form.form().bindFromRequest();
		Student currentUser = BenutzerUtils.getCurrentUser();

		List<Veranstaltung> veranstaltungsList = VeranstaltungenUtils.getVeranstaltungen();
		if (requestData.get("takepart") != null) {
			String identifier = requestData.get("takepart");
			this.an.takePart(identifier);
			return ok(views.html.index.render(veranstaltungsList, currentUser));
		} else {
			return notFound("Nicht gefunden");
		}
	}
	
	public Result signOut() {

		Student currentUser = BenutzerUtils.getCurrentUser();

		List<Veranstaltung> veranstaltungsList = VeranstaltungenUtils.getVeranstaltungen();
		DynamicForm requestData = Form.form().bindFromRequest();

		if (requestData.get("signOut") != null) {
			String identifier = requestData.get("signOut");
			this.an.signOut(identifier);
			return ok(views.html.index.render(veranstaltungsList, currentUser));
		} else {
			return notFound("Nicht gefunden");
		}
	}

	public Result watchParticipants() {
		DynamicForm requestData = Form.form().bindFromRequest();

		if (requestData.get("watchParticipants") != null) {

			String identifier = requestData.get("watchParticipants");
			List<Student> participants = this.an.showPaticipantsOfACourse(identifier);
			return ok(views.html.participants.render(participants, identifier));
		}
		else {
			return notFound("Nicht gefunden");
		}
	}
	
	public Result allMyCourses(){
		DynamicForm requestData = Form.form().bindFromRequest();

		if (requestData.get("allMyCourses") != null) {

			String studentIdentifier = requestData.get("allMyCourses");
			Student currentUser = BenutzerUtils.getCurrentUser();
			String userName = currentUser.getFirstname() + " " + currentUser.getLastname();
			List<Veranstaltung> myCourses = this.an.allMyCourses();
			return ok(views.html.myCourses.render(myCourses,userName));
		}
		else {
			return notFound("Nicht gefunden");
		}
	}


}
