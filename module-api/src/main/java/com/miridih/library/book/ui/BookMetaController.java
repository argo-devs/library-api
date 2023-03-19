package com.miridih.library.book.ui;

import com.miridih.library.book.application.BookMetaApiService;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.domain.ExternalBookMeta;
import com.miridih.library.book.ui.request.BookMetaSearchRequest;
import com.miridih.library.book.ui.response.BookMetaListResponse;
import com.miridih.library.core.ui.response.ApiResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookMetaController {

    private final BookMetaApiService bookMetaApiService;

    @GetMapping("/book-meta/external")
    public ApiResponse<BookMetaListResponse> searchExternalBookMeta(BookMetaSearchRequest request) {
        log.info("EXTN:SRCH:RQST: 외부 도서 검색 요청. [request={}]", request);

        try {
            List<ExternalBookMeta> externalBookMetaList =
                    bookMetaApiService.searchExternalBookMeta(request.toExternalBookMetaSearchCondition());
            BookMetaListResponse response = BookMetaListResponse.from(externalBookMetaList);

            return ApiResponse.of(response);
        } catch (Exception e) {
            log.error("EXTN:SRCH:FAIL: 외부 도서 메타 조회중 오류 발생.", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }

    @GetMapping("/book-meta")
    public ApiResponse<BookMetaListResponse> searchBookMeta(BookMetaSearchRequest request) {
        log.info("BKMT:SRCH:RQST: 도서 메타 검색 요청. [request={}]", request);

        try {
            Page<BookMeta> bookMetaPage = bookMetaApiService.searchBookMeta(request.toBookMetaSearchCondition());
            BookMetaListResponse response = BookMetaListResponse.from(bookMetaPage);

            return ApiResponse.of(response);
        } catch (Exception e) {
            log.error("BKMT:SRCH:FAIL: 도서 메타 조회중 오류 발생.", e);
            return ApiResponse.of(ErrorStatus.E1, "관리자에게 문의 바랍니다.");
        }
    }
}
