package com.miridih.library.book.application;

import com.miridih.library.book.application.dto.BookCode;
import com.miridih.library.book.application.dto.BookMetaInput;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BackofficeBookMetaService {
    List<ExternalBookMeta> searchExternalBookMeta(ExternalBookMetaSearchCondition searchCondition);
    List<BookCode> getAllBookCode(Long bookMetaId);
    Page<BookMeta> searchBookMeta(BookMetaSearchCondition searchCondition);
    BookMeta registerBookMeta(BookMetaInput bookMetaInput);
    BookMeta updateBookMeta(BookMetaInput bookMetaInput);
    void deleteBookMeta(Long bookMetaId);
}
