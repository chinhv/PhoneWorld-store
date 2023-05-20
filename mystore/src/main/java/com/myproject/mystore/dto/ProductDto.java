package com.myproject.mystore.dto;



import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private String description;
    private int price;
    private String cpu;
    private String ram;
    private String battery;
    private MultipartFile imageFile;
    private String imgPath;
    private int CategoryId;
    private int ManufacturerId;

}
