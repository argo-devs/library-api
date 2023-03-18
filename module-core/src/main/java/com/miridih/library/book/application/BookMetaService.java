package com.miridih.library.book.application;

import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookMetaService {
    List<ExternalBookMeta> getExternalBookMetaList(ExternalBookMetaSearchCondition searchCondition);
    Page<BookMeta> getAll(Pageable pageable);
    Page<BookMeta> getByName(String name, Pageable pageable);
    BookMeta getById(Long bookMetaId);
    BookMeta save(BookMeta bookMeta);
    BookMeta update(BookMeta bookMeta);
    void delete(Long bookMetaId);
}
