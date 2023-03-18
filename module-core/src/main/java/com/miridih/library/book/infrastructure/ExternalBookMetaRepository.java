package com.miridih.library.book.infrastructure;

import com.miridih.library.book.application.ExternalBookMetaSearchCondition;
import com.miridih.library.book.domain.ExternalBookMeta;

import java.util.List;

public interface ExternalBookMetaRepository {
    List<ExternalBookMeta> search(ExternalBookMetaSearchCondition searchCondition);

}
