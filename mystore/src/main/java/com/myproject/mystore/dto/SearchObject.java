package com.myproject.mystore.dto;

import lombok.Data;

@Data
public class SearchObject {
    
    private String categoryName;
    private String[] keywords;
    private String price;
    private String sort;
    private String manufacturerName;

}
