package com.example.rabbitmq.exchange;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PsRecive1 {

    private static final String QUEUE_NAME="test_queue_ps_mail";
    private static final String EXCHANGE_NAME = "test_exhcange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        channel.basicQos(1);
        Consumer defaultConsumer =new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.printf("接收到消息："+msg);

                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    System.out.println("[ps1]done");
                    // 手动回执
//                    channel.basicAck(,false);
                }
            }
        };

        channel.basicConsume(QUEUE_NAME,true,defaultConsumer);


    }
}
