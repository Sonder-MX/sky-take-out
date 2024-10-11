package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 菜品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    //菜品名称
    private String name;

    //菜品分类id
    private Long categoryId;

    //菜品价格
    private BigDecimal price;

    //图片
    private String image;

    //描述信息
    private String description;

    //0 停售 1 起售
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Long createUser;

     //甜品id
    private Long desdishId;

    //甜品套餐id
    private Long dessetmealId;

    //甜品类别
    private string descategory;
  
    //口味
    private String desdishFlavor;

    //数量
    private Integer desnumber;

    //价格
    private BigDecimal desamount;

    //会员价格
    private int desvipcost;

    //打折力度
    private int count;
  
    //甜品图片
    private String desimage;


    private Long updateUser;

}
