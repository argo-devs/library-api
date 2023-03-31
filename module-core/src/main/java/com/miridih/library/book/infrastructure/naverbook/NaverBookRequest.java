package com.miridih.library.book.infrastructure.naverbook;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Getter
@Builder
@ToString
class NaverBookRequest {
    @Getter
    public enum SortType {
        SIM("sim"),
        DATE("date"),
        ;

        private final String value;

        SortType(String value) {
            this.value = value;
        }
    }

    private static final Integer MIN_DISPLAY_VALUE = 10;
    private static final Integer MAX_DISPLAY_VALUE = 100;
    private static final Integer MIN_START_VALUE = 1;
    private static final Integer MAX_START_VALUE = 100;

    private String query;
    private String isbn;

    @Builder.Default
    private Integer display = 15;

    @Builder.Default
    private Integer start = 1;

    @Builder.Default
    private SortType sort = SortType.SIM;

    public Integer getDisplay() {
        if(display < 10) {
            return MIN_DISPLAY_VALUE;
        } else if(display > 100) {
            return MAX_DISPLAY_VALUE;
        }

        return display;
    }

    public Integer getStart() {
        if(start < 1) {
            return MIN_START_VALUE;
        } else if(start > 100) {
            return MAX_START_VALUE;
        }

        return start;
    }

    public URI toURI(String requestUrl) {
        return UriComponentsBuilder
                .fromHttpUrl(requestUrl)
                .queryParam("query", getQuery())
                .queryParam("display", getDisplay())
                .queryParam("start", getStart())
                .queryParam("sort", getSort().getValue())
                .encode() // UTF-8 인코딩
                .build()
                .toUri();
    }
}
