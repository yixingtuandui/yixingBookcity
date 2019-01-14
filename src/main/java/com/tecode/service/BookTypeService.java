package com.tecode.service;

import com.tecode.model.BookType;

import java.util.List;

public interface BookTypeService<T> {

    List<BookType> findAll();

    BookType findById(Integer id);
}
