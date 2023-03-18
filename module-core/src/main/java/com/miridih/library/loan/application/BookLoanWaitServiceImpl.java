package com.miridih.library.loan.application;

import com.miridih.library.book.domain.Book;
import com.miridih.library.loan.domain.BookLoanWait;
import com.miridih.library.loan.infrastructure.BookLoanWaitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLoanWaitServiceImpl implements BookLoanWaitService {

    private final BookLoanWaitRepository bookLoanWaitRepository;

    @Override
    public List<BookLoanWait> getAllLoanWaitList(Long bookId) {
        return bookLoanWaitRepository.findAllById_BookOrderByRequestedDate(Book.of(bookId));
    }

    @Override
    public void waitForLoan() {

    }

    @Override
    public void deleteWaitList() {

    }
}
