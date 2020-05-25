package com.zhangyfvir.jarticle.userservice;

import com.zhangyfvir.jarticle.userservice.components.rabbitmq.RabbitConfig;
import com.zhangyfvir.jarticle.userservice.components.rabbitmq.RabbitSenderUtil;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;


public class RabbitMQTest {
    @Test
    public void testPublish() {
        String userName = "admin";
        String password = "admin";
        String virtualHost = "/";
        String hostName = "localhost";
        int portNumber = 5672;

        RabbitConfig rabbitConfig = new RabbitConfig();
        rabbitConfig.setUserName(userName);
        rabbitConfig.setPassword(password);
        rabbitConfig.setVirtualHost(virtualHost);
        rabbitConfig.setHostName(hostName);
        rabbitConfig.setPortNumber(portNumber);
        RabbitSenderUtil mqUtil = new RabbitSenderUtil(rabbitConfig);
        mqUtil.publish("hello");

//        String password = "this_1$_seCret";
//        System.out.println("plain: " + password); // NOSONAR
//        String encrypted = PasswordUtils.hashPassword(password);
//        System.out.println("encrypted: " + encrypted); // NOSONAR
//        assertThat(encrypted, IsNull.notNullValue());
//        assertThat(ArrayUtils.getLength(StringUtils.split(encrypted, "::")), IsEqual.equalTo(3));
//        assertTrue(PasswordUtils.validatePassword(password, encrypted));
//        password = "fake_password";
//        assertFalse(PasswordUtils.validatePassword(password, encrypted));
//        encrypted = PasswordUtils.hashPassword(password, "0");
//        assertTrue(PasswordUtils.validatePassword(password, encrypted));
//        assertThrows(IllegalArgumentException.class, () -> PasswordUtils.hashPassword("shouldThrow", "999"));
//        assertThrows(IllegalStateException.class, () -> PasswordUtils.validatePassword("shouldThrow", "0::asdfasdf"));
//        assertThrows(IllegalStateException.class,
//                () -> PasswordUtils.validatePassword("shouldThrow", "999::asdfasdf::sdfasdfasdf"));
//
//        System.out.println(String.format("%s, 加密后是是：%s","admin",PasswordUtils.hashPassword("admin")));
//        System.out.println(String.format("%s, 加密后是是：%s","zhangyf",PasswordUtils.hashPassword("zhangyf")));
    }
}