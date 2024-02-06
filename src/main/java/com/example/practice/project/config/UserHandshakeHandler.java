package com.example.practice.project.config;

import com.sun.security.auth.UserPrincipal;
import java.security.Principal;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Slf4j
public class UserHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String randomId = request.getURI().getQuery().split("[=]")[1];
        log.info("User with ID '{}' opened the page", randomId);

        return new UserPrincipal(randomId);
    }
}
