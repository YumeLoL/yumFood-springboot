package com.yumfood.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Category
 */
@Data
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer type; // 1 dish; 2 promos

    private String name;

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
