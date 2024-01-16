package com.beck.springbootmvcjpa.respository;

import com.beck.springbootmvcjpa.entity.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentRepositoryImpl implements CommentRepository{

    private final EntityManager entityManager;


    @Override
    public void addComment(Comment comment) {
        entityManager.persist(comment);
        log.info("Comment was added: " + comment);

    }

    @Override
    public List<Comment> getAllComments() {
        return entityManager.createQuery("from Comment",Comment.class).getResultList();
    }
}
