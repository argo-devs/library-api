package com.miridih.library.book.internal.domain;

import com.miridih.library.book.internal.domain.enums.BookStatus;
import com.miridih.library.loan.domain.BookLoan;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private BookStatus status;
    private LocalDateTime registeredDate;

    @ManyToOne
    @JoinColumn(name = "book_meta_id")
    private BookMeta bookMeta;

    @OneToOne(mappedBy = "book")
    private BookLoan bookLoan;
}
