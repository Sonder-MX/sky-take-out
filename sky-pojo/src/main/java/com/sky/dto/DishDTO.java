package com.sky.dto;

import com.sky.entity.DishFlavor;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {

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
    //口味
    private List<DishFlavor> flavors = new ArrayList<>();
    //推荐套餐
    private String Recommenddish;

    /*
    public class Heart extends JFrame {
    public static void main(String[] args) {
        Heart.heart2();
    }
 
    public static  void heart2(){
        double x,y,a;
        char s[]=new char[]{'I',' ','l','o','v','e',' ','y','o','u','!'};
        int index=0;
        for(y=1.3f;y>-1.1f;y-=0.06f){
            index=0;
            for(x=-1.1f;x<=1.1f;x+=0.025f){
                double result=x*x+pow((5.0*y/4.0-sqrt(abs(x))),2);
                if(result<=1){
                    System.out.print((s[index]));
                    index=(index+1)%11;
                }
                else{
                    System.out.print(' ');
                }
            }
            System.out.println(" ");
        }
    }
}
    */

}
