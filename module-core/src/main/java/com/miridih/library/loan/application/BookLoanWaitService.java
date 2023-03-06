package com.miridih.library.loan.application;

import com.miridih.library.loan.domain.BookLoanWait;

import java.util.List;

public interface BookLoanWaitService {
    List<BookLoanWait> getAllLoanWaitList(Long bookId);
    void waitForLoan();
    void deleteWaitList();
}
