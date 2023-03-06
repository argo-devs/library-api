package com.miridih.library.book.application.external;

import com.miridih.library.book.application.dto.ExternalBookMeta;
import com.miridih.library.book.application.dto.ExternalBookMetaSearchCondition;

import java.util.List;

public interface ExternalBookMetaService {
    List<ExternalBookMeta> search(ExternalBookMetaSearchCondition searchCondition);
}
