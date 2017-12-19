package pl.sdacademy.controllers;

import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;
import pl.sdacademy.views.AdminView;

import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class AdminController {

    public static void createAdmin(String login, String password) {
        AdminRegistry.getInstance().add(new Admin(login, password));
    }

    public static void removeAdmin(String login) {
        AdminRegistry.getInstance().remove(login);
    }

    public static void listAdmins() {
        AdminView.printAdmin(AdminRegistry.getInstance().getAdmins());
    }
}
