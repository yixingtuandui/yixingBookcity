package com.tecode.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.tecode.dao.BooksMapper;
import com.tecode.dao.ListTimeMapper;
import com.tecode.dao.SetionTableMapper;
import com.tecode.model.*;
import com.tecode.service.BooksService;
import com.tecode.service.SetionService;
import com.tecode.util.DataUtil;
import com.tecode.util.PingYinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BookServiceImpl implements BooksService {
    @Autowired
    private BooksMapper booksMapper;
    @Autowired
    private ListTimeMapper timeMapper;
    @Autowired
    private SetionTableMapper setionTableMapper;
    @Autowired
    private SetionService setionService;
    @Autowired
    private DataUtil dataUtil;
    @Override
    public List<Books> selectByAuthor(String author) {//根据作者查询书籍
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingLike("%"+author+"%");
        return booksMapper.selectByExample(booksExample);
    }
    //根据书名进行模糊查询
    @Override
    public List<Books> selectByBookname(Integer pages, String name) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo("审核通过").andBookNameLike("%"+name+"%");
        return booksMapper.selectByExample(booksExample);
    }
    //对书籍查询进行排序
    @Override
    public List<Books> selectByBooknameOrder(Integer pages, String name, String comds) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo("审核通过").andBookNameLike("%"+name+"%");
        booksExample.setOrderByClause(comds+" desc");
        return booksMapper.selectByExample(booksExample);
    }


    @Override//根据id查询书籍
    public Books selectByBookId(Integer id) {
        int weekcount=1;
        int monthcount=1;
        ListTimeExample timeExample=new ListTimeExample();
        timeExample.createCriteria().andBidEqualTo(id).andStatsEqualTo("number");
        List<ListTime> listTimes=timeMapper.selectByExample(timeExample);
        Books books=booksMapper.selectByPrimaryKey(id);
        if (dataUtil.timeDay().after(dataUtil.getCurrentMonthStartTime())&&dataUtil.timeDay().before(dataUtil.getCurrentMonthEndTime())) {
            if (dataUtil.timeDay().after(dataUtil.getCurrentWeekDayStartTime()) && dataUtil.timeDay().before(dataUtil.getCurrentWeekDayEndTime())) {
                books.setNumber(books.getNumber() + monthcount);
                booksMapper.updateByPrimaryKey(books);
                System.out.println(listTimes.size());
                if (listTimes.size() != 0) {
                    for (ListTime listTime : listTimes) {
                        if (listTime.getBid() == id && listTime.getMonthstarttime().equals(dataUtil.getCurrentMonthStartTime()) && listTime.getMonthendtime().equals(dataUtil.getCurrentMonthEndTime())) {
                            if(listTime.getMonthcount()!=null){
                                listTime.setMonthcount(listTime.getMonthcount() + 1);
                                timeMapper.updateByPrimaryKey(listTime);
                            }
                            if (listTime.getBid() == id && listTime.getWeekstarttime().equals(dataUtil.getCurrentWeekDayStartTime()) && listTime.getWeekendtime().equals(dataUtil.getCurrentWeekDayEndTime())) {
                                listTime.setWeekcount(listTime.getWeekcount() + 1);
                                timeMapper.updateByPrimaryKey(listTime);
                            }else {
                                listTime.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                                listTime.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                                listTime.setWeekcount(weekcount);
                                timeMapper.updateByPrimaryKey(listTime);
                            }
                        }else {
                            listTime.setMonthstarttime(dataUtil.getCurrentMonthStartTime());
                            listTime.setMonthendtime(dataUtil.getCurrentMonthEndTime());
                            listTime.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                            listTime.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                            listTime.setMonthcount(monthcount);
                            timeMapper.updateByPrimaryKey(listTime);
                        }
                    }
                }else {
                    ListTime listTime1 = new ListTime();
                    listTime1.setBid(id);
                    listTime1.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                    listTime1.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                    listTime1.setMonthstarttime(dataUtil.getCurrentMonthStartTime());
                    listTime1.setMonthendtime(dataUtil.getCurrentMonthEndTime());
                    listTime1.setStats("number");
                    listTime1.setMonthcount(monthcount);
                    listTime1.setWeekcount(weekcount);
                    timeMapper.insert(listTime1);
                }
            }
        }
        return booksMapper.selectByPrimaryKey(id);
    }

    @Override//首页书籍展示
    public List<Books> homePageData(String type, int pages,int pageSize) {
        BooksExample booksExample=new BooksExample();
        PageHelper.startPage(pages, pageSize);
        switch (type){
            case "推荐":
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            case "排行":
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            default://男、女类型书籍
                booksExample.createCriteria().andAuditingEqualTo("审核通过").andKindsEqualTo(type);
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
        }
    }
    //查询某一类型的某一作者的书籍
    @Override
    public List<Books> bookdetails(String author, String type) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuthorEqualTo(author);
        if (!type.equals("")) {
            booksExample.or().andAuthorEqualTo(author).andAuditingLike("%"+type+"%");
            booksExample.or().andAuthorEqualTo(author).andBookNameLike("%"+type+"%");
        }
        return booksMapper.selectByExample(booksExample);
    }
    //查询某一作者的所有书籍
    @Override
    public List<Books> booksAll(String author,int pageNums) {
        BooksExample booksExample = new BooksExample();
        PageHelper.startPage(pageNums, 5);
        booksExample.or().andAuthorLike("%"+author+"%").andAuditingEqualTo("审核通过");
        booksExample.or().andBookNameLike("%"+author+"%").andAuditingEqualTo("审核通过");
        long count = booksMapper.countByExample(booksExample);
        if(author==""){
            return null;
        }else {
            if(count%10==0){
                if(count/10<pageNums){
                    return null;
                }else {
                    return booksMapper.selectByExample(booksExample);
                }
            }else {
                if(count/10+1<pageNums){
                    return null;
                }else {
                    return booksMapper.selectByExample(booksExample);
                }
            }
        }
    }

    @Override
    public List<Books> selectByType(Integer type, int pageNum) {
        BooksExample booksExamples=new BooksExample();
        PageHelper.startPage(pageNum,10);
        booksExamples.createCriteria().andAuditingEqualTo("审核通过").andTypeEqualTo(type);
//        booksExamples.createCriteria().andTypeEqualTo(type);
        long county=booksMapper.countByExample(booksExamples);
        if(county%10==0){
            if(county/10<pageNum){
                return null;
            }else {
                return booksMapper.selectByExample(booksExamples);
            }
        }else {
            System.out.println(3);
            if(county/10+1<pageNum){
                return null;
            }else {
                return booksMapper.selectByExample(booksExamples);
            }
        }
    }

    @Override
    public List<Books> selectByNumber(int pageNum) {
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo("审核通过");
        booksExample.setOrderByClause("number desc");
//        List<Books> list=booksMapper.selectByExample(booksExamplel);
        long count=booksMapper.countByExample(booksExample);
        if(count%10==0){
            if(count/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                booksExample=new BooksExample();
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            }

        }else {
            if(count/10+1<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                booksExample=new BooksExample();
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            }
        }
    }

    @Override
    public List<Books> selectByAmount(int pageNum) {
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo("审核通过");
        booksExample.setOrderByClause("amount desc");
        long counts=booksMapper.countByExample(booksExample);
        if(counts%10==0){
            if(counts/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                booksExample=new BooksExample();
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            }

        }else {
            if(counts/10+1<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                booksExample=new BooksExample();
                booksExample.createCriteria().andAuditingEqualTo("审核通过");
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            }
        }

    }

    @Override
    public List<Books> selectByMonthNumber(int pageNum) {
        ListTimeExample timeExample=new ListTimeExample();
        timeExample.createCriteria().andStatsEqualTo("number").andMonthstarttimeEqualTo(dataUtil.getCurrentMonthStartTime()).andMonthendtimeEqualTo(dataUtil.getCurrentMonthEndTime());
        timeExample.setOrderByClause("monthcount desc");
        long count=timeMapper.countByExample(timeExample);
        if(count%10==0){
            System.out.println(count/10<pageNum);
            if(count/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                timeExample=new ListTimeExample();
                timeExample.createCriteria().andStatsEqualTo("number").andMonthstarttimeEqualTo(dataUtil.getCurrentMonthStartTime()).andMonthendtimeEqualTo(dataUtil.getCurrentMonthEndTime());
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                        booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplels).get(0);
                        books.setBancout(lt.getMonthcount());
                        booksMapper.updateByPrimaryKey(books);
                        BooksExample booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplel).get(0));
                }
                return booksLists;
            }
        }else {
            if(count/10+1<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                timeExample=new ListTimeExample();
                timeExample.createCriteria().andStatsEqualTo("number").andMonthstarttimeEqualTo(dataUtil.getCurrentMonthStartTime()).andMonthendtimeEqualTo(dataUtil.getCurrentMonthEndTime());
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                        booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplels).get(0);
                        books.setBancout(lt.getMonthcount());
                        booksMapper.updateByPrimaryKey(books);
                        BooksExample booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplel).get(0));
                }
                return booksLists;
            }
        }

    }
    //    畅销 月榜
    @Override
    public List<Books> selectByMonthAmount(int pageNum) {
        ListTimeExample timeExample=new ListTimeExample();
        timeExample.createCriteria().andStatsEqualTo("amount").andMonthstarttimeEqualTo(dataUtil.getCurrentMonthStartTime()).andMonthendtimeEqualTo(dataUtil.getCurrentMonthEndTime());
        timeExample.setOrderByClause("monthcount desc");
        long count=timeMapper.countByExample(timeExample);
        if(count%10==0){
            if(count/10<pageNum){
                return null;
            }else {
                timeExample=new ListTimeExample();
                PageHelper.startPage(pageNum,10);
                timeExample.createCriteria().andStatsEqualTo("amount").andMonthstarttimeEqualTo(dataUtil.getCurrentMonthStartTime()).andMonthendtimeEqualTo(dataUtil.getCurrentMonthEndTime());
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                        booksExamplels =new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplels).get(0)) ;
                }
                return booksLists;
            }
        }else {
            if(count/10+1<pageNum){
                return null;
            }else {
                timeExample=new ListTimeExample();
                PageHelper.startPage(pageNum,10);
                timeExample.createCriteria().andStatsEqualTo("amount").andMonthstarttimeEqualTo(dataUtil.getCurrentMonthStartTime()).andMonthendtimeEqualTo(dataUtil.getCurrentMonthEndTime());
                timeExample.setOrderByClause("monthcount desc");
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                for (ListTime lt:timeList){
                        booksExamplels =new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplels).get(0));
                }
                return booksLists;
            }
        }
    }

    @Override
    public List<Books> selectByWeekNumber(int pageNum) {
        ListTimeExample timeExample=new ListTimeExample();
        timeExample.createCriteria().andStatsEqualTo("number").andWeekstarttimeEqualTo(dataUtil.getCurrentWeekDayStartTime()).andWeekendtimeEqualTo(dataUtil.getCurrentWeekDayEndTime());
        timeExample.setOrderByClause("weekcount desc");
        long count=timeMapper.countByExample(timeExample);
        System.out.println(count);
        if(count%10==0){
            if(count/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                timeExample=new ListTimeExample();
                timeExample.createCriteria().andStatsEqualTo("number").andWeekstarttimeEqualTo(dataUtil.getCurrentWeekDayStartTime()).andWeekendtimeEqualTo(dataUtil.getCurrentWeekDayEndTime());
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<>();
                BooksExample booksExamplel;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                    System.out.println(lt.getWeekstarttime());
                        booksExamplel =new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books= booksMapper.selectByExample(booksExamplel).get(0);
                        books.setBancout(lt.getWeekcount());
                        booksMapper.updateByPrimaryKey(books);
                        BooksExample booksExamplels=new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books book= booksMapper.selectByExample(booksExamplels).get(0);
                        booksList.add(book);
                }
                return booksList;
            }
        }else {
            if (count/10+1<pageNum){
                return  null;
            }else {
                PageHelper.startPage(pageNum,10);
                timeExample=new ListTimeExample();
                timeExample.createCriteria().andStatsEqualTo("number").andWeekstarttimeEqualTo(dataUtil.getCurrentWeekDayStartTime()).andWeekendtimeEqualTo(dataUtil.getCurrentWeekDayEndTime());
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<>();
                BooksExample booksExamplel;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                    System.out.println(lt.getWeekstarttime());
                        booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplel).get(0);
                        books.setBancout(lt.getWeekcount());
                        booksMapper.updateByPrimaryKey(books);
                        BooksExample booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        Books book = booksMapper.selectByExample(booksExamplels).get(0);
                        booksList.add(book);
                }
                return booksList;
            }
        }

    }
    //    畅销 周榜
    @Override
    public List<Books> selectByWeekAmount(int pageNum) {
        ListTimeExample timeExample=new ListTimeExample();
        timeExample.createCriteria().andStatsEqualTo("amount").andWeekstarttimeEqualTo(dataUtil.getCurrentWeekDayStartTime()).andWeekendtimeEqualTo(dataUtil.getCurrentWeekDayEndTime());
        timeExample.setOrderByClause("weekcount desc");
        long count=timeMapper.countByExample(timeExample);
        if(count%10==0){
            if(count/10<pageNum){
                return null;
            }else {
                PageHelper.startPage(pageNum,10);
                timeExample=new ListTimeExample();
                timeExample.createCriteria().andStatsEqualTo("amount").andWeekstarttimeEqualTo(dataUtil.getCurrentWeekDayStartTime()).andWeekendtimeEqualTo(dataUtil.getCurrentWeekDayEndTime());
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<Books>();
                BooksExample booksExamplel;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                        booksExamplel =new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books= booksMapper.selectByExample(booksExamplel).get(0);
                        booksList.add(books) ;
                }
                return booksList;
            }
        }else {
            if (count/10+1<pageNum){
                return  null;
            }else {
                PageHelper.startPage(pageNum,10);
                timeExample=new ListTimeExample();
                timeExample.createCriteria().andStatsEqualTo("amount").andWeekstarttimeEqualTo(dataUtil.getCurrentWeekDayStartTime()).andWeekendtimeEqualTo(dataUtil.getCurrentWeekDayEndTime());
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<Books>();
                BooksExample booksExamplel;
                List<ListTime> timeList=timeMapper.selectByExample(timeExample);
                for (ListTime lt:timeList){
                        booksExamplel =new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books= booksMapper.selectByExample(booksExamplel).get(0);
                        booksList.add(books) ;
                }
                return booksList;
            }
        }
    }

    @Override
    public void addCX(int id) {
        ListTimeExample timeExample=new ListTimeExample();
        timeExample.createCriteria().andStatsEqualTo("amount").andBidEqualTo(id);
        List<ListTime> listTimes=timeMapper.selectByExample(timeExample);
        Books books=booksMapper.selectByPrimaryKey(id);
        if (dataUtil.timeDay().after(dataUtil.getCurrentMonthStartTime())&&dataUtil.timeDay().before(dataUtil.getCurrentMonthEndTime())) {
            if (dataUtil.timeDay().after(dataUtil.getCurrentWeekDayStartTime()) && dataUtil.timeDay().before(dataUtil.getCurrentWeekDayEndTime())) {
                books.setNumber(books.getAmount()+1);
                booksMapper.updateByPrimaryKey(books);
                if(listTimes.size()!=0) {
                    for (ListTime listTime : listTimes) {
                        if (listTime.getBid() == id && listTime.getMonthstarttime().equals(dataUtil.getCurrentMonthStartTime()) && listTime.getMonthendtime().equals(dataUtil.getCurrentMonthEndTime())) {
                            if (listTime.getMonthcount()!=null){
                                listTime.setMonthcount(listTime.getMonthcount() + 1);
                                timeMapper.updateByPrimaryKey(listTime);
                            }
                            if (listTime.getBid() == id && listTime.getWeekstarttime().equals(dataUtil.getCurrentWeekDayStartTime()) && listTime.getWeekendtime().equals(dataUtil.getCurrentWeekDayEndTime())) {
                                listTime.setWeekcount(listTime.getWeekcount() + 1);
                                timeMapper.updateByPrimaryKey(listTime);
                            }else {
                                listTime.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                                listTime.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                                listTime.setWeekcount(1);
                                timeMapper.updateByPrimaryKey(listTime);
                            }
                        }else {
                            listTime.setMonthstarttime(dataUtil.getCurrentMonthStartTime());
                            listTime.setMonthendtime(dataUtil.getCurrentMonthEndTime());
                            listTime.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                            listTime.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                            listTime.setMonthcount(1);
                            timeMapper.updateByPrimaryKey(listTime);
                        }
                    }
                }else {
                    ListTime listTime1 = new ListTime();
                    listTime1.setBid(id);
                    listTime1.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                    listTime1.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                    listTime1.setMonthstarttime(dataUtil.getCurrentMonthStartTime());
                    listTime1.setMonthendtime(dataUtil.getCurrentMonthEndTime());
                    listTime1.setStats("amount");
                    listTime1.setMonthcount(1);
                    listTime1.setWeekcount(1);
                    timeMapper.insert(listTime1);
                }
            }
        }

    }

    @Override//查询通过的所有书籍
    public List<Books> bookAll(int pages, String auditing,String orders) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo(auditing);
        booksExample.setOrderByClause(orders);
        return booksMapper.selectByExample(booksExample);
    }

    @Override//通过书籍的计数
    public Long countBooks(String auditing) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo(auditing);
        return booksMapper.countByExample(booksExample);
    }

    @Override//下架书籍
    public void deletebooks(Books books) {
        booksMapper.updateByPrimaryKey(books);
    }

    @Override//根据书名查询的计数
    public Long countBooksname(String bookname) {
        BooksExample booksExample=new BooksExample();
        booksExample.createCriteria().andBookNameLike("%"+bookname+"%").andAuditingEqualTo("审核通过");
        return booksMapper.countByExample(booksExample);
    }

    @Override//成为作者
    public void authorUpdate(Books books) {
        booksMapper.updateByPrimaryKey(books);
    }
    @Override//作者书籍修改
    public boolean bookUpdate(int id, double price, String status) {
        Books books = selectByBookId(id);
        books.setPrice(price);
        books.setStatus(status);
        return booksMapper.updateByPrimaryKey(books) > 0;
    }

    @Override//书籍最新章节
    public int chapter(int bid) {
        SetionTableExample setionTableExample = new SetionTableExample();
        setionTableExample.createCriteria().andBidEqualTo(bid);
        return setionTableMapper.selectByExample(setionTableExample).size();
    }

    @Override//作者书籍章节更新
    public boolean chapterUpdate(String address, int bid, int chapter, String title, String content) {
        String chapterName = "第" + chapter + "章 " + title;
        Matcher matcher = Pattern.compile("/").matcher(address);
        int index = 0;
        while (matcher.find()) {
            index++;
            if (index == 3)
                break;
        }
        //章节路径
        String chapterAddr = address.substring(matcher.start()) + "/" + chapterName;
        OutputStreamWriter outputStreamWriter = null;
        try {
            //项目路径
            File path = new File(ResourceUtils.getURL("classpath:static").getPath());
            //项目路径字符串
            String proPath = path.getAbsolutePath();
            //获取文件地址
            File files = new File(proPath + chapterAddr);
            //判断父文件夹是否存在 （即最内层文件的所有前置文件夹）
            if (!files.getParentFile().exists()) {
                //创建文件夹
                files.getParentFile().mkdirs();
            }
            //根据文件路径创建输出流（自动创建文件）
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(proPath + chapterAddr), "UTF-8");
            //写入章节
            outputStreamWriter.write(content);
            //调方法
            if (setionService.addChapter(bid, chapterName, "http://www.tf6boy.vip" + chapterAddr)) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override//作者添加书籍
    public List<Books> addBook(String author, String bookname, int lxint, String lb, String jj, String title, String content) {
        List list = new ArrayList();
        if (!(authorBookYZ(author, bookname).size() > 0)) {
            //章节地址
            String chapteraddr = "";
            //书籍地址
            String bookaddr = "";
            //提取书名首字母
            String booknames = PingYinUtil.getFirstSpell(bookname);
            boolean flag = false;
            switch (lxint) {
                case 1://xuan_huan
                    chapteraddr = "/bookcity/com/xuan_huan/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/xuan_huan/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 2://qi_huan
                    chapteraddr = "/bookcity/com/qi_huan/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/qi_huan/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 3://yan_qing
                    chapteraddr = "/bookcity/com/yan_qing/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/yan_qing/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 4://li_shi
                    chapteraddr = "/bookcity/com/li_shi/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/li_shi/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 5://jun_shi
                    chapteraddr = "/bookcity/com/jun_shi/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/jun_shi/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 6://you_xi
                    chapteraddr = "/bookcity/com/you_xi/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/you_xi/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 7://ke_huan
                    chapteraddr = "/bookcity/com/ke_huan/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/ke_huan/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 8://xuanyi_lingyi
                    chapteraddr = "/bookcity/com/xuanyi_lingyi/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/xuanyi_lingyi/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 9://ti_yu
                    chapteraddr = "/bookcity/com/ti_yu/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/ti_yu/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 10://sheng_huo
                    chapteraddr = "/bookcity/com/sheng_huo/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/sheng_huo/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 11://erci_yuan
                    chapteraddr = "/bookcity/com/erci_yuan/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/erci_yuan/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
                case 12://wu_xia
                    chapteraddr = "/bookcity/com/wu_xia/" + booknames + "/directory/第1章 " + title;
                    bookaddr = "/bookcity/com/wu_xia/" + booknames + "/directory";
                    flag = uploadBook(chapteraddr, content);
                    break;
            }
            if (flag) {
                Books books = new Books();
                books.setAddr("http://www.tf6boy.vip" + bookaddr);
                books.setAmount(0);
                books.setAuditing("审核中");
                books.setAuthor(author);
                books.setBookImg("");
                books.setBookName(bookname);
                books.setBrief(jj);
                books.setKinds(lb);
                books.setNumber(0);
                books.setPrice(0D);
                books.setStatus("连载");
                books.setType(lxint);
                if (booksMapper.insert(books) > 0) {
                    int bid = authorBookYZ(author, bookname).get(0).getId();
                    //调方法
                    setionService.addChapter(bid, "第1章 " + title, "http://www.tf6boy.vip" + chapteraddr);
                    list.add(bid);
                    list.add(bookaddr);
                    return list;
                }
            }
            list.add(0);
            return list;
        }
        list.add(-1);
        return list;
    }

    @Override//新书添加图片
    public boolean uploadImg(HttpServletRequest req, HttpServletResponse res) {
        //书籍id
        int id = Integer.parseInt(req.getParameter("id"));
        String addr = req.getParameter("imgaddr");
        String imgaddr = addr.substring(0, addr.lastIndexOf("/")) + "/介绍/";
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("img");
        String[] imgName = addr.split("/");
        try {
            String proPath = new File(ResourceUtils.getURL("classpath:static").getPath()).getAbsolutePath();
            File dir = new File(proPath + imgaddr);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(proPath + imgaddr, imgName[4] + ".jpg");
            multipartFile.transferTo(file);
            //调方法
            if (uploadImgs(id, "http://www.tf6boy.vip" + imgaddr + imgName[4] + ".jpg")) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override//作者删除书籍
    public boolean bookRemove(int id, String addr) {
        if (booksMapper.deleteByPrimaryKey(id) > 0) {
            if (setionService.chapterRemove(id)) {
                Matcher matcher = Pattern.compile("/").matcher(addr);
                int index = 0;
                while (matcher.find()) {
                    index++;
                    if (index == 3)
                        break;
                }
                try {
                    //书籍地址
                    String proPath = new File(ResourceUtils.getURL("classpath:static")
                            .getPath()).getAbsolutePath() + addr.substring(matcher.start(), addr.lastIndexOf("/"));
                    delFolder(proPath);
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override//作者修改图片
    public String updateImg(HttpServletRequest req, HttpServletResponse res) {
        int bookid = Integer.parseInt(req.getParameter("id"));
        String bookaddr = req.getParameter("addr");
        String bookname = PingYinUtil.getFirstSpell(req.getParameter("bookname"));
        Matcher matcher = Pattern.compile("/").matcher(bookaddr);
        int index = 0;
        while (matcher.find()) {
            index++;
            if (index == 3)
                break;
        }
        String imgaddr = bookaddr.substring(matcher.start(), bookaddr.lastIndexOf("/")) + "/介绍";
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("img");
        try {
            String proPath = new File(ResourceUtils.getURL("classpath:static").getPath()).getAbsolutePath();
            File dir = new File(proPath + imgaddr);
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(proPath + imgaddr, bookname + ".jpg");
            multipartFile.transferTo(file);
            //调方法
            if (uploadImgs(bookid, "http://www.tf6boy.vip" + imgaddr +"/"+ bookname + ".jpg")) {
                return "http://www.tf6boy.vip" + imgaddr +"/"+ bookname + ".jpg";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return "";
    }
    //作者添加书籍判断
    public List<Books> authorBookYZ(String author, String bookname) {
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuthorEqualTo(author).andBookNameEqualTo(bookname);
        return booksMapper.selectByExample(booksExample);
    }

    //上传新书
    public boolean uploadBook(String chapteraddr, String content) {
        OutputStreamWriter outputStreamWriter = null;
        try {
            //项目路径
            File path = new File(ResourceUtils.getURL("classpath:static").getPath());
            //项目路径字符串
            String proPath = path.getAbsolutePath();
            //获取文件地址
            File files = new File(proPath + chapteraddr);
            if (!files.getParentFile().exists()) {
                files.getParentFile().mkdirs();
            }
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(proPath + chapteraddr), "UTF-8");
            outputStreamWriter.write(content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    //新书图片地址存到数据库
    public boolean uploadImgs(int id, String imgaddr) {
        Books books = booksMapper.selectByPrimaryKey(id);
        books.setBookImg(imgaddr);
        return booksMapper.updateByPrimaryKey(books) > 0;
    }
    //作者删除书籍文件夹
    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath);
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //作者删除书籍相关文件
    public boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);
                delFolder(path + "/" + tempList[i]);
            }
            flag = true;
        }
        return flag;
    }
    //    章节列表
    @Override
    public List<SetionTable> selectZJ(int id,int pages) {
        SetionTableExample setionTableExample = new SetionTableExample();

        setionTableExample.createCriteria().andBidEqualTo(id);
        long count = setionTableMapper.countByExample(setionTableExample);
        if(count%50==0){
            if(count/50<pages){
                return null;
            }else {
                PageHelper.startPage(pages, 50);
                return setionTableMapper.selectByExample(setionTableExample);
            }
        }else {
            if(count/50+1<pages){
                return null;
            }else {
                PageHelper.startPage(pages, 50);
                return setionTableMapper.selectByExample(setionTableExample);
            }

        }
    }
    //  返回章节内容
    @Override
    public List<SetionTable> selectzjnr(int bid, String djz) {
        SetionTableExample setionTableExample = new SetionTableExample();
        setionTableExample.createCriteria().andBidEqualTo(bid).andChapterEqualTo(djz);
        return setionTableMapper.selectByExample(setionTableExample);
    }
    //根据id查找章节
    @Override
    public List<SetionTable> selectzjid(int id) {
        SetionTableExample setionTableExample = new SetionTableExample();
        setionTableExample.createCriteria().andIdEqualTo(id);
        return setionTableMapper.selectByExample(setionTableExample);
    }
    //章节页数
    public int zjnum(int id){
        long pages = 0;
        SetionTableExample setionTableExample = new SetionTableExample();
        setionTableExample.createCriteria().andBidEqualTo(id);
        long count = setionTableMapper.countByExample(setionTableExample);
        if(count%50==0){
            pages = count/50;
        }else {
            pages = count/50+1;
        }
        return (int) pages;
    }
}
