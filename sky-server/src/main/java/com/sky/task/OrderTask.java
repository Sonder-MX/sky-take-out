package com.sky.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单
     * 
     * @return
     */
    @Scheduled(cron = "0 * * * * ?") // 每分钟执行一次
    public void processTimeoutOrder() {
        log.info("处理超时订单");
        LocalDateTime tm = LocalDateTime.now().plusMinutes(-15);
        List<Orders> odList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, tm);
        if (odList != null && odList.size() > 0) {
            odList.forEach(od -> {
                od.setStatus(Orders.CANCELLED);
                od.setCancelReason("超时未支付");
                od.setCancelTime(LocalDateTime.now());
                orderMapper.update(od);
            });
        }
    }

    /**
     * 处理派送中订单
     * 
     * @return
     */
    @Scheduled(cron = "0 0 1 * * ?") // 每天凌晨1点执行一次
    public void processDeliveryOrder() {
        log.info("处理派送中订单");
        LocalDateTime tm = LocalDateTime.now().plusHours(-1);
        List<Orders> odList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, tm);
        if (odList != null && odList.size() > 0) {
            odList.forEach(od -> {
                od.setStatus(Orders.COMPLETED);
                od.setCancelReason("超时未确认收货");
                od.setCancelTime(LocalDateTime.now());
                orderMapper.update(od);
            });
        }
    }
}
