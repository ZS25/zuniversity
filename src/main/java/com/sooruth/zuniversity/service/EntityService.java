package com.sooruth.zuniversity.service;

import java.util.List;

public interface EntityService<T> {
    Long create(T t);
    T read(Long id);
    List<T> readAll();
    T update(T t);
    void delete(Long id);
}
