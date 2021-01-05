package com.zetyun.websocket;


import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
@ServerEndpoint("/websocket")
public class WebSocketServer {
    /*websocket 客户端会话 通过Session 向客户端发送数据*/
    private Session session;
    /*线程安全set 存放每个客户端处理消息的对象*/
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet();
    /*websocket 连接建立成功后进行调用*/
    @OnOpen
    public void onOpen(Session session) {
        session.setMaxIdleTimeout(3600000);
        this.session = session;
        webSocketSet.add(this);
        System.out.println("websocket 有新的链接"+webSocketSet.size());
    }
    /*WebSocket 连接关闭调用的方法*/
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }
    /*收到客户端消息后调用的方法*/
    @OnMessage
    public void onMessage(String message) throws IOException, IOException {
        for (WebSocketServer socket : webSocketSet) {
            socket.session.getBasicRemote().sendText("自己嘎给自己嘎发的消息："+message);
        }
    }
    /* WebSocket 发生错误时进行调用*/
    @OnError
    public void onError(Session session,Throwable error) throws IOException {
        System.out.println("WebSocket 发生错误");
        if (session.isOpen()) {
            session.close();
        }
        webSocketSet.remove(session);
        error.printStackTrace();
    }
    public void sendMessage(String message) throws IOException {
        for (WebSocketServer socket : webSocketSet) {
            if(socket.session.isOpen()){
                socket.session.getBasicRemote().sendText(message);
            }
        }
    }
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }
}