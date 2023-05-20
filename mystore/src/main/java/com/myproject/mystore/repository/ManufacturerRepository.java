package com.myproject.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
    
}
