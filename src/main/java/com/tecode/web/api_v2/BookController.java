package com.tecode.web.api_v2;

import com.tecode.model.Books;
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
import java.util.List;

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
    public List<Books> search(String book) {
        return booksService.booksAll(book);
    }

    @RequestMapping(value = "/read")
    @ResponseBody
    public void read() throws IOException {
        URL myurl = new URL("http://106.13.7.6:8080/bookcity/com/jun_shi/123/23.txt");
        URLConnection uc = myurl.openConnection();//创建连接
        InputStream is = uc.getInputStream();//创建输入流
//    InputStreamReader inputStreamReader = new InputStreamReader(is,"UTF-8");
//    StringBuffer stringBuffer = new StringBuffer();
//    while (inputStreamReader.ready()){
//        stringBuffer.append((char)inputStreamReader.read());
//    }
//    System.out.println(stringBuffer.toString());
//    is.close();
//    inputStreamReader.close();
        byte[] a = new byte[is.available()];
        is.read(a);
        String stringBuffer = new String(a);
        System.out.println(stringBuffer);
        if (is != null) {
            is.close();
        }
    }

    //首页查询
    @RequestMapping(value = "/home_page", method = RequestMethod.GET)
    @ResponseBody
    public List<Books> homePageData(String type, int pages) {
        return booksService.homePageData(type, pages);
    }

    //作者书籍查询
    @RequestMapping(value = "/bookdetails", method = RequestMethod.GET)
    @ResponseBody
    public List bookdetails(String author, String type){
        return booksService.bookdetails(author, type);
    }

}
