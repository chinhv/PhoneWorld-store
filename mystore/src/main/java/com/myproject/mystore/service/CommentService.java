package com.myproject.mystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.mystore.entities.Comment;
import com.myproject.mystore.entities.User;
import com.myproject.mystore.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentByUser(User user) {
        return commentRepository.findByUser(user);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public String deleteComment(int id){
        commentRepository.deleteById(id);
        return "delete done!";
    }
}
