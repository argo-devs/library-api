package com.miridih.library.book.application;

import com.miridih.library.book.application.dto.*;
import com.miridih.library.book.internal.domain.BookMeta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BackofficeBookMetaService {
    List<ExternalBookMeta> searchBookMeta(ExternalBookMetaSearchCondition searchCondition);
    List<BookCode> getAllBookCode(Long bookMetaId);
    Page<BookMeta> searchBookMeta(BookMetaSearchCondition searchCondition);
    BookMeta registerBookMeta(BookMetaInput bookMetaInput);
    BookMeta updateBookMeta(BookMetaInput bookMetaInput);
    void deleteBookMeta(Long bookMetaId);
}
