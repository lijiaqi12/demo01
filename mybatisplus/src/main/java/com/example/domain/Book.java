package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "tbl_book")
public class Book {
    private Integer id;
//    private Long id;
    private String type;
    private String name;
    private String description;

    public Integer getId() {
        return id;
    }
//    public Long getId() {
//    return id;
//}

    public void setId(Integer id) {
        this.id = id;
    }
//    public void setId(Long id) {
//    this.id = id;
//}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
