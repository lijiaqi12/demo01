package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.BookDao;
import com.example.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void testSave(){
        Book book = new Book();
        book.setId(11);
        book.setName("三只小猪");
        book.setType("童话故事");
        book.setDescription("适合给孩子睡觉前阅读的童话故事");
        bookDao.insert(book);
    }



    @Test
    void testGetAll() {
        //方式1：按条件查询
//        QueryWrapper qt = new QueryWrapper();
//        qt.lt("id",5);
//        List<Book> books = bookDao.selectList(qt);
//        System.out.println(books);
        //方式2:Lambda表达式条件查询
        QueryWrapper<Book> qw = new QueryWrapper<Book>();
        qw.lambda().lt(Book::getId,5);
        List<Book> books = bookDao.selectList(qw);
        System.out.println(books);
    }

}
