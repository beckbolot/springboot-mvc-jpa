package com.beck.springbootmvcjpa.service;

import com.beck.springbootmvcjpa.entity.Comment;
import com.beck.springbootmvcjpa.respository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void addComment(Comment comment, String name){
        comment.setName(name);
        comment.setDate(new Date());
        commentRepository.addComment(comment);

    }

    public List<Comment> getAllComments(){
        return commentRepository.getAllComments();
    }


}
