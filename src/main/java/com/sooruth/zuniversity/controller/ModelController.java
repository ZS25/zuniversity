package com.sooruth.zuniversity.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.hateoas.EntityModel;

public interface ModelController<T> {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    Page<T> readAll(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    T readById(@PathVariable Long id);

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    HttpEntity<EntityModel> create(@Valid @RequestBody T model);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody T model);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
