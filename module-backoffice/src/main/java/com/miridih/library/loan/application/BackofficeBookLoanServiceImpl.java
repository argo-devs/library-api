package com.miridih.library.loan.application;

import com.miridih.library.loan.domain.BookLoan;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BackofficeBookLoanServiceImpl implements BackofficeBookLoanService {

    private final BookLoanService bookLoanService;

    @Override
    public Page<BookLoan> getAllLoanedBook(Pageable pageable) {
        return bookLoanService.getAll(pageable);
    }

    @Override
    public void returnBook(Long bookLoanId) {
        bookLoanService.returnBook(bookLoanId);



    }
}
