package com.miridih.library.book.application;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class ExternalBookMetaSearchCondition {
    private String query;
    private int start;
    private int display;
}
