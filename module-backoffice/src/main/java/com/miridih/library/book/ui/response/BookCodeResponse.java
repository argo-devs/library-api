package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.dto.BookCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
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
