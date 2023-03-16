package com.miridih.library.loan.application;

import com.miridih.library.loan.domain.BookLoan;
import com.miridih.library.loan.exception.BookLoanExistException;
import com.miridih.library.loan.exception.BookLoanNotFoundException;
import com.miridih.library.loan.infrastructure.BookLoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private static final int DEFAULT_LOAN_EXTEND_DAYS = 7;

    private final BookLoanRepository bookLoanRepository;

    @Override
    public Page<BookLoan> getAll(Pageable pageable) {
        return bookLoanRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public BookLoan getById(Long bookLoanId) {
        return bookLoanRepository
                .findById(bookLoanId)
                .orElseThrow(() -> new BookLoanNotFoundException("대출한 도서 정보가 존재하지 않습니다.", bookLoanId));
    }

    @Override
    public BookLoan loan(BookLoan bookLoan) {
        bookLoanRepository
                .findByAccountAndBook(bookLoan.getAccount(), bookLoan.getBook())
                .ifPresent(loan -> {
                    throw new BookLoanExistException("도서를 대출중입니다.", bookLoan);
                });

        return bookLoanRepository.save(bookLoan);
    }

    @Override
    public void extend(Long bookLoanId) {
        BookLoan loanedBook = getById(bookLoanId);
        loanedBook.extendLoan(DEFAULT_LOAN_EXTEND_DAYS);

        bookLoanRepository.save(loanedBook);
    }

    @Override
    public void returnBook(Long bookLoanId) {
        BookLoan loanedBook = getById(bookLoanId);
        loanedBook.returnBook();

        bookLoanRepository.save(loanedBook);
    }
}
