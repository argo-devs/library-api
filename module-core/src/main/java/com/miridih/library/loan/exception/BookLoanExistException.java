package com.miridih.library.loan.exception;

public class BookLoanExistException extends LoanException {

    public BookLoanExistException(String message, Object data) {
        super(message, data);
    }
}
