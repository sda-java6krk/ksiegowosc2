package pl.sdacademy.views;

import pl.sdacademy.models.Admin;

import java.util.List;

public class AdminView {
    public static void printAdmin(List<Admin> admins) {
        for (Admin admin : admins) {
            System.out.println("Login: " + admin.getLogin());
        }
    }
}
