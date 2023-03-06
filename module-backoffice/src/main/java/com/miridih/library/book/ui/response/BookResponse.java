package com.miridih.library.book.ui.response;

import com.miridih.library.book.internal.domain.Book;
import lombok.Data;

@Data
public class BookResponse {



    public static BookResponse from(Book book) {
        BookResponse response = new BookResponse();

        return response;
    }
}
