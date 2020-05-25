package com.zhangyfvir.jarticle.userservice.components.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConsumerUtil {
    private RabbitConfig rabbitConfig;

    public RabbitConsumerUtil(RabbitConfig rabbitConfig) {
        this.rabbitConfig = rabbitConfig;
    }

    ConnectionFactory createConnectionFactory() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        // "guest"/"guest" by default, limited to localhost connections
        factory.setUsername(rabbitConfig.userName);
        factory.setPassword(rabbitConfig.password);
        factory.setVirtualHost(rabbitConfig.virtualHost);
        factory.setHost(rabbitConfig.hostName);
        factory.setPort(rabbitConfig.portNumber);
        return factory;
    }

    final String QUEUE_NAME = "queue1";

    public void receive() {

        doReceive(new MyHandler() {
            @Override
            public void handle(Channel channel) throws IOException {
                System.out.println("in handle");
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });
            }
        });
        try {
            int i = 10;
            while ((i--) < 0) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doReceive(MyHandler handler) {

        Connection conn = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = createConnectionFactory();
            conn = factory.newConnection();
            channel = conn.createChannel();
            handler.handle(channel);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (channel != null)
                    channel.close();
                if (conn != null)
                    conn.close();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        }
    }
}
