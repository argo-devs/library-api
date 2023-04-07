package com.miridih.library.book.ui.response;

import com.miridih.library.book.application.code.BookCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BookCodeResponse {
    @Schema(description = "도서 ID", example = "1")
    private String bookId;

    @Schema(description = "QR 코드", example = "iVBORw0KGgoAAAANSUhEUgAAAH0AAAB9AQAAAACn+1GIAAABOElEQVR4Xu3UPZKFIAwH8DAUdrwLOOM16LiSXsCPC+iV6LwGM1xAO4qM2biL7ntVsH+M1a+A+E8A6HMl+MJzCAC0JJoSgCuESAmHFVtLRzGMNu7U7CmYB4B9FZfnsNkHQIlGC116K10AziPOlr+3gATgxT9Xd3fqMgQD+nBh8LQUAzhoK00e+6sOCXDwerR6dnEsBd4DDQS1hu4+VgDa/Tk+ymOb85BhoqBSw5XmRhXAAfhaub16K4VgqoZTV+kMvhB+B6Hm1K89RKCDD6xwSDSXAs9ps1k0ts55yICK9AzY3ZXKECev94Qt6Dx0MtD1f9c4yMC3IbzOxyDkSmXgOxenc47iVYcM/H4cDvrzTpQDjRV3SedKiyC0lt82/d8oCbhSTt04vt+FcOahEvTc4b89ZPhYX3gMP0CLCU2ew9xpAAAAAElFTkSuQmCC")
    private byte[] code;

    public static BookCodeResponse from(BookCode bookCode) {
        BookCodeResponse response = new BookCodeResponse();
        response.bookId = bookCode.getBookId().toString();
        response.code = bookCode.getCode();

        return response;
    }
}
