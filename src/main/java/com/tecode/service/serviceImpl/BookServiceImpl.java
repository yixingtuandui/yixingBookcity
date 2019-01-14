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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
    private DataUtil dataUtil;

    @Override//根据作者查询书籍
    public List<Books> selectByAuthor(String author) {
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuditingLike(author);
        return booksMapper.selectByExample(booksExample);
    }

    @Override
    public List<Books> selectByBookname(Integer pages, String name) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo("审核通过").andBookNameLike("%" + name + "%");
        return booksMapper.selectByExample(booksExample);
    }

    @Override//根据id查询书籍
    public Books selectByBookId(Integer id) {
        return booksMapper.selectByPrimaryKey(id);
    }

    @Override//首页书籍展示
    public List<Books> homePageData(String type, int pages, int pageSize) {
        BooksExample booksExample = new BooksExample();
        PageHelper.startPage(pages, pageSize);
        switch (type) {
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
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
        }
    }

    @Override//查询某一类型的某一作者的书籍
    public List<Books> bookdetails(String author, String type) {
        BooksExample booksExample = new BooksExample();
        if (!type.equals("")) {
            booksExample.or().andAuthorEqualTo(author).andAuditingLike("%" + type + "%");
            booksExample.or().andAuthorEqualTo(author).andBookNameLike("%" + type + "%");
        } else {
            booksExample.createCriteria().andAuthorEqualTo(author);
        }
        return booksMapper.selectByExample(booksExample);
    }

    @Override //查询某一作者的所有书籍
    public List<Books> booksAll(String author, int pageNums) {
        BooksExample booksExample = new BooksExample();
        PageHelper.startPage(pageNums, 5);
        booksExample.or().andAuthorLike("%" + author + "%").andAuditingEqualTo("审核通过");
        booksExample.or().andBookNameLike("%" + author + "%").andAuditingEqualTo("审核通过");
        long count = booksMapper.countByExample(booksExample);
        if (author == "") {
            return null;
        } else {
            if (count % 10 == 0) {
                if (count / 10 < pageNums) {
                    return null;
                } else {
                    return booksMapper.selectByExample(booksExample);
                }
            } else {
                if (count / 10 + 1 < pageNums) {
                    return null;
                } else {
                    return booksMapper.selectByExample(booksExample);
                }
            }
        }
    }

    @Override//根据类型查询
    public List<Books> selectByType(Integer type, int pageNum) {
        PageHelper.startPage(pageNum, 10);
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andTypeEqualTo(type);
        booksExample.createCriteria().andAuditingEqualTo("审核通过");
        long county = booksMapper.countByExample(booksExample);
        if (county % 10 == 0) {
            if (county / 10 < pageNum) {
                return null;
            } else {
                System.out.println(booksMapper.selectByExample(booksExample));
                return booksMapper.selectByExample(booksExample);
            }
        } else {
            System.out.println(3);
            if (county / 10 + 1 < pageNum) {
                return null;
            } else {
                return booksMapper.selectByExample(booksExample);
            }
        }
    }

    @Override//根据点击量查询
    public List<Books> selectByNumber(int pageNum) {
        BooksExample booksExamplel = new BooksExample();
        booksExamplel.setOrderByClause("number desc");
        long count = booksMapper.countByExample(booksExamplel);
        if (count % 10 == 0) {
            if (count / 10 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                BooksExample booksExample = new BooksExample();
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            }

        } else {
            if (count / 10 + 1 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                BooksExample booksExample = new BooksExample();
                booksExample.setOrderByClause("number desc");
                return booksMapper.selectByExample(booksExample);
            }
        }
    }

    @Override//根据购买量查询
    public List<Books> selectByAmount(int pageNum) {
        BooksExample booksExamplels = new BooksExample();
        booksExamplels.setOrderByClause("amount desc");
        long counts = booksMapper.countByExample(booksExamplels);
        if (counts % 10 == 0) {
            if (counts / 10 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                BooksExample booksExample = new BooksExample();
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            }

        } else {
            if (counts / 10 + 1 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                BooksExample booksExample = new BooksExample();
                booksExample.setOrderByClause("amount desc");
                return booksMapper.selectByExample(booksExample);
            }
        }

    }

    @Override
    public List<Books> selectByMonthNumber(int pageNum) {
        ListTimeExample timeExample = new ListTimeExample();
        timeExample.setOrderByClause("monthcount desc");
        long count = timeMapper.countByExample(timeExample);
        if (count / 10 == 0) {
            System.out.println(count / 10 < pageNum);
            if (count / 10 + 1 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("number")) {
                        booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplels).get(0));
                    }
                }
                return booksLists;
            }
        } else {
            System.out.println(count / 10 < pageNum);
            if (count / 10 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("number")) {
                        booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplels).get(0));
                    }
                }
                return booksLists;
            }
        }
    }

    @Override
    public List<Books> selectByMonthAmount(int pageNum) {
        ListTimeExample timeExample = new ListTimeExample();
        timeExample.setOrderByClause("monthcount desc");
        long count = timeMapper.countByExample(timeExample);
        if (count / 10 == 0) {
            System.out.println(count / 10 < pageNum);
            if (count / 10 + 1 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("amount")) {
                        booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplels).get(0));
                    }
                }
                return booksLists;
            }
        } else {
            System.out.println(count / 10 < pageNum);
            if (count / 10 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("monthcount desc");
                List<Books> booksLists = new ArrayList<Books>();
                BooksExample booksExamplels;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("amount")) {
                        booksExamplels = new BooksExample();
                        booksExamplels.createCriteria().andIdEqualTo(lt.getBid());
                        booksLists.add(booksMapper.selectByExample(booksExamplels).get(0));
                    }
                }
                return booksLists;
            }
        }
    }

    @Override
    public List<Books> selectByWeekNumber(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        ListTimeExample timeExample = new ListTimeExample();
        timeExample.setOrderByClause("weekcount desc");
        long count = timeMapper.countByExample(timeExample);
        if (count / 10 == 0) {
            if (count / 10 + 1 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<Books>();
                BooksExample booksExamplel;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("number")) {
                        booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplel).get(0);
                        booksList.add(books);
                    }
                }
                return booksList;
            }
        } else {
            if (count / 10 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<Books>();
                BooksExample booksExamplel;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("number")) {
                        booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplel).get(0);
                        booksList.add(books);
                    }
                }
                return booksList;
            }
        }
    }

    @Override
    public List<Books> selectByWeekAmount(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        ListTimeExample timeExample = new ListTimeExample();
        timeExample.setOrderByClause("weekcount desc");
        long count = timeMapper.countByExample(timeExample);
        if (count / 10 == 0) {
            if (count / 10 + 1 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<Books>();
                BooksExample booksExamplel;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("amount")) {
                        booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplel).get(0);
                        booksList.add(books);
                    }
                }
                return booksList;
            }
        } else {
            if (count / 10 < pageNum) {
                return null;
            } else {
                PageHelper.startPage(pageNum, 10);
                timeExample = new ListTimeExample();
                timeExample.setOrderByClause("weekcount desc");
                List<Books> booksList = new ArrayList<Books>();
                BooksExample booksExamplel;
                List<ListTime> timeList = timeMapper.selectByExample(timeExample);
                for (ListTime lt : timeList) {
                    if (lt.getStats().equals("amount")) {
                        booksExamplel = new BooksExample();
                        booksExamplel.createCriteria().andIdEqualTo(lt.getBid());
                        Books books = booksMapper.selectByExample(booksExamplel).get(0);
                        booksList.add(books);
                    }
                }
                return booksList;
            }
        }
    }

    @Override
    public void addCX(int id) {
        ListTimeExample timeExample = new ListTimeExample();
        timeExample.createCriteria().andStatsEqualTo("amount").andBidEqualTo(id);
        List<ListTime> listTimes = timeMapper.selectByExample(timeExample);
        Books books = booksMapper.selectByPrimaryKey(id);
        ListTime listTime;
        if (dataUtil.timeDay().after(dataUtil.getCurrentMonthStartTime()) && dataUtil.timeDay().before(dataUtil.getCurrentMonthEndTime())) {
            if (dataUtil.timeDay().after(dataUtil.getCurrentWeekDayStartTime()) && dataUtil.timeDay().before(dataUtil.getCurrentWeekDayEndTime())) {
                if (listTimes.size() != 0) {
                    listTime = listTimes.get(0);
                    System.out.println(listTime.getStats());
                    System.out.println(123);
                    listTime.setWeekcount(listTime.getWeekcount() + 1);
                    listTime.setMonthcount(listTime.getMonthcount() + 1);
                    timeMapper.updateByPrimaryKey(listTime);
                } else {
                    System.out.println(456);
                    books.setNumber(books.getAmount() + 1);
                    booksMapper.updateByPrimaryKey(books);
                    listTime = new ListTime();
                    listTime.setBid(id);
                    listTime.setWeekendtime(dataUtil.getCurrentWeekDayEndTime());
                    listTime.setWeekstarttime(dataUtil.getCurrentWeekDayStartTime());
                    listTime.setMonthstarttime(dataUtil.getCurrentMonthStartTime());
                    listTime.setMonthendtime(dataUtil.getCurrentMonthEndTime());
                    listTime.setStats("amount");
                    listTime.setMonthcount(1);
                    listTime.setWeekcount(1);
                    timeMapper.insert(listTime);
                }
            }
        }
    }

    @Override//查询通过的所有书籍
    public List<Books> bookAll(int pages, String auditing) {
        PageHelper.startPage(pages, 10);
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo(auditing);
        return booksMapper.selectByExample(booksExample);
    }

    @Override//通过书籍的计数
    public Long countBooks(String auditing) {
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andAuditingEqualTo(auditing);
        return booksMapper.countByExample(booksExample);
    }

    @Override//下架书籍
    public void deletebooks(Books books) {
        booksMapper.updateByPrimaryKey(books);
    }

    @Override//根据书名查询的计数
    public Long countBooksname(String bookname) {
        BooksExample booksExample = new BooksExample();
        booksExample.createCriteria().andBookNameLike("%" + bookname + "%").andAuditingEqualTo("审核通过");
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
        String imgaddr = addr.substring(0, addr.lastIndexOf("/")) + "/介绍";
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("img");
        String[] imgName = addr.split("/");
        try {
            String proPath = new File(ResourceUtils.getURL("classpath:static").getPath()).getAbsolutePath();
            File dir = new File(proPath + imgaddr);
            if (!dir.exists()) {
                dir.mkdir();
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
}
