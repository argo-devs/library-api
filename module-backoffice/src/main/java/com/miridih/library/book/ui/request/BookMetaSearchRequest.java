package com.miridih.library.book.ui.request;

import com.miridih.library.book.application.dto.BookMetaSearchCondition;
import com.miridih.library.book.application.dto.ExternalBookMetaSearchCondition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class BookMetaSearchRequest {

    @Data
    public static class InternalBookMetaSearchRequest {
        private enum Sort {
            REGISTERED_DATE,
            NAME
        }

        @Schema(description = "도서 메타 ID")
        private Long bookMetaId;

        @Schema(description = "카테고리 ID")
        private Long categoryId;

        @Schema(description = "도서 이름")
        private String name;

        private Pagination pagination = new Pagination();

        @Parameter(description = "결과 정렬 기준")
        @Schema(description = "결과 정렬 기준", defaultValue = "REGISTERED_DATE")
        private Sort sort = Sort.REGISTERED_DATE;

        public BookMetaSearchCondition toBookMetaSearchCondition() {
            return BookMetaSearchCondition
                    .builder()
                    .bookMetaId(bookMetaId)
                    .name(name)
                    .categoryId(categoryId)
                    .page(pagination.getPage())
                    .size(pagination.getSize())
                    .sort(BookMetaSearchCondition.Sort.valueOf(sort.name()))
                    .build();
        }
    }

    @Data
    public static class ExternalBookMetaSearchRequest {
        @Schema(description = "도서 이름")
        private String name;
        private Pagination pagination = new Pagination();

        public ExternalBookMetaSearchCondition toExternalBookMetaSearchCondition() {
            return ExternalBookMetaSearchCondition
                    .builder()
                    .query(name)
                    .page(pagination.getPage())
                    .size(pagination.getSize())
                    .build();
        }
    }

    @Data
    private static class Pagination {
        @Schema(name = "page", description = "패이지 번호", defaultValue = "0")
        private int page = 0;

        @Schema(name="size", description = "페이지 결과 개수", defaultValue = "10")
        private int size = 10;
    }
}
