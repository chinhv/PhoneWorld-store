package com.myproject.mystore.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String userName;
    private String address;
    private String phone;
    private String note;
}
