package com.zetyun.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter  serverEndpointExporter() {
        System.out.println("开启WebSocket支持");
        return new ServerEndpointExporter();
    }

}
