package com.myproject.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.Category;





public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
      

}
