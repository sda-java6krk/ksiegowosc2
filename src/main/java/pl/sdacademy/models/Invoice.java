package pl.sdacademy.models;

import java.math.BigDecimal;

public class Invoice {

    private String type;
    private BigDecimal netAmmount;
    private double vat;
    private boolean paid;
    private static int idSale = 0;
    private static int idBuy = 0;
    private int invoiceIdSale;
    private int invoiceIdBuy;


    public Invoice(String type, BigDecimal netAmount, double vat, boolean paid) {
        this.type = type;
        this.netAmmount = netAmount;
        this.vat = vat;
        this.paid = paid;
        if (type.equals("Sprzedaz")) {

            invoiceIdSale = idSale;
            idSale++;
        } else if (type.equals("Zakup")) {

            invoiceIdBuy = idBuy;
            idBuy++;
        }
    }

    public String getType() {
        return type;
    }

    public BigDecimal getNetAmmount() {
        return netAmmount;
    }

    public double getVat() {
        return vat;
    }

    public boolean isPaid() {
        return paid;
    }

    @Override
    public String toString() {
        if (type.equals("Zakup")) {
            return "Invoice{" +
                    "type='" + type + '\'' +
                    ", netAmmount=" + netAmmount +
                    ", vat=" + vat +
                    ", paid=" + paid +
                    ", invoiceId=" + invoiceIdBuy +
                    '}';
        }
        return "Invoice{" +
                "type='" + type + '\'' +
                ", netAmmount=" + netAmmount +
                ", vat=" + vat +
                ", paid=" + paid +
                ", invoiceId=" + invoiceIdSale +
                '}';
    }
}