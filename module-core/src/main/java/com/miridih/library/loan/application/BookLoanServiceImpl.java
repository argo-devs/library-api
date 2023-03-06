package com.miridih.library.loan.application;

import com.miridih.library.loan.domain.BookLoan;
import com.miridih.library.loan.infrastructure.BookLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private final BookLoanRepository bookLoanRepository;

    private static final int DEFAULT_LOAN_EXTEND_DAYS = 7;

    @Override
    public Page<BookLoan> getAll(Pageable pageable) {
        return bookLoanRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public BookLoan get(Long bookLoanId) {
        return bookLoanRepository
                .findById(bookLoanId)
                .orElseThrow(() -> new RuntimeException("대출한 도서 정보가 존재하지 않습니다."));
    }

    @Override
    public BookLoan loan(BookLoan bookLoan) {
        return bookLoanRepository.save(bookLoan);
    }

    @Override
    public void extend(Long bookLoanId) {
        BookLoan loanedBook = bookLoanRepository
                .findById(bookLoanId)
                .orElseThrow(() -> new RuntimeException("대출한 도서 정보가 존재하지 않습니다."));

        loanedBook.extendLoan(DEFAULT_LOAN_EXTEND_DAYS);
        bookLoanRepository.save(loanedBook);
    }

    @Override
    public void returnBook(Long bookLoanId) {
        BookLoan loanedBook = bookLoanRepository
                .findById(bookLoanId)
                .orElseThrow(() -> new RuntimeException("대출한 도서 정보가 존재하지 않습니다."));

        loanedBook.returnBook();
        bookLoanRepository.save(loanedBook);
    }
}
