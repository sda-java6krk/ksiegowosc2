package pl.sdacademy.models;

import java.util.ArrayList;
import java.util.List;

public class ClientRegistry {

    private static ClientRegistry instance = null;

    public static ClientRegistry getInstance() {
        if (instance == null) {
            instance = new ClientRegistry();
        }
        return instance;
    }


    private List<Client> clients;

    public ClientRegistry() {
        this.clients = new ArrayList<>();
        this.clients.add(new Client("mietek", "mrukol", "5272525995"));
        this.clients.add(new Client("pawel", "mrukol", "5272525995"));

    }

    public Client findClientByNip(String nip) {
        for (Client client : clients) {
            if (client.getNip().equals(nip)) {
                return client;
            }
        }
        return null;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }
}
