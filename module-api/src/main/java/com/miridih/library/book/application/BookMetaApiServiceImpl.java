package com.miridih.library.book.application;

import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMetaApiServiceImpl implements BookMetaApiService {

    private final BookMetaService bookMetaService;

    @Override
    public List<ExternalBookMeta> searchExternalBookMeta(ExternalBookMetaSearchCondition searchCondition) {
        return bookMetaService.getExternalBookMetaList(searchCondition);
    }

    @Override
    public Page<BookMeta> searchBookMeta(BookMetaSearchCondition searchCondition) {
        return bookMetaService.getByName(searchCondition.getName(), searchCondition.getPageable());
    }
}
