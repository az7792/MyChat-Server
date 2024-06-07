package org.mychat.mychatserver.component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.mychat.mychatserver.controller.GroupConnectController;
import org.mychat.mychatserver.mapper.GroupConnectMapper;
import org.mychat.mychatserver.service.GroupConnectService;
import org.mychat.mychatserver.service.impl.GroupConnectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{uid}")
@Component
public class WebSocketServer {

    public static GroupConnectService groupConnectService;

    public static final Map<Integer,Session> sessions = new ConcurrentHashMap<Integer,Session>();
    @OnOpen
    public void onOpen(Session session, @PathParam("uid")Integer uid) {
        System.out.printf("新连接:uid(%d),session(%s)%n", uid, session.getId());
        sessions.put(uid, session);
        System.out.println("服务器人数：" + Integer.toString(sessions.size()));
    }

    @OnClose
    public void onClose(Session session,@PathParam("uid")Integer uid) {
        System.out.printf("连接关闭:uid(%d),session(%s)%n", uid, session.getId());
        sessions.remove(uid);
        System.out.println("服务器人数：" + Integer.toString(sessions.size()));
    }

    @OnMessage
    public void onMessage(String message, Session session,@PathParam("uid")Integer uid){
        System.out.println("Message received: " + message);
        try {
            // 解析原始 JSON 字符串,获取目标用户的 ID 和 ID类型
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(message);
            Integer to = jsonNode.get("to").asInt();
            String receiverType = jsonNode.get("receiverType").asText();
            sendMessage(to, message,receiverType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Integer id, String message,String receiverType) {
        //获取待发送用户的ID列表
        List<Integer> UsersID = new ArrayList<>();
        if(Objects.equals(receiverType, "user")){
            UsersID.add(id);
        }else {
            UsersID = groupConnectService.getAllUidByGroupId(id);
        }

        for(Integer uid : UsersID) {
            //存数据库

            //存用户消息状态表
            Session session = WebSocketServer.sessions.get(uid);
            if (session != null && session.isOpen()) {
                try {
                    session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("User with uid " + uid + " not found or session is closed.");
            }
        }
    }
}
