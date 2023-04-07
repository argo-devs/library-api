package com.miridih.library.book.ui.response;

import com.miridih.library.book.domain.BookMeta;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookMetaResponse {
    @Getter
    private static class Category {
        @Schema(description = "카테고리 ID", example = "1")
        @NotBlank
        private Long id;

        @Schema(description = "카테고리 이름", example = "개발")
        @NotBlank
        private String name;

        private static Category of(Long id, String name) {
            Category response = new Category();
            response.id = id;
            response.name = name;

            return  response;
        }
    }

    @Schema(description = "도서 메타 ID", example = "1")
    @NotBlank
    private Long bookMetaId;

    @Schema(description = "도서 제목", example = "알고리즘 101")
    @NotBlank
    private String title;

    @Schema(description = "도서 설명", example = "이 책은 알고리즘 책입니다.")
    @NotBlank
    private String description;

    @Schema(description = "저자", example = "미리리")
    @NotBlank
    private String author;

    @Schema(description = "출판사", example = "미리출판사")
    @NotBlank
    private String publisher;

    @Schema(description = "ISBN", example = "9123812938")
    @NotBlank
    private String isbn;

    @Schema(description = "도서 이미지 링크", example = "https://image.com")
    private String imageUrl;

    @Schema(description = "카테고리 정보")
    private Category category;

    @Schema(description = "도서 리스트")
    private List<BookResponse> bookList;

    public static BookMetaResponse from(BookMeta bookMeta) {
        BookMetaResponse response = new BookMetaResponse();
        response.bookMetaId = bookMeta.getId();
        response.title = bookMeta.getTitle();
        response.description = bookMeta.getDescription();
        response.author = bookMeta.getAuthor();
        response.publisher = bookMeta.getPublisher();
        response.isbn = bookMeta.getIsbn();
        response.imageUrl = bookMeta.getImageUrl();
        response.category = Category.of(bookMeta.getCategory().getId(), bookMeta.getCategory().getDisplayName());
        response.bookList = bookMeta
                .getBookList()
                .stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());

        return response;
    }

    public static BookMetaResponse of(Long bookMetaId) {
        BookMetaResponse response = new BookMetaResponse();
        response.bookMetaId = bookMetaId;

        return response;
    }
}
