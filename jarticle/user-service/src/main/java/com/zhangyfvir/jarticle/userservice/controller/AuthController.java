package com.zhangyfvir.jarticle.userservice.controller;

import com.zhangyfvir.jarticle.common.entity.Result;
import com.zhangyfvir.jarticle.userservice.components.JwtUtils;
import com.zhangyfvir.jarticle.userservice.entity.AuthData;
import com.zhangyfvir.jarticle.userservice.entity.UserInfo;
import com.zhangyfvir.jarticle.userservice.services.UserService;
import com.zhangyfvir.jarticle.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * 授权管理
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private static final String TAG = AuthController.class.getSimpleName();
    private UserService userService;


    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login")
    public Result login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password) {

        PrintUtil.print("request.getHeaderNames()", request.getHeaderNames());

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return Result.error(500, "参数错误");
        }
        UserInfo user = userService.login(username, password);
        if (user != null) {
            String token = userService.buildToken(user.getId() + "");
            AuthData authData = new AuthData(token);
            authData.setUserInfo(user);
            return Result.success(authData);
        }
        LogUtil.d(TAG, "login failure");
        return Result.error(500, "login failure");
    }

    private UserInfo getUserInfo() {
        return null;
    }

    @RequestMapping(value = "/verify", produces = Const.CONTENT_TYPE_JSON)
    public Result verify(
            @RequestParam(required = true) String token) {
        if (TextUtils.isEmpty(token)) {
            return Result.error(500, "参数错误");
        }
        boolean verify = JwtUtils.verify(token);
        LogUtil.d(TAG, "verify=" + verify);
        HashMap<String, Boolean> res = new HashMap<>();
        res.put("verify", verify);
        return Result.success(res);
    }

}
