package pl.sdacademy.controllers;

import pl.sdacademy.models.Admin;
import pl.sdacademy.models.AdminRegistry;
import pl.sdacademy.views.AdminView;
import java.io.IOException;


public class AdminController {

    public static void createAdmin(String login, String password) throws IOException {
        AdminRegistry.getInstance().addAdmin(new Admin(login, password));
    }

    public static void removeAdmin(String login) throws IOException {
        AdminRegistry.getInstance().remove(login);
    }

    public static void listAdmins() {
        AdminView.printAdmin(AdminRegistry.getInstance().getAdmins());
    }

    public static void saveAdmin() throws IOException {
        AdminRegistry.saveAdminToFile(AdminRegistry.getInstance().getAdmins());
    }

    public static void readAdmin() throws IOException, ClassNotFoundException {
        AdminRegistry.readAdminFromFile(AdminRegistry.getInstance().getAdmins());
    }
}
