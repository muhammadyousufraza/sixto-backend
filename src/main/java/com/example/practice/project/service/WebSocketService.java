package com.example.practice.project.service;

import com.example.practice.project.model.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * notifyUser.
     *
     * @param to      to
     * @param message message
     */
    public void notifyUser(final String to, final String message) {
        log.info("sending notification to: {}", to);
        ResponseMessage response = new ResponseMessage(message);
        messagingTemplate.convertAndSendToUser(to, "/topic/private-messages", response);
    }
}
