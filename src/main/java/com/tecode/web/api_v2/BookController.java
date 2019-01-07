package com.tecode.web.api_v2;

import com.alibaba.fastjson.JSON;
import com.tecode.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2018/12/14.
 */
@Controller
public class BookController {
    @Autowired
    private BooksService booksService;
    //书籍查询
    @RequestMapping(value = "/booksearch", method = RequestMethod.GET)
    @ResponseBody
    public Object search(String book,int pageNums) {
//        System.out.println(book+"\n"+pageNums);
        return JSON.toJSON(booksService.booksAll(book,pageNums));
    }

    @RequestMapping(value = "/read",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String read(String url) throws IOException {
        System.out.println(url);
        URL myurl = new URL(url);
        URLConnection uc = myurl.openConnection();//创建连接
        InputStream is = uc.getInputStream();//创建输入流
        byte[] a = new byte[is.available()];
        is.read(a);
        String stringBuffer = new String(a);
        System.out.println(stringBuffer);
        if (is != null) {
            is.close();
        }
        return JSON.toJSONString(stringBuffer);
    }

}
