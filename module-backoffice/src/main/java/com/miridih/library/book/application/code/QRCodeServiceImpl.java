package com.miridih.library.book.application.code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.miridih.library.book.domain.Book;
import com.miridih.library.book.exception.BookNotFoundException;
import com.miridih.library.book.infrastructure.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class QRCodeServiceImpl implements QRCodeService {

    @Value("${book.code.path:}")
    private String bookCodePath;

    private final static int QR_CODE_WIDTH = 125;
    private final static int QR_CODE_HEIGHT = 125;
    private final static String QR_CODE_FORMAT = "PNG";

    private final BookRepository bookRepository;

    @Override
    public BookCode getCode(Long bookId) throws IOException, WriterException {
        Book book =  bookRepository
                .findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("도서를 찾을 수 없습니다.", bookId));

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
}
