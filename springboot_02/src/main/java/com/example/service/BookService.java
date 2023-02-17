package com.example.service;

import com.example.domain.Book;


import java.util.List;

public interface BookService {
    /**
     * 添加书籍
     * @param book
     * @return
     */
    public boolean save(Book book);


    /**
     * 编辑书籍信息
     * @param book
     * @return
     */
    public boolean update(Book book);


    /**
     * 根据id删除
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Book getById(Integer id);

    /**
     * 查询全部书籍
     * @return
     */
    public List<Book> getAll();
}
