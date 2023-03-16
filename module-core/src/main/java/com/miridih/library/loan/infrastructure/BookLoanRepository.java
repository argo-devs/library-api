package com.miridih.library.loan.infrastructure;

import com.miridih.library.account.domain.Account;
import com.miridih.library.book.internal.domain.Book;
import com.miridih.library.loan.domain.BookLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    Page<BookLoan> findAllByOrderByIdDesc(Pageable pageable);
    Optional<BookLoan> findByAccountAndBook(Account account, Book book);
}
