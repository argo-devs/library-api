package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.ExternalBookMeta;
import com.miridih.library.core.ui.response.PaginationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ExternalBookMetaListResponse {
    @Schema(description = "외부 도서 메타 리스트")
    @NotBlank
    private final List<ExternalBookMetaResponse> bookMetaList = new ArrayList<>();

    @NotBlank
    private PaginationResponse pagination;

    public static ExternalBookMetaListResponse from(Page<ExternalBookMeta> externalBookMetaPage) {
        ExternalBookMetaListResponse response = new ExternalBookMetaListResponse();
        List<ExternalBookMeta> externalBookMetaList = externalBookMetaPage.getContent();
        externalBookMetaList
                .forEach(bookMeta ->
                        response.bookMetaList.add(ExternalBookMetaResponse.from(bookMeta))
                );
        response.pagination = PaginationResponse.from(externalBookMetaPage);

        return response;
    }
}
