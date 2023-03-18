package com.miridih.library.book.application;

import com.miridih.library.book.domain.ExternalBookMeta;
import com.miridih.library.book.exception.BookMetaNotFoundException;
import com.miridih.library.book.infrastructure.BookMetaRepository;
import com.miridih.library.book.domain.BookMeta;
import com.miridih.library.book.infrastructure.ExternalBookMetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMetaServiceImpl implements BookMetaService {

    private final BookMetaRepository bookMetaRepository;
    private final ExternalBookMetaRepository externalBookMetaRepository;

    @Override
    public List<ExternalBookMeta> getExternalBookMetaList(ExternalBookMetaSearchCondition searchCondition) {
        return externalBookMetaRepository.search(searchCondition);

    }

    @Override
    public Page<BookMeta> getAll(Pageable pageable) {
        return bookMetaRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Page<BookMeta> getByName(String name, Pageable pageable) {
        return bookMetaRepository.findAllByTitleOrderByIdDesc(name, pageable);
    }

    @Override
    public BookMeta getById(Long bookMetaId) {
        return bookMetaRepository
                .findById(bookMetaId)
                .orElseThrow(() -> new BookMetaNotFoundException("도서 메타를 찾을 수 없습니다.", bookMetaId));
    }

    @Override
    public BookMeta save(BookMeta bookMeta) {
        return bookMetaRepository.save(bookMeta);
    }

    @Override
    public BookMeta update(BookMeta bookMeta) {
        getById(bookMeta.getId());

        return bookMetaRepository.save(bookMeta);
    }

    @Override
    public void delete(Long bookMetaId) {
        getById(bookMetaId);

        bookMetaRepository.deleteById(bookMetaId);
    }
}
