package com.miridih.library.book.internal.infrastructure;

import com.miridih.library.book.internal.domain.BookMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMetaRepository extends JpaRepository<BookMeta, Long> {
}
