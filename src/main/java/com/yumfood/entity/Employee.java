package com.yumfood.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * employee entity
 */
@Data
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String sex;

    private Integer status;

    @TableField(fill = FieldFill.INSERT) //insert autofill
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //insert & update autofill
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT) //insert autofill
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE) //insert & update autofill
    private Long updateUser;

}
