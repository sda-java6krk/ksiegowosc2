package pl.sdacademy.controllers;

import pl.sdacademy.associations.AccountantCompanyAssociation;
import pl.sdacademy.associations.AccountantCompanyAssociationRegistry;
import pl.sdacademy.exceptions.AccountantCompanyAssociationAlreadyExistException;
import pl.sdacademy.exceptions.AccountantNotFoundException;
import pl.sdacademy.exceptions.CompanyNotFoundException;

public class AccountantCompanyAssociationController {

    public static void createAccountantCompanyAssociation(String accountantLogin, String companyNip) throws AccountantCompanyAssociationAlreadyExistException, AccountantNotFoundException, CompanyNotFoundException{
        AccountantCompanyAssociationRegistry.getInstance().addAccountantCompanyAssociation(new AccountantCompanyAssociation(accountantLogin,companyNip));
    }

}
