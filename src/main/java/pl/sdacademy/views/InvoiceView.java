package pl.sdacademy.views;

import pl.sdacademy.models.Invoice;

import java.util.List;

/**
 * Created by marcin on 13.12.2017.
 */
public class InvoiceView {

    public  static void printInvoiceForCompany(List<Invoice> invoiceForCompany) {

        for (Invoice invoice : invoiceForCompany) {
            System.out.println(invoice);
        }
    }
    }