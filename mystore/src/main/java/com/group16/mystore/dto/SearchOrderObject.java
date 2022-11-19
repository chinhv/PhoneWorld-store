package com.group16.mystore.dto;

import com.group16.mystore.entities.User;

import lombok.Data;

@Data
public class SearchOrderObject {
    private String userName;
    private String status;
    private User user;
}
