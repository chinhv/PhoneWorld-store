package com.group16.mystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.group16.mystore.entities.Manufacturer;
import com.group16.mystore.repository.ManufacturerRepository;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepository manufacturerRepository;

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturerById(int id){
        return manufacturerRepository.findById(id).get();
    }

    public Manufacturer saveManufacturer(Manufacturer manufacturer){
        return manufacturerRepository.save(manufacturer);
    }

    public String deleteManufacturer(int id){
        manufacturerRepository.deleteById(id);
        return "delete done !";
    }

    public Page<Manufacturer> getAllManufacturersPage(int page, int pageSize){
        return manufacturerRepository.findAll(PageRequest.of(page, pageSize));
    }
}
