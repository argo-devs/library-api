package com.miridih.library.book.ui;

import com.miridih.library.book.application.BackofficeBookMetaService;
import com.miridih.library.book.application.dto.BookCode;
import com.miridih.library.book.application.dto.ExternalBookMeta;
import com.miridih.library.book.internal.domain.BookMeta;
import com.miridih.library.book.ui.request.BookMetaCreateRequest;
import com.miridih.library.book.ui.request.BookMetaSearchRequest;
import com.miridih.library.book.ui.request.BookMetaUpdateRequest;
import com.miridih.library.book.ui.response.BookCodeResponse;
import com.miridih.library.book.ui.response.BookMetaListResponse;
import com.miridih.library.book.ui.response.BookMetaResponse;
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
    public BookMetaListResponse getBookMeta(BookMetaSearchRequest request) {
        log.info("Request: {}", request);
        Page<BookMeta> bookMetaPage = backofficeBookMetaService.searchBookMeta(request.toBookMetaSearchCondition());

        return BookMetaListResponse.from(bookMetaPage);
    }

    @PostMapping("/book-meta")
    public BookMetaResponse registerBookMeta(@RequestBody BookMetaCreateRequest request) {
        log.info("Request: {}", request);
        BookMeta bookMeta = backofficeBookMetaService.registerBookMeta(request.toBookMetaInput());

        return BookMetaResponse.from(bookMeta);
    }

    @PutMapping("/book-meta")
    public BookMetaResponse updateBookMeta(@RequestBody BookMetaUpdateRequest request) {
        log.info("Request: {}", request);
        BookMeta bookMeta = backofficeBookMetaService.updateBookMeta(request.toBookMetaInput());

        return BookMetaResponse.from(bookMeta);
    }

    @DeleteMapping("/book-meta/{bookMetaId}")
    public void deleteBookMeta(@PathVariable Long bookMetaId) {
        backofficeBookMetaService.deleteBookMeta(bookMetaId);
    }

    // 외부 도서를 검색하는 요청
    @GetMapping("/book-meta/external")
    public BookMetaListResponse searchBookMeta(BookMetaSearchRequest request) {
        log.info("Request: {}", request);
        List<ExternalBookMeta> externalBookMetaList = backofficeBookMetaService.searchBookMeta(request.toExternalBookMetaSearchCondition());

        return BookMetaListResponse.from(externalBookMetaList);
    }

    @GetMapping("/book-meta/code/{bookMetaId}")
    public List<BookCodeResponse> getAllQRCode(@PathVariable Long bookMetaId) {
        List<BookCode> bookCodeList = backofficeBookMetaService.getAllBookCode(bookMetaId);

        return bookCodeList
                .stream()
                .map(BookCodeResponse::from)
                .collect(Collectors.toList());
    }
}
