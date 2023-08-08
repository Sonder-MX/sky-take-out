package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.OrderDetail;

@Mapper
public interface OrderDetailMapper {

    /**
     * 订单详情表批量插入数据
     * 
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);

}
