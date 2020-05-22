# jarticle
一个java微服务实现的markdown管理工具

# web应用功能概要
1. 使用 markdown 格式编辑文章，保存文章
2. 浏览文章
3. 文章在本地以文件形式存在，便于管理
4. 博客园支持MetaWeblog，据此实现和博客园文章同步,新建/编辑/删除文章( 进度10% )

# 技术栈
## 后端（微服务架构，使用Docker实现容器化）
- 使用 SpringBoot 作为微服务框架
- 使用 JSON 协议实现序列化
- 使用 Docker 实现容器化，docker-compose 容器编排管理
- 事件驱动的架构，使用 RabbitMQ 作为消息队列，当注册用户成功时发送消息，Email-service读取消息发送通知邮件
- 使用 makefile 实现构建管理，生成docker镜像和端口映射。
- 使用 Maven 方式管理依赖
- 采用 zuul 微服务网关，CORS 处理跨域请求
- 基于 RESTful 风格的 HTTP API 。
- 使用 flexmark 将 markdown 格式文本转成HTML
- 使用 XML-RPC 的 MetaWeblog 协议接口，实现和博客园文章同步
- 使用 JWT 实现tokent 生成和校验，使用 jwt-go 框架
- 使用 UUID 作为用户唯一标识

## 前端
- React 开发实现前端分离
- React Hook 简化状态管理
- React Router 路由 V4.0
- for-editor 作为 markdown 编辑器
- 多环境构建（开发，测试，线上）
- 支持基于 token 的登录，退出登录功能

# 整体结构
采用“前后端分离”的结构：
- server 文件夹 下是服务端代码，用go语言实现。
- web 文件夹 下是前端代码，使用 react 开发。

# 微服务架构
使用 go-micro 实现微服务架构，将功能分解到各个离散的服务中以实现对解决方案的解耦，主要有：
- article-service 文章服务，提供文章的增删改查等数据访问。
- user-service    用户服务，提供用户管理，和 token 的生成和校验。
- web-app         使用 iris 实现的web应用，提供 HTTP RESTful 风格API ，供web调用。
- email-service   消息队列，当注册用户成功时发送消息，Email-service读取消息发送通知邮件
- sync-service    同步服务，将文章同步到第三方博客平台。（开发中）

# 容器化
使用 docker 实现容器化，简化和方便于微服务架构下的环境配置。
编写 makefile 脚本，实现自动生成容器，和快速启动多个服务。
docker-compose 分组管理多个镜像和依赖。

# 事件驱动的架构
事件驱动的架构，使用 RabbitMQ 作为消息队列，当注册用户成功时发送消息，Email-service读取消息发送通知邮件
使得当用户注册完成时，无需等待 通知邮件发送完成即进行下一步任务。邮件通知微服务会异步完成发送邮件的任务。

# 数据库
使用 PostgreSQL 数据库，使用 gORM 作为数据访问框架。

# 关于
- 欢迎关注我的Github，网址: https://github.com/vir56k
- 欢迎关注我的技术blog，网址：https://www.jianshu.com/u/b05ccb1463c2
- markdown 编辑器 for-editor 地址：https://github.com/kkfor/for-editor
- MetaWeblog协议资料，请阅读:
https://github.com/vir56k/goarticle/blob/master/server/goarticle/MetaWeblog%E5%8D%8F%E8%AE%AE%E8%B5%84%E6%96%99.md

- Go 网址资料： http://docscn.studygolang.com/doc/
- Go 中文文档:  https://studygolang.com/pkgdoc
- Go ORM 文件： http://gorm.book.jasperxu.com/
