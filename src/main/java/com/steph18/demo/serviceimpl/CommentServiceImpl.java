package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Comment;
import com.steph18.demo.repository.CommentRepository;
import com.steph18.demo.service.CommentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends GenericServiceImpl<Comment, Long>
        implements CommentService {

    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Comment, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Comment p) {
        return p.getId();
    }
}
