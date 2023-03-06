package com.miridih.library.loan.application;

import com.miridih.library.loan.domain.BookLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BackofficeBookLoanService {
    Page<BookLoan> getAllLoanedBook(Pageable pageable);
    void returnBook(Long bookLoanId);
}
