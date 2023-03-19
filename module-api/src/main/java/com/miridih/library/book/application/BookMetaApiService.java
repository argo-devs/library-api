package com.miridih.library.book.application;

import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookMetaApiService {
    List<ExternalBookMeta> searchExternalBookMeta(ExternalBookMetaSearchCondition searchCondition);
    Page<BookMeta> searchBookMeta(BookMetaSearchCondition searchCondition);
}
