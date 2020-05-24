package com.zhangyfvir.jarticle.userservice.repo;

import com.zhangyfvir.jarticle.userservice.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepo {
    @Select("select * from userInfo Where userName = #{userName}")
    UserInfo getUserByName(String userName);

    @Select("select * from userInfo")
    UserInfo[] getAll();
}
