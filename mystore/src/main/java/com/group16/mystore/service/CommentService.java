package com.group16.mystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group16.mystore.entities.Comment;
import com.group16.mystore.entities.User;
import com.group16.mystore.repository.CommentRepository;

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
