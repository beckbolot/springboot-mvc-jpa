package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.Comment;

import java.util.List;

public interface CommentRepository {

    void addComment(Comment comment);

    List<Comment> getAllComments();


}
