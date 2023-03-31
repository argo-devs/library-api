package com.miridih.library.book.application.code;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@Builder
public class BookCode {
    private UUID bookId;
    private byte[] code;
}
