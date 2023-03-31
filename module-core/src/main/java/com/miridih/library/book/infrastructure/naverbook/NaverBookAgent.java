package com.miridih.library.book.infrastructure.naverbook;

import com.miridih.library.book.domain.ExternalBookMeta;
import com.miridih.library.book.exception.ExternalBookMetaAgentException;
import com.miridih.library.book.infrastructure.ExternalBookMetaAgent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
class NaverBookAgent implements ExternalBookMetaAgent {

    private static final String NAVER_BOOK_SEARCH_API = "https://openapi.naver.com/v1/search/book.json";
    private static final String NAVER_CLIENT_ID_HEADER = "X-Naver-Client-Id";
    private static final String NAVER_CLIENT_SECRET_HEADER = "X-Naver-Client-Secret";
    private static final int DEFAULT_OFFSET = 1;

    private final RestTemplate restTemplate;

    @Value("${naver.book.api.id:}")
    private String clientId;

    @Value("${naver.book.api.secret:}")
    private String clientSecret;

    @Override
    public Page<ExternalBookMeta> search(String name, Pageable pageable) {
        List<ExternalBookMeta> bookMetaList = new ArrayList<>();
        if(name == null || name.isBlank()) {
            new PageImpl<>(bookMetaList);
        }

        // 조회 요청 데이터 생성
        int offset = getOffset(pageable);
        NaverBookRequest naverBookRequest =
                NaverBookRequest
                        .builder()
                        .query(name)
                        .start(offset)
                        .display(pageable.getPageSize())
                        .build();

        // 조회 URI 생성
        URI uri = naverBookRequest.toURI(NAVER_BOOK_SEARCH_API);

        // API 요청 및 응답
        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header(NAVER_CLIENT_ID_HEADER, clientId)
                .header(NAVER_CLIENT_SECRET_HEADER, clientSecret)
                .build();

        ResponseEntity<NaverBookResponse> response =
                restTemplate.exchange(requestEntity, NaverBookResponse.class);

        NaverBookResponse naverBookResponse = response.getBody();

        // API 결과
        if(naverBookResponse == null) {
            throw new ExternalBookMetaAgentException("네이버 책 조회 결과가 null 입니다.", naverBookRequest);
        }

        // 네이버 책 -> 미리디 책 정보로 변경
        List<NaverBook> naverBookList = naverBookResponse.getItems();
        naverBookList.forEach(book -> bookMetaList.add(book.toExternalBookMeta()));

        return new PageImpl<>(bookMetaList, pageable, naverBookResponse.getTotal());
    }

    private int getOffset(Pageable pageable) {
        return (int) pageable.getOffset() + DEFAULT_OFFSET;
    }
}
