package com.miridih.library.book.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class BookMetaInput {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String isbn;
    private String imageUrl;
    private int quantity;
    private Long categoryId;
}
