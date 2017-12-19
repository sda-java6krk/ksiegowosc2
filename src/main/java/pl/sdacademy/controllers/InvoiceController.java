package pl.sdacademy.controllers;

import pl.sdacademy.models.*;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceController {

    public static void createInvoiceForComapny(String type, double netAmount, double vat, boolean paid, Company company){
        InvoiceRegistry.getInstance().addInvoiceForCompany(new Invoice(type,netAmount,vat,paid),company);
    }

    public static void createInvoiceForClient(String type, double netAmount, double vat, boolean paid, Client client){
        InvoiceRegistry.getInstance().addInvoiceForClient(new Invoice(type,netAmount,vat,paid), client);
    }

    public static void invoiceForCompanyList(Company company){
        for (Invoice invoice : company.getInvoices()) {
            System.out.println(invoice);
        }

    }

    public static void invoiceForClient(Client client){
        for (Invoice invoice : client.getInvoicesForClient()) {
            System.out.println(invoice);
        }

    }
}
