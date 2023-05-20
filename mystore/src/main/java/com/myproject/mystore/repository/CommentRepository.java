package com.myproject.mystore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproject.mystore.entities.Comment;
import com.myproject.mystore.entities.User;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
    Comment findByUser(User user);
}
