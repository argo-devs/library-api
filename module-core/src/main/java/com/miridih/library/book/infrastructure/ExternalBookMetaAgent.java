package com.miridih.library.book.infrastructure;

import com.miridih.library.book.domain.ExternalBookMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExternalBookMetaAgent {
    Page<ExternalBookMeta> search(String name, Pageable pageable);
}
