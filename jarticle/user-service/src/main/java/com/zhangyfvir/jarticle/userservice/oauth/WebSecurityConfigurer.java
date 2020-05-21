//package com.zhangyfvir.jarticle.userservice.oauth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
///**
// * 为了配置你的 OAuth2 服务器迚行用户 ID 认证
// */
//@Configuration
//public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
//
//    /**
//     * Spring Security 使用 AuthenticationManagerBean 来处理身份验证。
//     *
//     * @return
//     * @throws Exception
//     */
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    /**
//     * Spring Security 使用 UserDetailsService 来处理 Spring Security 返回的用户信息。
//     *
//     * @return
//     * @throws Exception
//     */
//    @Override
//    @Bean(name = "n_userDetailsServiceBean")
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    /**
//     * configure() 方法将定义用户、密码和角色。
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("zhangyf")
//                .password("123456")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("123456")
//                .roles("USER", "ADMIN");
//    }
//}
