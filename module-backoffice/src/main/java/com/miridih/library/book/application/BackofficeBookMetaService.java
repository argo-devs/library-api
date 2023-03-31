package com.miridih.library.book.application;

import com.miridih.library.book.application.dto.BookMetaInfo;
import com.miridih.library.book.application.dto.BookMetaInput;
import com.miridih.library.book.application.code.BookCode;
import com.miridih.library.book.application.dto.BookMetaSearchCondition;
import com.miridih.library.book.application.dto.ExternalBookMetaSearchCondition;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BackofficeBookMetaService {
    Page<ExternalBookMeta> searchExternalBookMeta(ExternalBookMetaSearchCondition searchCondition);
    List<BookCode> getAllBookCode(Long bookMetaId);
    BookMetaInfo searchBookMeta(BookMetaSearchCondition searchCondition);
    BookMeta registerBookMeta(BookMetaInput bookMetaInput);
    void deleteBookMeta(Long bookMetaId);
}
