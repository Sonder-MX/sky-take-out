package com.sky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sky.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量新增菜品口味
     * 
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

}
