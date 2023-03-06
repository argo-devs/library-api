package com.miridih.library.book.internal.application;

import com.miridih.library.book.internal.domain.BookMeta;
import com.miridih.library.book.internal.infrastructure.BookMetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMetaServiceImpl implements BookMetaService {

    private final BookMetaRepository bookMetaRepository;

    @Override
    public Page<BookMeta> getAll(Pageable pageable) {
        return bookMetaRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Page<BookMeta> getByName(String name, Pageable pageable) {
        return bookMetaRepository.findAllByTitleOrderByIdDesc(name, pageable);
    }

    @Override
    public BookMeta get(Long bookMetaId) {
        return bookMetaRepository
                .findById(bookMetaId)
                .orElseThrow(() -> new RuntimeException(""));
    }

    @Override
    public BookMeta save(BookMeta bookMeta) {
        return bookMetaRepository.save(bookMeta);
    }

    @Override
    public BookMeta update(BookMeta bookMeta) {
        return bookMetaRepository.save(bookMeta);
    }

    @Override
    public void delete(Long bookMetaId) {
        bookMetaRepository.deleteById(bookMetaId);
    }
}
