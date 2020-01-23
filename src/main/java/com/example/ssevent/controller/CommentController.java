package com.example.ssevent.controller;

import com.example.ssevent.EventConsumer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class CommentController {


    private EventConsumer eventConsumer;

    public CommentController(EventConsumer eventConsumer){
        this.eventConsumer = eventConsumer;
    }


    @GetMapping(path = "/comment/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> feed() {
         return eventConsumer.get();
    }
}
