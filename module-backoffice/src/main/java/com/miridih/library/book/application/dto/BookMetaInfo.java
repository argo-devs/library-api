package com.miridih.library.book.application.dto;

import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.category.domain.Category;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 전테 도서 메타
 */

@Getter
public class BookMetaInfo {
    private Page<BookMeta> content;
    private final List<Category> categoryList = new ArrayList<>();

    public static BookMetaInfo of(Page<BookMeta> bookMetaPage) {
        BookMetaInfo bookMetaInfo = new BookMetaInfo();
        bookMetaInfo.content = bookMetaPage;

        return bookMetaInfo;
    }

    public static BookMetaInfo of(Page<BookMeta> bookMetaPage, List<Category> categoryList) {
        BookMetaInfo bookMetaInfo = new BookMetaInfo();
        bookMetaInfo.content = bookMetaPage;
        bookMetaInfo.addAll(categoryList);

        return bookMetaInfo;
    }

    public void addAll(List<Category> categoryList) {
        this.categoryList.addAll(categoryList);
    }

}
