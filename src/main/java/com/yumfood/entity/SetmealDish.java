package com.yumfood.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * setmeal: promos with dishes
 */
@Data
public class SetmealDish implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;


    private Long setmealId;


    private Long dishId;


    private String name;

    private BigDecimal price;

    private Integer amount;


    private Integer sort;


    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


}
