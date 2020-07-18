package com.example.rabbitmq.exchange;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String EXCHANGE_NAME = "test_exhcange";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String msg ="hello ps";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());

        channel.close();
        connection.close();
        System.out.println("ps done");
    }
}
