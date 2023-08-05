package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.entity.User;

@Mapper
public interface UserMapper {

    /**
     * 根据openid查询用户
     * 
     * @param openid
     * @return
     */
    @Select("select * from user where openid = #{openid}")
    public User getByOpenid(String openid);

    /**
     * 插入用户
     * 
     * @param user
     */
    public void insert(User user);
}
