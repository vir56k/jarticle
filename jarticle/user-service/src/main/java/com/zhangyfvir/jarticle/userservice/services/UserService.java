package com.zhangyfvir.jarticle.userservice.services;

import com.zhangyfvir.jarticle.userservice.components.JwtUtils;
import com.zhangyfvir.jarticle.userservice.entity.UserInfo;
import com.zhangyfvir.jarticle.utils.LogUtil;
import io.micrometer.core.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String TAG = UserService.class.getSimpleName();

    @Nullable
    public UserInfo login(String name, String password) {
        if ("zhangyf".equals(name) && "123456".equals(password)) {
            UserInfo u = new UserInfo();
            u.setId(1);
            u.setName("zhangyf");
            LogUtil.d(TAG, "UserInfo=" + u);
            return u;
        }
        return null;
    }

    public String buildToken(String userID) {
        return JwtUtils.sign(userID);
    }
}
