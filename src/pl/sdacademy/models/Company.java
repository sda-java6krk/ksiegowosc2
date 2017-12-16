package pl.sdacademy.models;

/**
 * Created by marcin on 13.12.2017.
 */
public class Company {
    private String nip;
    private String name;
    private int yearFound;

    public Company(String nip,String name, int yearFound) {
        this.nip = nip;
        this.name = name;
        this.yearFound = yearFound;
    }
    public String getNip() {
        return nip ;
    }

    public String getName() {
        return name;
    }

    public int getYearFound() {
        return yearFound;
    }
    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearFound(int yearFound) {
        this.yearFound = yearFound;
    }

}
