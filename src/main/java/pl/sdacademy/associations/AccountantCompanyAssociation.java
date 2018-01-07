package pl.sdacademy.associations;

import java.io.Serializable;

public class AccountantCompanyAssociation implements Serializable{

    private String nip;
    private String accountantLogin;


    public AccountantCompanyAssociation(String accountantLogin, String nip) {
        this.nip = nip;
        this.accountantLogin = accountantLogin;
    }

    public AccountantCompanyAssociation() {

    }

    public String getNip() {
        return nip;
    }

    public String getAccountantLogin() {
        return accountantLogin;
    }


    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }
        if(!(o instanceof AccountantCompanyAssociation)) {
            return false;
        }
        AccountantCompanyAssociation a = (AccountantCompanyAssociation) o;

        int compareLogin = this.getAccountantLogin().toLowerCase().compareTo(a.getAccountantLogin().toLowerCase());
        int compareNip = this.getNip().toLowerCase().compareTo(a.getNip().toLowerCase());

        if(compareLogin == 0 && compareNip == 0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public int hashCode() {
        return this.getNip().hashCode() + this.getAccountantLogin().hashCode();
    }
}
