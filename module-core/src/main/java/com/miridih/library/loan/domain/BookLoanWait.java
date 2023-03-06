package com.miridih.library.loan.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Entity(name = "book_loan_wait")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookLoanWait {
    @EmbeddedId
    private BookLoanWaitId id;
    private LocalDateTime requestedDate;
}
