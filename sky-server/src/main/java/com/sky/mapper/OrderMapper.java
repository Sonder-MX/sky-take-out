package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.Orders;

@Mapper
public interface OrderMapper {

    /**
     * 订单表插入一条数据
     * 
     * @param orders
     */
    void insert(Orders orders);

}
