package com.example.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtils {

    /**
     * 获取mq连接
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //difine c f
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        //amqp port
        factory.setPort(5672);
        factory.setVirtualHost("vhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory.newConnection();
    }
}
