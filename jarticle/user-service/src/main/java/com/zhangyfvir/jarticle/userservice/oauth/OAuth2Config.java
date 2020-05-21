//package com.zhangyfvir.jarticle.userservice.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//
///**
// * 这个类将定义哪些应用程序在你的 OAuth2 认证服务注册。
// */
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    /**
//     * 这定义了哪些客户端将被你 的服务注册
//     *
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()  //它支持应用程序信息的两种不同的存储类型:内存存 储和 JDBC 存储。
//                .withClient("my_client_id") //应用程序名称
//                .secret("my_secret")    // 秘钥
//                .authorizedGrantTypes( // 一个逗号分隔的授权批准类型的列 表，将是你的 OAuth2 服务支持列表
//                        "refresh_token",
//                        "password", // 密码
//                        "client_credentials") // 和客户凭证授权。
//                .scopes("webclient", "mobileclient"); //作用域，web端，和移动端
//
//        //通过定义作用域，你可以编写特定于客户端应用程序正在工作的范围 的授权规则。
//    }
//
//    /**
//     * 该方法定义了在 AuthenticationServerConfigurer 使用的不同组件。
//     * 这个代码告诉 Spring 使用 Spring 自带的默认身份 验证管理器和用户详细信息服务。
//     *
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager)
//                .userDetailsService(userDetailsService);
//    }
//}
