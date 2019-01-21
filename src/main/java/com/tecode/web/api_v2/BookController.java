package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.model.SetionTable;
import com.tecode.service.BookShelfService;
import com.tecode.service.BooksService;
import com.tecode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/12/14.
 */
@Controller
public class BookController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private BookShelfService bookShelfService;
    @Autowired
    private UserService userService;
    //书籍查询
    @RequestMapping(value = "/booksearch", method = RequestMethod.GET)
    @ResponseBody
    public Object search(String book,int pageNums) {
//        System.out.println(book+"\n"+pageNums);
        return JSON.toJSON(booksService.booksAll(book,pageNums));
    }

    //获取文本内容
    @RequestMapping(value = "/read")
    @ResponseBody
    public Object read(int bid, String djz,boolean flag) throws IOException {
       // System.out.println(bid+djz+flag);
        String url="";
        String djz1 = "";
        int id=0 ;
        if(flag){
            for (SetionTable s:booksService.selectzjnr(bid,djz)){
                id= s.getId();
            }
            id+=1;
            for (SetionTable d:booksService.selectzjid(id)){
                url=d.getContent();
                djz1=d.getChapter();
            }
        }else {
            for (SetionTable s:booksService.selectzjnr(bid,djz)){
                id= s.getId();
            }
            id-=1;
            for (SetionTable d:booksService.selectzjid(id)){
                url=d.getContent();
                djz1=d.getChapter();
            }
        }
        URL myurl = new URL(url);
        URLConnection uc = myurl.openConnection();//创建连接
        InputStream is = uc.getInputStream();//创建输入流
        byte[] a = new byte[is.available()];
        is.read(a);
        String stringBuffer = new String(a);
      //  System.out.println(stringBuffer);
        if (is != null) {
            is.close();
        }
        Map<String,String> map = new HashMap();
        map.put("stringBuffer",stringBuffer);
        map.put("djz1",djz1);
        return JSON.toJSON(map);
    }
    //获取章节目录
    @RequestMapping(value = "/zj", method = RequestMethod.GET)
    @ResponseBody
    public Object zj(int bid,int pages){
        List list = new ArrayList();
        int page = 0;
        page = booksService.zjnum(bid);
        if(booksService.selectZJ(bid,pages)!=null){
            for(SetionTable a:booksService.selectZJ(bid,pages)){
                list.add(a.getChapter());
            }
            list.add(page);
            return JSON.toJSON(list);
        }else {
            return null;
        }
    }
    //返回章节内容
    @RequestMapping(value = "/zjnr", method = RequestMethod.GET)
    @ResponseBody
    public String zjnr(int bid, String djz) throws IOException {
      //  System.out.println(bid+djz);
        String url="";
        for (SetionTable s:booksService.selectzjnr(bid,djz)){
            url = s.getContent();
        }
        URL myurl = new URL(url);
        URLConnection uc = myurl.openConnection();//创建连接
        InputStream is = uc.getInputStream();//创建输入流
        byte[] a = new byte[is.available()];
        is.read(a);
        String stringBuffer = new String(a);
       // System.out.println(stringBuffer);
        if (is != null) {
            is.close();
        }

        return stringBuffer;
    }
    //热门搜索
    @RequestMapping(value = "/Popular",method = RequestMethod.GET)
    @ResponseBody
    public Object Popular(int pages){

        return booksService.selectByMonthNumber(pages);
    }
}
