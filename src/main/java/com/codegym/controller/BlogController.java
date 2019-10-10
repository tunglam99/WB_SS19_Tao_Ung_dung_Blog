package com.codegym.controller;


import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/blogs")
    public ModelAndView listBlog(@PageableDefault(value = 2)Pageable pageable){
        Page<Blog> blogs =blogService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

    @GetMapping("/create-blog")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blogForm",new  BlogForm());
        return modelAndView;
    }

    @PostMapping("/save-blog")
    public ModelAndView saveBlog(@ModelAttribute("blog") BlogForm blogForm, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            System.out.println("Result error"+bindingResult.getAllErrors());
        }

        blogService.save(blogForm);

        ModelAndView modelAndView = new ModelAndView("/blog/create");
        modelAndView.addObject("blogForm",new BlogForm());
        modelAndView.addObject("message","New BLog created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-blog/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if ( blog!= null){
            ModelAndView modelAndView = new ModelAndView("/blog/edit");
            modelAndView.addObject("blog",blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-blog")
    public String updateBlog(@ModelAttribute("blog") BlogForm blogForm, BindingResult result){
        if (result.hasFieldErrors()){
            System.out.println("Result Error Occured" + result.getAllErrors());
        }
        blogService.save(blogForm);

        return "redirect:/blogs";
    }

    @GetMapping("/delete-blog/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("/blog/delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error:404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-blog")
    public String deleteEmployee(@ModelAttribute("blog") BlogForm blogForm){
        blogService.remove(blogForm.getId());
        return "redirect:/blogs";
    }

}
