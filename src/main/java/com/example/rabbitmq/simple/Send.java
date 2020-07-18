package com.example.rabbitmq.simple;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_name = "simple_test";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_name,false,false,false,null);
        String msg ="hello simple mq";
        channel.basicPublish("",QUEUE_name,null,msg.getBytes());
        channel.close();
        connection.close();
        System.out.println("simple send done");
    }
}
