package com.miridih.library.core.ui.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;

@Getter
public class PaginationResponse {
    @Schema(description = "총 페이지 수", example = "10")
    @NotBlank
    private int totalPageNumber;

    @Schema(description = "총 요소 개수", example = "100")
    @NotBlank
    private int totalElementCount;

    @Schema(description = "현재 페이지 번호", example = "0")
    @NotBlank
    private int currentPageNumber;

    @Schema(description = "검색 결과 수", example = "10")
    @NotBlank
    private int currentElementCount;

    public static PaginationResponse from(Page<?> page) {
        PaginationResponse response = new PaginationResponse();
        response.totalPageNumber = page.getTotalPages();
        response.totalElementCount = (int) page.getTotalElements();
        response.currentPageNumber = page.getNumber();
        response.currentElementCount = page.getNumberOfElements();

        return response;
    }
}
