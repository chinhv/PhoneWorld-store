package com.myproject.mystore.dto;

import com.myproject.mystore.entities.User;

import lombok.Data;

@Data
public class SearchOrderObject {
    private String userName;
    private String status;
    private User user;
}
