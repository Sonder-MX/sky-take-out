package com.sky.mapper;

import java.util.Map;

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

    /**
     * 根据用户id查询用户
     * 
     * @param userId
     * @return
     */
    @Select("select * from user where id = #{userId}")
    public User getById(Long userId);

    /**
     * 根据动态条件统计用户数量
     * 
     * @param map
     * @return
     */
    Integer countByMap(Map<String, Object> map);
}
