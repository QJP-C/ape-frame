package com.qjp.user.websocket;

import com.qjp.websocket.config.WebSocketServerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@ServerEndpoint(value = "/chicken/socket",configurator = WebSocketServerConfig.class)//声明该websocket的地址 指定配置类
public class ChickenWebsocket {
    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);//AtomicInteger:线程安全的计数器

    /**
     * 存放所有在线的客户端
     */
    private static Map<String,ChickenWebsocket> clients = new ConcurrentHashMap<>();//ConcurrentHashMap:线程安全的map
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 标识当前会话的key
     */
    private String erp = "";

    /**
     * 连接建立成功调用的方法
     * @param session
     * @param config
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws Exception {
        Map<String, Object> userProperties = config.getUserProperties();
        String erp = (String) userProperties.get("erp");
        this.erp = erp;
        this.session = session;
        //如果已经存在连接，就先关闭
        if (clients.containsKey(this.erp)){
            clients.get(this.erp).session.close();//关闭连接
            clients.remove(this.erp);//移除连接
            onlineCount.decrementAndGet();//在线人数减一
        }
        clients.put(this.erp,this);//将当前连接放入map
        onlineCount.incrementAndGet();//在线人数加一
        log.info("有新连接加入：{}！当前在线人数为：{}" ,erp,onlineCount.get());
        sendMessage("连接成功",session);
    }

    /**
     * 连接关闭调用的方法
     * @throws Exception
     */
    @OnClose
    public void onClose() throws Exception {
        if (clients.containsKey(erp)){
            clients.get(erp).session.close();//关闭连接
            clients.remove(this.erp);//移除连接
            onlineCount.decrementAndGet();//在线人数减一
        }
        log.info("有一处连接关闭：{},当前在线人数为：{}" ,this.erp,onlineCount.get());
    }

    @OnError
    public void onError(Session session, Throwable error) throws Exception {
        log.error("websocket：{},发生错误,错误原因：{}" ,erp,error.getMessage(),error);
        session.close();
    }

    /**
     * 服务端收客户端消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws Exception {
        log.info("服务端收到客户端：{} 发送的消息：{}",this.erp,message);
        //心跳机制 （一段时间没有消息的话会自动断连）  和前端商议好 一般是前端发ping  服务端回pong 以此保持连接
        if (message.equals("ping")) {
            this.sendMessage("pong", session);
        }
    }


    /**
     * 服务端给客户端发送消息
     * @param message
     * @param session
     * @throws Exception
     */
    public void sendMessage(String message,Session session) throws Exception {
        log.info("服务端给客户端：{} 发送消息：{}",this.erp,message);
        session.getBasicRemote().sendText(message);
    }

    /**
     * 给所有的客户端发消息
     * @param message
     * @throws Exception
     */
    public void sendMessage(String message) throws Exception {
        for (ChickenWebsocket item : clients.values()) {
            item.session.getBasicRemote().sendText(message);
        }
    }
}
