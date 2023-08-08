package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.entity.Orders;

@Mapper
public interface OrderMapper {

    /**
     * 订单表插入一条数据
     * 
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * 
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * 
     * @param orders
     */
    void update(Orders orders);

}
