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
    @Override
    public List<Books> selectByAuthor(String author) {//根据作者查询书籍
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingLike(author);
        return booksMapper.selectByExample(booksExample);
    }

    @Override
    public List<Books> selectByBookname(Integer pages, String name) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andBookNameLike(name);
        return booksMapper.selectByExample(booksExample);
    }


    @Override//根据id查询书籍
    public Books selectByBookId(Integer id) {
        return booksMapper.selectByPrimaryKey(id);
    }

    @Override//首页书籍展示
    public List<Books> homePageData(String type, int pages) {
        BooksExample booksExample=new BooksExample();
        PageHelper.startPage(pages, 10);
        switch (type){
            case "推荐":
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            case "排行":
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            default:
                booksExample.createCriteria().andAuditingEqualTo("审核通过").andKindsEqualTo(type);
                return booksMapper.selectByExample(booksExample);
        }
    }
    //查询某一类型的某一作者的书籍
    @Override
    public List<Books> bookdetails(String author, String type) {
        BooksExample booksExample=new BooksExample();
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
    public List<Books> booksAll(String author,int pageNums) {
        BooksExample booksExample = new BooksExample();
        PageHelper.startPage(pageNums, 5);
        booksExample.or().andAuthorLike("%"+author+"%");
        booksExample.or().andBookNameLike("%"+author+"%");
        long count = booksMapper.countByExample(booksExample);
        if(count/5==0){
            if(count/5<pageNums){
                return null;
            }else {
                return booksMapper.selectByExample(booksExample);
            }
        }else {
            if(count/5+1<pageNums){
                return null;
            }else {
                return booksMapper.selectByExample(booksExample);
            }
        }
    }

    @Override
    public List<Books> selectByType(Integer type,int pageNum) {
        PageHelper.startPage(pageNum,10);
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andTypeEqualTo(type);
        booksExample.createCriteria().andAuditingEqualTo("审核通过");
        long county=booksMapper.countByExample(booksExample);
        if(county/10==0){
            if(county/10<pageNum){
                return null;
            }else {
                System.out.println(booksMapper.selectByExample(booksExample));
                return booksMapper.selectByExample(booksExample);
            }
        }else {
            System.out.println(3);
            if(county/10+1<pageNum){
                return null;
            }else {
                return booksMapper.selectByExample(booksExample);
            }
        }
    }

    @Override
    public List<Books> selectByNumber(int pageNum) {
        BooksExample booksExamplel=new BooksExample();
        booksExamplel.setOrderByClause("number desc");
//        List<Books> list=booksMapper.selectByExample(booksExamplel);
        long count=booksMapper.countByExample(booksExamplel);
        if(count/10==0){
            if(count/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                BooksExample booksExample=new BooksExample();
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            }

        }else {
            if(count/10+1<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                BooksExample booksExample=new BooksExample();
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            }
        }
    }

    @Override
    public List<Books> selectByAmount(int pageNum) {
        BooksExample booksExamplels=new BooksExample();
        booksExamplels.setOrderByClause("amount desc");
        long counts=booksMapper.countByExample(booksExamplels);
        if(counts/10==0){
            if(counts/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                BooksExample booksExample=new BooksExample();
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            }

        }else {
            if(counts/10+1<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                BooksExample booksExample=new BooksExample();
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            }
        }

    }

    @Override
    public List<Books> bookAll(int pages, String auditing) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo(auditing);
        return booksMapper.selectByExample(booksExample);
    }

    @Override
    public Long countBooks(String auditing) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo(auditing);
        return booksMapper.countByExample(booksExample);
    }

    @Override
    public void deletebooks(Books books) {
        booksMapper.updateByPrimaryKey(books);
    }

    @Override
    public Long countBooksname(String bookname) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andBookNameLike("%"+bookname+"%");
        return booksMapper.countByExample(booksExample);
    }
    @Override
    public List<Books> selectByMonthNumber() {
        return null;
    }
    //    畅销 月榜
    @Override
    public List<Books> selectByMonthAmount() {
        return null;
    }

    @Override
    public List<Books> selectByWeekNumber() {
        return null;
    }
    //    畅销 周榜
    @Override
    public List<Books> selectByWeekAmount() {
        return null;
    }
}
