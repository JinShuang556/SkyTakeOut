package com.qrs.mapper;

import com.qrs.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {


    @Select("SELECT * FROM user WHERE openid=#{openid}")
    User selectByOpenid(String openid);

    void insert(User user);
}
