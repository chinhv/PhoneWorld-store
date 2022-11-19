package com.group16.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group16.mystore.entities.Comment;
import com.group16.mystore.entities.User;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    Comment findByUser(User user);
}
