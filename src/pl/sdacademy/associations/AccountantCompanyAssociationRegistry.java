package pl.sdacademy.associations;

import pl.sdacademy.exceptions.AccountantCompanyAssociationAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.models.AccountantRegistry;
import pl.sdacademy.models.CompanyRegistry;

import java.util.HashSet;
import java.util.Set;

public class AccountantCompanyAssociationRegistry {

    private static AccountantCompanyAssociationRegistry instance = null;
    private Set<AccountantCompanyAssociation> AccountantCompanyAssociations;


    public AccountantCompanyAssociationRegistry() {
        this.AccountantCompanyAssociations = new HashSet<>();

        this.AccountantCompanyAssociations.add(new AccountantCompanyAssociation("tomasz","1236547896"));
        this.AccountantCompanyAssociations.add(new AccountantCompanyAssociation("marek", "5987643258"));
    }

    public static AccountantCompanyAssociationRegistry getInstance() {
        if (instance == null) {
            instance = new AccountantCompanyAssociationRegistry();
        }
        return instance;
    }

    public void addAccountantCompanyAssociation(AccountantCompanyAssociation AccountantCompanyAssociation)throws AccountantCompanyAssociationAlreadyExistException, AccountantNotFoundException{
        if(!AccountantRegistry.getInstance().findAccountantByLogin(AccountantCompanyAssociation.getAccountantLogin())){
            throw new AccountantNotFoundException("Ksiegowy o loginie " + AccountantCompanyAssociation.getAccountantLogin() + " nie istnieje");
        }

        if(!CompanyRegistry.getInstance().findCompanyForNip(AccountantCompanyAssociation.getNip())){
            throw new AccountantNotFoundException("Firma o NIPie " + AccountantCompanyAssociation.getNip() + " nie istnieje");
        }

        if (AccountantCompanyAssociations.contains(AccountantCompanyAssociation)) {
            throw new AccountantCompanyAssociationAlreadyExistException("Podane przypisanie Ksiegowy-Firma juz istnieje");
        }else {
            AccountantCompanyAssociations.add(AccountantCompanyAssociation);
        }
    }
}
