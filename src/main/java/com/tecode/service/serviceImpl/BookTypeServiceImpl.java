package com.tecode.service.serviceImpl;

import com.tecode.dao.BookTypeMapper;
import com.tecode.model.BookType;
import com.tecode.model.BookTypeExample;
import com.tecode.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeMapper bookTypeMapper;
    private BookTypeExample bookTypeExample;

    @Override//查询所有类型
    public List<BookType> findAll() {
        bookTypeExample = new BookTypeExample();
        return bookTypeMapper.selectByExample(bookTypeExample);
    }

    @Override
    public BookType findById(Integer id) {
        return bookTypeMapper.selectByPrimaryKey(id);
    }
}
