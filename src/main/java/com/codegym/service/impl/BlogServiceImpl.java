package com.codegym.service.impl;

import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import com.codegym.repository.BlogRepository;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class BlogServiceImpl implements BlogService {
    @Autowired
    Environment env;
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog findById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public void save(BlogForm blogForm) {
        Blog blog = getBlog(blogForm);
        blogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepository.delete(id);
    }

    @Override
    public Blog getBlog(BlogForm blogForm) {

        //lay ten fil
        MultipartFile multipartFile = blogForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();

        try {
            //multipartFile.transferTo(imageFile);
            FileCopyUtils.copy(blogForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // neu ko sua lay lai image cu
        if (fileName.equals("") && blogForm.getId() != null){
            Blog blog = findById(blogForm.getId());
            fileName = blog.getImage();
        }

        //tao doi tuong luu vao DB
        if (blogForm.getId() == null){
            return new Blog(blogForm.getTitle(), blogForm.getContent(),fileName);
        } else {
            return new Blog(blogForm.getId(), blogForm.getTitle(), blogForm.getContent(),fileName);
        }
    }
}
