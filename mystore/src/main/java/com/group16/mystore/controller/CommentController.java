package com.group16.mystore.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group16.mystore.entities.Comment;
import com.group16.mystore.entities.User;
import com.group16.mystore.service.CommentService;
import com.group16.mystore.service.UserService;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(auth.getName());
    }

    @GetMapping("/all")
    public List<Comment> getComments() {
        return commentService.getAllComments();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestParam(required = false, value = "comment") String comment) {
        User currentUser = getCurrentUser();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null){
            return new ResponseEntity<>("return form log-in", HttpStatus.BAD_REQUEST);
        }else{
            Comment newComment = new Comment();
            newComment.setUser(currentUser);
            newComment.setComment(comment);
            newComment = commentService.saveComment(newComment);
        }
        return new ResponseEntity<>("Saved !",HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public String deleteComment(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByEmail(auth.getName());
        Comment comment = commentService.getCommentByUser(currentUser);
        return commentService.deleteComment(comment.getId());
    }

    
}
