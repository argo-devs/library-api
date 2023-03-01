package com.miridih.library.loan.domain;

import com.miridih.library.account.domain.Account;
import com.miridih.library.book.internal.domain.Book;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class BookLoanWaitId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
