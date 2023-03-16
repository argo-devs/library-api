package com.miridih.library.book.internal.application;

import com.miridih.library.book.internal.domain.BookMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookMetaService {
    Page<BookMeta> getAll(Pageable pageable);
    Page<BookMeta> getByName(String name, Pageable pageable);
    BookMeta getById(Long bookMetaId);
    BookMeta save(BookMeta bookMeta);
    BookMeta update(BookMeta bookMeta);
    void delete(Long bookMetaId);
}
