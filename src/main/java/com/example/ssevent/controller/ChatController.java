package com.example.ssevent.controller;

import com.example.ssevent.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("topic.name")
    private static String TOPIC_NAME;

    @PostMapping("/chat")
    public void sendMessage(@RequestBody Comment comment){
        kafkaTemplate.send(TOPIC_NAME,comment.getMessage());
    }

}
