package com.myproject.mystore.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mystore.entities.Category;
import com.myproject.mystore.service.CategoryService;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/")
    public List<Category> findAllCategory(){
        return service.getAllCategory();
    }

    

    @PostMapping("/add")
    public Category addCategory(@RequestBody Category category){
        return service.saveCategory(category);
    }

    @PostMapping("/addAll")
    public List<Category> addAllCategory(@RequestBody List<Category> categories){
        return service.saveAllCategory(categories);
    }

    @PutMapping("/update")
    public Category updateCategory(@RequestBody Category category){
        return service.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable int id){
        service.deleteCategory(id);
    }
}
