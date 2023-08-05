package com.sky.controller.admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.result.Result;

import lombok.extern.slf4j.Slf4j;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置店铺状态 1: 营业 0: 休息
     * 
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    public Result<String> setShopStatus(@PathVariable Integer status) {
        log.info("设置店铺状态: {}", status == 1 ? "营业" : "休息");
        redisTemplate.opsForValue().set("SHOP_STATUS", status);
        return Result.success();
    }

    /**
     * 获取店铺状态 1: 营业 0: 休息
     * 
     * @return
     */
    @GetMapping("/status")
    public Result<Integer> getShopStatus() {
        Integer status = (Integer) Optional.ofNullable(redisTemplate.opsForValue().get("SHOP_STATUS")).orElse(0);
        log.info("获取店铺状态: {}", status == 1 ? "营业" : "休息");
        return Result.success(status);
    }

}
