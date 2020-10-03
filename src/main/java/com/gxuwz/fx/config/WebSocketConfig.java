package com.gxuwz.fx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 配置WebSocket需要自动注入的Servlet
 */
@Configuration
@ComponentScan("com.gxuwz.fx")
public class WebSocketConfig {

    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}

