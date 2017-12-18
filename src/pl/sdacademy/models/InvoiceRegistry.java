package pl.sdacademy.models;

import java.util.ArrayList;
import java.util.List;

public class InvoiceRegistry {


    private static InvoiceRegistry instance = null;



    public static InvoiceRegistry getInstance() {
        if (instance == null) {
            instance = new InvoiceRegistry();
        }
        return instance;
    }



    public void addInvoiceForCompany(Invoice invoice, Company company) {

        company.getInvoices().add(invoice);
    }

    public void addInvoiceForClient(Invoice invoice, Client client) {

        client.getInvoicesForClient().add(invoice);
    }


}
