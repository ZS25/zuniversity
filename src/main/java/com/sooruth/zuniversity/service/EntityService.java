package com.sooruth.zuniversity.service;

import org.springframework.data.domain.Page;

public interface EntityService<T> {
    Long create(T t);
    T read(Long id);
    Page<T> readAll(int page, int size);
    T update(T t);
    void delete(Long id);
}
