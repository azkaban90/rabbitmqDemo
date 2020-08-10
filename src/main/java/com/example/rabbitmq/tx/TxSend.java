package com.example.rabbitmq.tx;

import com.example.rabbitmq.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TxSend {

    private static final String QUEUE_NAME = "test_queue_tx";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "hello tx";


        try {
            channel.txSelect();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            int xxx = 1/0;
            channel.txCommit();
        } catch (IOException e) {
           channel.txRollback();
            System.out.println("txRollback");
            e.printStackTrace();
        }
        channel.close();
        connection.close();

    }
}
