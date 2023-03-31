package com.miridih.library.book.application.dto;

import com.miridih.library.category.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Getter
@Builder
@ToString
public class BookMetaSearchCondition {
    @Getter
    public enum Sort {
        REGISTERED_DATE("id"),
        NAME("title");

        private final String columnName;

        Sort(String columnName) {
            this.columnName = columnName;
        }
    }

    private Long bookMetaId;
    private Long categoryId;
    private String name;
    private int page;
    private int size;
    private Sort sort;

    public boolean isEmptySearch() {
        return bookMetaId == null && categoryId == null & name == null;
    }

    public boolean isSearchingIndividualBookMeta() {
        return bookMetaId != null;
    }

    public boolean containsAllSearchCondition() {
        return categoryId != null && name != null;
    }

    public Category getCategory() {
        return getCategoryId() != null
                ? Category.of(getCategoryId())
                : null;
    }

    public Pageable getPageable() {
        return PageRequest.of(getPage(), getSize(), ASC, getSort().getColumnName());
    }
}
