package com.group16.mystore.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String userName;
    private String address;
    private String phone;
    private String status;
    private String note;
}
