package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.enumeration.OperationType;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * 
     * @param id
     * @return
     */
    @Select("select count(id) from setmeal where category_id = #{categoryId}")
    Integer countByCategoryId(Long id);

    /**
     * 根据id查询套餐
     * 
     * @param id
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

}
