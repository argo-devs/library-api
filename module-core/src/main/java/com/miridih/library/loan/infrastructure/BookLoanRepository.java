package com.miridih.library.loan.infrastructure;

import com.miridih.library.loan.domain.BookLoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    Page<BookLoan> findAllByOrderByIdDesc(Pageable pageable);
}
