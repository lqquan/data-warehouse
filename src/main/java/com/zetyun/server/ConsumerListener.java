package com.zetyun.server;

import com.zetyun.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 消费者监听topic=testTopic的消息
 *
 * @author Lynch
 */
@Component
public class ConsumerListener {
    @Autowired
    private WebSocketServer socket;
    @KafkaListener(topics = "pv-out")
    public void onMessage(String message) throws IOException, InterruptedException {
        //insertIntoDb(buffer);//这里为插入数据库代码
        socket.sendMessage(message);
         Thread.sleep(10);
         }

}