package com.sky.service;

import java.util.List;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

public interface DishService {
    /**
     * 新增菜品以及保存口味
     * 
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * 分页查询菜品
     * 
     * @param dishPageQueryDTO
     */
    public PageResult<DishVO> queryPage(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 删除菜品
     * 
     * @param ids
     */
    public void deleteBatch(List<Long> ids);
}
