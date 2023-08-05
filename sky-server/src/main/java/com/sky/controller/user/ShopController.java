package com.sky.controller.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sky.result.Result;

import lombok.extern.slf4j.Slf4j;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
public class ShopController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取店铺状态 1: 营业 0: 休息
     * 
     * @return
     */
    @GetMapping("/status")
    public Result<Integer> getShopStatus() {
        Integer status = Optional.ofNullable((Integer) redisTemplate.opsForValue().get("SHOP_STATUS")).orElse(0);
        log.info("获取店铺状态: {}", status == 1 ? "营业" : "休息");
        return Result.success(status);
    }

}
