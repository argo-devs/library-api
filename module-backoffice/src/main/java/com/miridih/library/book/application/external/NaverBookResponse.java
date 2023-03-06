package com.miridih.library.book.application.external;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
class NaverBookResponse {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBook> items;
}
