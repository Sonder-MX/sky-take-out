package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.SetmealDish;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询套餐id
     * 
     * @param dishId
     * @return
     */
    List<Long> getSetmealIdByDishIds(List<Long> dishIds);

    /**
     * 批量插入
     * 
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
