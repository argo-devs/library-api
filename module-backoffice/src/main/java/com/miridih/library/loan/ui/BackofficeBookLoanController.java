package com.miridih.library.loan.ui;

import com.miridih.library.loan.application.BackofficeBookLoanService;
import com.miridih.library.loan.domain.BookLoan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeBookLoanController {

    private final BackofficeBookLoanService backofficeBookLoanService;

    @GetMapping("/book/loan")
    public BookLoanListResponse getAllLoanBooks(Pageable pageable) {
        Page<BookLoan> bookLoanPage = backofficeBookLoanService.getAllLoanedBook(pageable);

        return BookLoanListResponse.from(bookLoanPage);
    }

    @DeleteMapping("/book/loan/{bookLoanId}")
    public void updateBookLoan(@PathVariable Long bookLoanId) {
        backofficeBookLoanService.returnBook(bookLoanId);
    }
}
