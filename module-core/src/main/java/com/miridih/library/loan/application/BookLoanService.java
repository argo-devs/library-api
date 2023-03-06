package com.miridih.library.loan.application;

import com.miridih.library.loan.domain.BookLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookLoanService {
    Page<BookLoan> getAll(Pageable pageable);
    BookLoan get(Long bookLoanId);
    BookLoan loan(BookLoan bookLoan);
    void extend(Long bookLoanId);
    void returnBook(Long bookLoanId);
}
