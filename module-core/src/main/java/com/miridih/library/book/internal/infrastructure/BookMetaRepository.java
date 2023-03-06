package com.miridih.library.book.internal.infrastructure;

import com.miridih.library.book.internal.domain.BookMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMetaRepository extends JpaRepository<BookMeta, Long> {
    Page<BookMeta> findAllByOrderByIdDesc(Pageable pageable);
    Page<BookMeta> findAllByTitleOrderByIdDesc(String title, Pageable pageable);
}
