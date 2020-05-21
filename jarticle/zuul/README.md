# 启动

xxx



# 测试是否可用
浏览器打开：http://localhost:11111/api/article/public/articles/1


# https://blog.csdn.net/hdn_kb/article/details/93880252

参考：https://blog.csdn.net/hdn_kb/article/details/93880252
1、首先我们来查看以下actuator对外暴露的端点信息（以GET方式访问）：
    http://localhost:5555/actuator（5555端口号根据自己的配置文件种的设置），发现没有/routes端点：
    
  （1）、将所有的端点都暴露出来
   management.endpoints.web.exposure.include=*
   （2）、只将/routes端点暴露出来
   management.endpoints.web.exposure.include=routes
  
           
```
# 开放健康检查接口
                         management:
                           endpoints:
                             web:
                               exposure:
                                 include: "routes"
```

在浏览器查看
  http://localhost:11111/actuator/routes
  http:/