package com.zhangyfvir.jarticle.userservice.components.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitSenderUtil {
    private RabbitConfig rabbitConfig;

    public RabbitSenderUtil(RabbitConfig rabbitConfig) {
        this.rabbitConfig = rabbitConfig;
    }

    private ConnectionFactory createConnectionFactory() throws IOException, TimeoutException {
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
    final String ROUTING_KEY = QUEUE_NAME;
    final String EXCHANGE_NAME = "";

    public void publish(String message) {
        if (message == null) return;

        doPublish(new MyHandler() {
            public void handle(Channel channel) throws IOException {
                /*
                 * a durable, non-autodelete exchange of "direct" type
                 * a durable, non-exclusive, non-autodelete queue with a well-known name
                 * */

                byte[] messageBodyBytes = message.getBytes();
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                channel.basicPublish("", QUEUE_NAME, null, messageBodyBytes);
                System.out.println(" [x] Sent '" + message + "'");
            }
        });
    }

    private void doPublish(MyHandler handler) {

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
