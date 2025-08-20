package com.steph18.demo.serviceimpl;

import com.steph18.demo.entities.Post;
import com.steph18.demo.repository.PostRepository;
import com.steph18.demo.service.PostService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends GenericServiceImpl<Post, Long>
        implements PostService {

    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Post, Long> getRepository() {
        return this.repository;
    }

    @Override
    public Long getId(Post p) {
        return p.getId();
    }
}
