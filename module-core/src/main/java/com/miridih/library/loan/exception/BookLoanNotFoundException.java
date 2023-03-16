package com.miridih.library.loan.exception;

public class BookLoanNotFoundException extends LoanException {

    public BookLoanNotFoundException(String message, Object data) {
        super(message, data);
    }
}
