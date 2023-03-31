package com.miridih.library.book.infrastructure;

import com.miridih.library.book.domain.BookMeta;

import com.miridih.library.category.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMetaRepository extends JpaRepository<BookMeta, Long> {
    Page<BookMeta> findAll(Pageable pageable);
    Page<BookMeta> findAllByTitleContainingOrCategory(String title, Category category, Pageable pageable);
    Page<BookMeta> findAllByTitleContainingAndCategory(String title, Category category, Pageable pageable);
}
