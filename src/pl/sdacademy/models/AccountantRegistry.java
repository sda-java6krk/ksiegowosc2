package pl.sdacademy.models;

import pl.sdacademy.exceptions.AccountantNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountantRegistry extends Accountant implements Serializable {
    private static AccountantRegistry instance = null;
    private ArrayList<Accountant> accountants;




    public AccountantRegistry() {
        this.accountants = new ArrayList<>();

        //this.accountants.add(new Accountant("Tomasz", "123"));
        //this.accountants.add(new Accountant("Marek", "123"));
    }

    public static AccountantRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantRegistry();
        }
        return instance;
    }

    public void addAccountant(Accountant accountant) {

        accountants.add(accountant);
    }

    public static void saveAccountantToFile(List<Accountant> accountants) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream =
              new ObjectOutputStream(new FileOutputStream("myAcountantRegistry.bin"));

        objectOutputStream.writeObject(accountants);
        objectOutputStream.close();

    }

    public static void readAccountants() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream("myAcountantRegistry.bin"));

        List<Accountant> list = (List<Accountant>) objectInputStream.readObject();

        for(Accountant a : list){
            getInstance().addAccountant(a);
        }

        objectInputStream.close();

        /*for(Accountant acs : list){ WYPISYWANIE LISTY KSIĘGOWYCH
            System.out.println(acs.login);
            System.out.println(acs.password);
        }*/

    }
    /*public static void showAccounutants() {
        for(Accountant acc : getInstance().accountants) {
            System.out.println(acc.login);
        }
    }*/

    public void removeAccountant(String login) {
        boolean removed = false;

        for (int i = 0; i < accountants.size(); i++) {
            if (accountants.get(i).getLogin().equals(login)) {
                accountants.remove(i);
                removed = true;
            }
        }

        /*for(Accountant ac : accountants){
            if(ac.getLogin().equals(login)){
                accountants.remove(ac);
                removed=true;
            }
        }*/
        if (removed == false) {
            System.out.println("Nie ma takiego ksiegowego");
        }

    }

    public Accountant findAccountant(String login, String password) throws AccountantNotFoundException {
        for (Accountant accountant : accountants) {
            if (accountant.getLogin().equals(login) && accountant.getPassword().equals(password)) {
                return accountant;
            }
        }

        throw new AccountantNotFoundException();

    }

    public ArrayList<Accountant> getAccountants() {
        return accountants;
    }

}
