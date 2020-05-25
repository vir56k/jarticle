package com.zhangyfvir.jarticle.userservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangyfvir.jarticle.userservice.components.JwtUtils;
import com.zhangyfvir.jarticle.userservice.components.PasswordUtils;
import com.zhangyfvir.jarticle.userservice.components.rabbitmq.RabbitConfig;
import com.zhangyfvir.jarticle.userservice.components.rabbitmq.RabbitSenderUtil;
import com.zhangyfvir.jarticle.userservice.entity.User;
import com.zhangyfvir.jarticle.userservice.entity.UserInfo;
import com.zhangyfvir.jarticle.userservice.repo.UserRepo;
import com.zhangyfvir.jarticle.utils.LogUtil;
import io.micrometer.core.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String TAG = UserService.class.getSimpleName();

    @Autowired
    UserRepo userRepo;

    @Autowired
    RabbitConfig rabbitConfig;

    @Nullable
    public User login(String name, String password) throws Exception {
        UserInfo u = userRepo.getUserByName(name);
        if (u != null) {
            String dbPassword = u.getPassword();
            if (!PasswordUtils.validatePassword(password, dbPassword)) {
                throw new Exception("密码错误");
            }
            User user = new User();
            user.setId(u.getUserID());
            user.setName(u.getUserName());
            user.setRole(u.getRole());
            LogUtil.d(TAG, "UserInfo=" + user);
            return user;
        }
        return null;
    }

    public UserInfo[] getAll() {
        return userRepo.getAll();
    }


    public String buildToken(String userID) {
        return JwtUtils.sign(userID);
    }

    public void mq() throws JsonProcessingException {
        LogUtil.d(TAG, "on mq");
        LogUtil.d(TAG, "RabbitConfig is:" + rabbitConfig);
        RabbitSenderUtil mqUtil = new RabbitSenderUtil(rabbitConfig);
        mqUtil.publish("hello");
        UserInfo user = new UserInfo();
        user.setUserID(1);
        user.setUserName("zhangyf343");
        user.setRole("user");
        onCreateUserSuccess(user);
    }

    public void onCreateUserSuccess(UserInfo user) throws JsonProcessingException {
        LogUtil.d(TAG, "on onCreateUserSuccess");
        LogUtil.d(TAG, "RabbitConfig is:" + rabbitConfig);
        RabbitSenderUtil mqUtil = new RabbitSenderUtil(rabbitConfig);
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(user);
        objectMapper.writeValueAsString(user);
        mqUtil.publish(str);
    }
}
