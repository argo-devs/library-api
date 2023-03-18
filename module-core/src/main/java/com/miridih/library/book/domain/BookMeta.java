package com.miridih.library.book.domain;

import com.miridih.library.category.domain.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity(name = "book_meta")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookMeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String isbn;
    private String imageUrl;

    @ManyToOne
//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "bookMeta")
    private List<Book> bookList;

    public static BookMeta of(Long bookMetaId) {
        BookMeta bookMeta = new BookMeta();
        bookMeta.id = bookMetaId;

        return bookMeta;
    }

    public static BookMeta of(String title, String description, String author, String publisher,
                              String isbn, String imageUrl, Long categoryId) {
        BookMeta bookMeta = new BookMeta();
        bookMeta.title = title;
        bookMeta.description = description;
        bookMeta.author = author;
        bookMeta.publisher = publisher;
        bookMeta.isbn = isbn;
        bookMeta.imageUrl = imageUrl;
        bookMeta.category = Category.of(categoryId);

        return bookMeta;
    }
}
