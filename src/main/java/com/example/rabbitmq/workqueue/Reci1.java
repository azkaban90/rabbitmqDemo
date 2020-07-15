package com.example.rabbitmq.workqueue;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Reci1 {
    private  static final String WORK_QUEUE= "WORK_QUEUE";

    public static void main(String[] args) throws IOException, TimeoutException {
        try (Connection connection = ConnectionUtils.getConnection()) {
           Channel channel=  connection.createChannel();
           channel.queueDeclare(WORK_QUEUE,false,false,false,null);

            Consumer defaultConsumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String msg = new String(body,"utf-8");
                    System.out.printf("接收到消息："+msg);

                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }finally {
                        System.out.println("【1】done");
                    }
                }
            };

            boolean autoAck = true;
            channel.basicConsume(WORK_QUEUE,autoAck,defaultConsumer);

        }
    }
}
