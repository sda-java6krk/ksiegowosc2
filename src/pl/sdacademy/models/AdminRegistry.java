package pl.sdacademy.models;

import pl.sdacademy.exceptions.AdminNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class AdminRegistry {
    private static AdminRegistry instance = null;
    private ArrayList<Admin> admins;

    public AdminRegistry() {
        this.admins = new ArrayList<>();

        this.admins.add(new Admin("adam", "123"));
        this.admins.add(new Admin("ziutek", "456"));
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static AdminRegistry getInstance() {
        if (instance == null) {
            instance = new AdminRegistry();
        }
        return instance;
    }


    public Admin findAdmin(String login, String password) throws AdminNotFoundException {
        for (Admin admin : admins) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        throw new AdminNotFoundException();
    }

    public void add(Admin admin) {
        this.admins.add(admin);
    }

    public void remove(String login) {
        boolean removed = false;
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getLogin().equals(login)) {
                admins.remove(admins.get(i));
                removed = true;
            }

        }
        if (removed == false) {
            System.out.println("Brak admina o podanym loginie");
        }

    }

}
