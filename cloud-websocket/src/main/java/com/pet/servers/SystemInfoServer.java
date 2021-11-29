package com.pet.servers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/web-socket/system-info/{room}")
public class SystemInfoServer {

    private static final ConcurrentHashMap<Integer, Session> types = new ConcurrentHashMap<>();

    @OnOpen
    public void open(@PathParam(value = "room") Integer type, Session session) {
        log.info("客户端 【{}】 建立连接， type 【{}】", session.getId(), type);
        types.put(type, session);
    }

    @OnClose
    public void close(@PathParam(value = "room") Integer type, Session session) {
        log.info("客户端 【{}】 连接关闭， type 【{}】", session.getId(), type);
        types.remove(type);
    }

    @OnError
    public void error(Session session, Throwable throwable) {
        log.error("websocket error client id 【{}】", session.getId());
        throwable.printStackTrace();
    }

    public void sendMsg(Integer type, String message) {
        Session session = types.get(type);
        if (Objects.nonNull(session)) {
            session.getAsyncRemote().sendText(message);
        }
    }

}
