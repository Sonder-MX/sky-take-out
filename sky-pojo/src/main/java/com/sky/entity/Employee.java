package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;                                //员工代码

    private String username;                        //用户姓名

    private String name;                            //员工姓名

    private String password;                        //密码

    private String phone;                           //电话号码

    private String sex;                             //性别

    private String idNumber;                        //id账号

    private Integer status;                         //在线状态

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 会导致前端接收到的时间格式不对
    private LocalDateTime createTime;

    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;

}
