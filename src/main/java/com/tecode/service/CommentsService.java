package com.tecode.service;

import com.tecode.model.Comment;

import java.util.List;

public interface CommentsService<T> {
    void add(Comment comment);
    List<T> findByBookId(Integer id);
    List<T> findByBookIdLess(Integer id);
}
