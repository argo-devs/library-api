package com.miridih.library.loan.infrastructure;

import com.miridih.library.book.domain.Book;
import com.miridih.library.loan.domain.BookLoanWait;
import com.miridih.library.loan.domain.BookLoanWaitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookLoanWaitRepository extends JpaRepository<BookLoanWait, BookLoanWaitId> {
    List<BookLoanWait> findAllById_BookOrderByRequestedDate(Book book);
}
