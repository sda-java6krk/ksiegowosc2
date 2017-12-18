package pl.sdacademy.exceptions;

public class AccountantNotFoundException extends Exception {
    public AccountantNotFoundException() {
        super();
    }

    public AccountantNotFoundException(String message) {
        super(message);
    }
}
