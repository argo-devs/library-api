package com.miridih.library.book.domain;

import com.miridih.library.book.domain.enums.BookStatus;
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
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private LocalDateTime registeredDate;

    @ManyToOne
    @JoinColumn(name = "book_meta_id")
    private BookMeta bookMeta;

    @OneToOne(mappedBy = "book")
    private BookLoan bookLoan;

    public static Book of(Long id) {
        Book book = new Book();
        book.id = id;

        return book;
    }
    public static Book from(Long bookMetaId) {
        Book book = new Book();
        book.uuid = UUID.randomUUID();
        book.status = BookStatus.ACTIVE;
        book.registeredDate = LocalDateTime.now();
        book.bookMeta = BookMeta.of(bookMetaId);

        return book;
    }

    public void updateStatus(BookStatus status) {
        this.status = status;
    }

    public boolean isAvailableForLoan() {
        return status == BookStatus.ACTIVE;
    }

    public boolean isInLoan() {
        return status == BookStatus.IN_LOAN &&
                bookLoan != null;
    }
}
