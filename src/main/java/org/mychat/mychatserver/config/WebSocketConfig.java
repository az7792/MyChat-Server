package org.mychat.mychatserver.config;

import org.mychat.mychatserver.component.WebSocketServer;
import org.mychat.mychatserver.service.GroupConnectService;
import org.mychat.mychatserver.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
    @Autowired
    public void setGroupConnectService(GroupConnectService groupConnectService){
        WebSocketServer.groupConnectService = groupConnectService;
    }

    @Autowired
    public void setMessageService(MessageService messageService){
        WebSocketServer.messageService = messageService;
    }
}
