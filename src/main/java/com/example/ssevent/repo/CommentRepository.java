package com.example.ssevent.repo;

import com.example.ssevent.model.Comment;
import reactor.core.publisher.Flux;

public interface CommentRepository {
    Flux<Comment> findAll();
}
