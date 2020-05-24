package com.zhangyfvir.jarticle.userservice.services;

import com.zhangyfvir.jarticle.userservice.components.JwtUtils;
import com.zhangyfvir.jarticle.userservice.components.PasswordUtils;
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
}
