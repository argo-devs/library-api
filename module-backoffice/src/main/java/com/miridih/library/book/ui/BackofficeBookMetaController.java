package com.miridih.library.book.ui;

import com.miridih.library.book.application.BackofficeBookMetaService;
import com.miridih.library.book.application.dto.BookMetaInfo;
import com.miridih.library.book.domain.ExternalBookMeta;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.ui.request.BookMetaCreateRequest;
import com.miridih.library.book.ui.request.BookMetaSearchRequest;
import com.miridih.library.book.ui.response.BookCodeResponse;
import com.miridih.library.book.ui.response.BookMetaListResponse;
import com.miridih.library.book.ui.response.BookMetaResponse;
import com.miridih.library.book.ui.response.ExternalBookMetaListResponse;
import com.miridih.library.core.ui.response.BackofficeResponse;
import com.miridih.library.core.ui.response.ErrorStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BackofficeBookMetaController {

    private final BackofficeBookMetaService backofficeBookMetaService;

    /**
     * 도서 (메타) 검색
     * 이른 또는 ID 로 개별 검색을 하거나
     * 비어있으면 전체 검색을 한다 (페이징)
     */
    @GetMapping("/book-meta")
    public BackofficeResponse<BookMetaListResponse> getBookMeta(BookMetaSearchRequest request) {
        log.info("BKMT:SRCH:RQST: 도서 메타 조회 요청. [request={}]", request);

        try {
            BookMetaInfo bookMetaInfo = backofficeBookMetaService.searchBookMeta(request.toBookMetaSearchCondition());

            return BackofficeResponse.of(BookMetaListResponse.from(bookMetaInfo));
        } catch (Exception e) {
            log.error("BKMT:SRCH:FAIL: 도서 메타 조회중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @PostMapping("/book-meta")
    public BackofficeResponse<BookMetaResponse> registerBookMeta(@RequestBody BookMetaCreateRequest request) {
        log.info("BKMT:RGST:RQST: 도서 메타 등록 요청. [request={}]", request);

        try {
            BookMeta bookMeta = backofficeBookMetaService.registerBookMeta(request.toBookMetaInput());

            return BackofficeResponse.of(BookMetaResponse.from(bookMeta));
        } catch (Exception e) {
            log.error("BKMT:RGST:FAIL: 도서 메타 등록중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @DeleteMapping("/book-meta/{bookMetaId}")
    public BackofficeResponse<BookMetaResponse> deleteBookMeta(@PathVariable Long bookMetaId) {
        log.info("BKMT:DEL_:RQST: 도서 메타 삭제 요청. [bookMeta={}]", bookMetaId);

        try {
            backofficeBookMetaService.deleteBookMeta(bookMetaId);

            return BackofficeResponse.of(BookMetaResponse.of(bookMetaId));
        } catch (Exception e) {
            log.error("BKMT:DEL_:FAIL: 도서 메타 삭제중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    // 외부 도서를 검색하는 요청
    @GetMapping("/book-meta/external")
    public BackofficeResponse<ExternalBookMetaListResponse> searchBookMeta(BookMetaSearchRequest request) {
        log.info("EXTN:SRCH:RQST: 외부 도서 메타 조회 요청. [request={}]", request);

        try {
            Page<ExternalBookMeta> externalBookMetaList =
                    backofficeBookMetaService.searchExternalBookMeta(request.toExternalBookMetaSearchCondition());

            return BackofficeResponse.of(ExternalBookMetaListResponse.from(externalBookMetaList));
        } catch (Exception e) {
            log.error("EXTN:SRCH:FAIL: 외부 도서 메타 조회중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }

    @GetMapping("/book-meta/code/{bookMetaId}")
    public BackofficeResponse<List<BookCodeResponse>> getAllQRCode(@PathVariable Long bookMetaId) {
        log.info("BKMT:CODE:RQST: 도서 메타 QR 코드 발급 요청.");

        try {
            List<BookCodeResponse> response = backofficeBookMetaService
                    .getAllBookCode(bookMetaId)
                    .stream()
                    .map(BookCodeResponse::from)
                    .collect(Collectors.toList());

            return BackofficeResponse.of(response);
        } catch (Exception e) {
            log.error("BKMT:CODE:FAIL: 도서 메타 QR 코드 발급중 오류 발생.", e);

            return BackofficeResponse.of(ErrorStatus.E1, e.getMessage());
        }
    }
}
