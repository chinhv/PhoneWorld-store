package com.group16.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group16.mystore.entities.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{
    
}
