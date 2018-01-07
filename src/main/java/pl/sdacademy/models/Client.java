package pl.sdacademy.models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String surname;
    private String nip;
    private List<Invoice> invoicesForClient = new ArrayList<>();

    public Client(String name, String surname, String nip) {
        this.name = name;
        this.surname = surname;
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNip() {
        return nip;
    }

    public List<Invoice> getInvoicesForClient() {
        return invoicesForClient;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nip='" + nip + '\'' +
                ", invoicesForClient=" + invoicesForClient +
                '}';
    }
}
