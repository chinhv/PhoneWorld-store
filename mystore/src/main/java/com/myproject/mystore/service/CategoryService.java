package com.myproject.mystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mystore.entities.Category;
import com.myproject.mystore.repository.CategoryRepository;

import java.util.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id).orElse(null);
    }

   

    

    public List<Category> saveAllCategory(List<Category> categories) {
        return categoryRepo.saveAll(categories);
    }

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public Category updateCategory(Category category){
        Category currentCategory = categoryRepo.findById(category.getId()).orElse(null);
        currentCategory.setCategoryName(category.getCategoryName());
        return categoryRepo.save(currentCategory);
    }

    public void deleteCategory(int id){
        categoryRepo.deleteById(id);
    }
}
