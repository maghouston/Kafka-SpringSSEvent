package com.example.ssevent.repo;

import com.example.ssevent.model.Comment;
import com.example.ssevent.utils.CommentGenerator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Repository
public class ReactiveCommentRepository implements CommentRepository {
    @Override
    public Flux<Comment> findAll() {
        return Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .map(this::generateComment)
                .flatMapIterable(x->x);
    }

    private List<Comment> generateComment(Long aLong) {
        Comment comment = new Comment(
                CommentGenerator.randomAuthor(),
                CommentGenerator.randomMessage(),
                CommentGenerator.getCurrentTimeStamp()
        );
        return Arrays.asList(comment);
    }
}
