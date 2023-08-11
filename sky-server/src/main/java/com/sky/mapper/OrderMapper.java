package com.sky.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
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

    /**
     * 分页条件查询并按下单时间排序
     * 
     * @param ordersPageQueryDTO
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据id查询订单
     * 
     * @param id
     */
    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);

    /**
     * 根据状态统计订单数量
     * 
     * @param status
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 根据状态和下单时间小于查询订单
     * 
     * @param status
     * @param orderTimeLT
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTimeLT}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTimeLT);

    /**
     * 根据动态条件统计营业额
     * 
     * @param map
     */
    Double sumByMap(Map<String, Object> map);

    /**
     * 根据动态条件统计订单数量
     * 
     * @param map
     */
    Integer countByMap(Map<String, Object> map);

    /**
     * 查询商品销量排名
     * 
     * @param begin
     * @param end
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);

}
