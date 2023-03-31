package com.miridih.library.book.application.code;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeService {
    BookCode getCode(Long bookId) throws IOException, WriterException;
}
