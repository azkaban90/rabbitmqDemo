package com.example.rabbitmq.workqueue;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private  static final String WORK_QUEUE= "WORK_QUEUE";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(WORK_QUEUE,false,false,false,null);
        for (int i = 0;i<50;i++){
            String msg ="msg"+i;
            channel.basicPublish("",WORK_QUEUE,null,msg.getBytes());
            Thread.sleep(i*2);
        }

        channel.close();
        connection.close();
    }
}
