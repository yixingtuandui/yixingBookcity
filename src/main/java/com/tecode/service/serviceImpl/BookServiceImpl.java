package com.tecode.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.tecode.dao.BooksMapper;
import com.tecode.model.Books;
import com.tecode.model.BooksExample;
import com.tecode.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BooksService {
    @Autowired
    private BooksMapper booksMapper;
    private BooksExample booksExample=new BooksExample();
    @Override
    public List<Books> selectByAuthor(String author) {//根据作者查询书籍
        booksExample.createCriteria().andAuditingLike(author);
        return booksMapper.selectByExample(booksExample);
    }

    @Override
    public List<Books> selectByBookname(String name) {//根据书名查询书籍
        booksExample.createCriteria().andBookNameLike(name);
        return booksMapper.selectByExample(booksExample);
    }

    @Override//根据id查询书籍
    public Books selectByBookId(Integer id) {
        return booksMapper.selectByPrimaryKey(id);
    }

    @Override//首页书籍展示
    public List<Books> homePageData(String type, int pages) {
        PageHelper.startPage(pages, 10);
        BooksExample.Criteria criteria=null;
        criteria.andAddrEqualTo("审核通过");
        switch (type){
            case "推荐":
                booksExample.createCriteria().andAddrEqualTo("审核通过");
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            case "排行":
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            default:
                criteria.andKindsEqualTo(type);
                return booksMapper.selectByExample(booksExample);
        }
    }
    //查询某一类型的某一作者的书籍
    @Override
    public List<Books> bookdetails(String author, String type) {
        booksExample.createCriteria().andAuthorEqualTo(author);
        if (!type.equals("")) {
            booksExample.or().andAuthorEqualTo(author).andAuditingLike(type);
            booksExample.or().andAuthorEqualTo(author).andBookNameLike(type);
            booksExample.or().andAuthorEqualTo(author).andStatusLike(type);
        }
        return booksMapper.selectByExample(booksExample);
    }
    //查询某一作者的所有书籍
    @Override
    public List<Books> booksAll(String author) {
        booksExample.or().andAuthorLike(author);
        booksExample.or().andBookNameLike(author);
        return booksMapper.selectByExample(booksExample);
    }

    @Override
    public List<Books> selectByType(Integer type) {
        booksExample.createCriteria().andTypeEqualTo(type);
        return booksMapper.selectByExample(booksExample);
    }

}
