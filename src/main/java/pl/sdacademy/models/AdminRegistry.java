package pl.sdacademy.models;

import pl.sdacademy.controllers.AdminController;
import pl.sdacademy.exceptions.AdminNotFoundException;

import java.io.*;
import java.util.*;

public class AdminRegistry extends Admin implements Serializable {
    private static AdminRegistry instance = null;
    private ArrayList<Admin> admins;

    public AdminRegistry() {
       this.admins = new ArrayList<>();
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

    public void addAdmin(Admin admin) throws IOException {
        boolean exist = false;

        if (admin.getLogin().equals(null) || admin.getLogin().equals("") || admin.getLogin().contains(" ")) {
            System.out.println("Login admina nie moze byc pusty!");
            exist = true;
        }
        if (admin.getPassword().equals(null) || admin.getPassword().length() < 3 || admin.getPassword().contains(" ")) {
            System.out.println("Haslo musi byc zlozone z przynajmniej trzech znakow i nie moze byc spacja!");
            exist = true;
        }

        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getLogin().equals(admin.getLogin())) {
                System.out.println("Admin o takim loginie juz istnieje");
                exist = true;
            }
        }
        if (!exist) {
            this.admins.add(admin);
            AdminController.saveAdmin();
        }

    }

    public void remove(String login) throws IOException {
        boolean removed = false;
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getLogin().equals(login)) {
                admins.remove(admins.get(i));
                AdminController.saveAdmin();
                removed = true;
            }

        }
        if (removed == false) {
            System.out.println("Brak admina o podanym loginie");
        }

    }

    public static void saveAdminToFile(ArrayList<Admin> admins) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/myAdminRegistry.bin"));

        objectOutputStream.writeObject(admins);
        objectOutputStream.close();
    }

    public static void readAdminFromFile(ArrayList<Admin> admins) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/myAdminRegistry.bin"));

        ArrayList<Admin> list = (ArrayList<Admin>) objectInputStream.readObject();

        getInstance().removeAllAdmins();

        for (Admin a : list) {
            getInstance().addAdmin(a);
        }
        objectInputStream.close();
    }

    public void removeAllAdmins() {
        admins = new ArrayList<>();
    }

}
