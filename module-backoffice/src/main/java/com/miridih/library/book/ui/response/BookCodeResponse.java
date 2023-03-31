package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.code.BookCode;
import lombok.Getter;

@Getter
public class BookCodeResponse {
    private String bookId;
    private byte[] code;

    public static BookCodeResponse from(BookCode bookCode) {
        BookCodeResponse response = new BookCodeResponse();
        response.bookId = bookCode.getBookId().toString();
        response.code = bookCode.getCode();

        return response;
    }
}
