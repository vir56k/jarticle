package com.zhangyfvir.jarticle.userservice.components.rabbitmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rabbitmq.config")
@Data
public class RabbitConfig {
    String userName;
    String password;
    String virtualHost;
    String hostName;
    int portNumber;

    @Override
    public String toString() {
        return "RabbitConfig{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", virtualHost='" + virtualHost + '\'' +
                ", hostName='" + hostName + '\'' +
                ", portNumber=" + portNumber +
                '}';
    }
}
