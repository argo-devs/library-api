package com.miridih.library.book.internal.infrastructure;

import com.miridih.library.book.internal.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
