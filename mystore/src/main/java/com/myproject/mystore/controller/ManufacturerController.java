package com.myproject.mystore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.mystore.entities.Manufacturer;
import com.myproject.mystore.service.ManufacturerService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {
    @Autowired
    private ManufacturerService service;

    @GetMapping("/all")
    public List<Manufacturer> findAllManufacturers() {
        return service.getAllManufacturers();
    }

    @GetMapping("/")
    public Page<Manufacturer> findAllManufacturers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int pageSize
    ) {
        return service.getAllManufacturersPage(page, pageSize);
    }

    @PostMapping("/save")
    public Manufacturer addManufacturer(@RequestBody Manufacturer manufacturer) {
        return service.saveManufacturer(manufacturer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable int id){
        return service.deleteManufacturer(id);
    }
}
