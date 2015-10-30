package controllers;

import models.Contact;
import play.api.data.Mapping;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import static play.data.Form.form;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Welcome to PlayTest!"));
    }

    public static Result search_student() {
        play.data.Form<Contact> userForm = form(Contact.class);
        return ok(search_student.render(userForm));
    }

    public static Result choose_student() {
        return ok(default_view.render("choose_student"));
    }

    public static Result send_invite() {
        //int value2 = Integer.valueOf(request().body().asFormUrlEncoded().get("value")[0]);

        play.data.Form<Contact> userForm = form(Contact.class);
        userForm = userForm.bindFromRequest();
        String name = userForm.data().get("name");

        return ok(send_invite.render(String.valueOf(name)));
    }

    public static Result choose_invite() {
        return ok(default_view.render("choose_invite"));
    }

    public static Result accept_invite() {
        return ok(default_view.render("accept_invite"));
    }

}
