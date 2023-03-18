package com.miridih.library.book.application;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.miridih.library.book.application.dto.BookCode;
import com.miridih.library.book.domain.Book;
import com.miridih.library.book.domain.enums.BookStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BackofficeBookServiceImpl implements BackofficeBookService {

    private final BookService bookService;

    @Value("${book.code.path:}")
    private String bookCodePath;

    private final static int QR_CODE_WIDTH = 125;
    private final static int QR_CODE_HEIGHT = 125;
    private final static String QR_CODE_FORMAT = "PNG";

    @Override
    public BookCode getCode(Long bookId) throws IOException, WriterException {
        Book book = bookService.getById(bookId);
        byte[] code = createQRCode(bookId);

        // 도서 QR 정보
        return BookCode
                .builder()
                .bookId(book.getUuid())
                .code(code)
                .build();
    }

    private byte[] createQRCode(Long bookId) throws IOException, WriterException {
        // QR 생성
        final String content = bookCodePath + "/" + bookId;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, QR_CODE_FORMAT, outputStream);

        return outputStream.toByteArray();
    }

    @Override
    public Book getBook(Long bookId) {
        return bookService.getById(bookId);
    }

    @Transactional
    @Override
    public Book registerBook(Long bookMetaId) {
        return bookService.save(Book.from(bookMetaId));
    }

    @Override
    public void deleteBook(Long bookId) {
        bookService.delete(bookId);
    }

    @Override
    public void deactivateBook(Long bookId) {
        Book book = bookService.getById(bookId);

        validateStatusUpdate(book.getStatus());
        book.updateStatus(BookStatus.INACTIVE);

        bookService.update(book);
    }

    @Override
    public void activateBook(Long bookId) {
        Book book = bookService.getById(bookId);

        validateStatusUpdate(book.getStatus());
        book.updateStatus(BookStatus.ACTIVE);

        bookService.update(book);
    }

    private void validateStatusUpdate(BookStatus status) {
        if(status == BookStatus.IN_LOAN) {
            throw new RuntimeException("");
        }
    }
}
