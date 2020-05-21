package com.zhangyfvir.jarticle.userservice.controller;

import com.zhangyfvir.jarticle.common.entity.Result;
import com.zhangyfvir.jarticle.utils.Const;
import com.zhangyfvir.jarticle.utils.LogUtil;
import com.zhangyfvir.jarticle.utils.UserIDHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static final String TAG = "UserController";

    @RequestMapping(value = "/self", produces = Const.CONTENT_TYPE_JSON)
    public Result self(HttpServletRequest req) {
        //LogUtil.d(TAG, "/self");
        String userID = UserIDHelper.getAttribute(req);
        LogUtil.d(TAG, "## userID=%s ", userID);
        HashMap<String, String> map = new HashMap<>();
        map.put("name", userID);
        map.put("time", new Date().getTime() + "");
        return Result.success(map);
    }
}

