package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {
    private Long id;
    private String title;
    private String content;
    private MultipartFile image;


    public BlogForm() {
    }

    public BlogForm(String title, String content, MultipartFile image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public BlogForm(Long id, String title, String content, MultipartFile image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
