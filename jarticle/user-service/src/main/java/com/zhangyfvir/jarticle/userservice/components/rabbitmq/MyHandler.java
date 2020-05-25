package com.zhangyfvir.jarticle.userservice.components.rabbitmq;

import com.rabbitmq.client.Channel;

import java.io.IOException;

public interface MyHandler {
    void handle(Channel channel) throws IOException;
}
