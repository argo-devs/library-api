package com.miridih.library.category.domain;

import com.miridih.library.book.domain.BookMeta;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    private static final String DEFAULT_BOOK_CATEGORY_VALUE = "기타";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String displayName;

    @OneToMany(mappedBy = "category")
    private List<BookMeta> bookMetaList = new ArrayList<>();

    public static Category of(Long id) {
        Category category = new Category();
        category.id = id;

        return category;
    }

    public static Category of(String name) {
        Category category = new Category();
        category.name = name;
        category.displayName = name;

        return category;
    }

    public static Category of(Long id, String name) {
        Category category = new Category();
        category.id = id;
        category.name = name;
        category.displayName = name;

        return category;
    }

    public boolean isEqualTo(String categoryName) {
        return categoryName.equals(name)
                && categoryName.equals(displayName);
    }

    public boolean isDefault() {
        return DEFAULT_BOOK_CATEGORY_VALUE.equals(name)
                && DEFAULT_BOOK_CATEGORY_VALUE.equals(displayName);
    }

    public boolean isDeleted() {
        return DEFAULT_BOOK_CATEGORY_VALUE.equals(displayName)
                && !DEFAULT_BOOK_CATEGORY_VALUE.equals(name);
    }

    public void delete() {
        displayName = DEFAULT_BOOK_CATEGORY_VALUE;
    }
}
