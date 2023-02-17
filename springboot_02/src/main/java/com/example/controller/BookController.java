package com.example.controller;

import com.example.domain.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id){
        System.out.println("id===>"+id);
        return bookService.getById(id);
    }
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }
}
