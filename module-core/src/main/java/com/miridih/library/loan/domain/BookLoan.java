package com.miridih.library.loan.domain;

import com.miridih.library.account.domain.Account;
import com.miridih.library.book.internal.domain.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity(name = "book_loan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime loanedDate;
    private LocalDateTime toBeReturnedDate;
    private LocalDateTime returnedDate;


    public void extendLoan(int days) {
        toBeReturnedDate = toBeReturnedDate.plusDays(days);
    }

    public void returnBook() {
        returnBook(LocalDateTime.now());
    }

    public void returnBook(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;

    }
}
