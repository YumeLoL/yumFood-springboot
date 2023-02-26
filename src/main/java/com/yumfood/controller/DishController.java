package com.yumfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yumfood.common.R;
import com.yumfood.entity.Category;
import com.yumfood.entity.Dish;
import com.yumfood.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    public R<Page<Dish>> page(int page, int pageSize, String name){
        Page<Dish> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        queryWrapper.orderByAsc(Dish::getSort);

        dishService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    @PostMapping
    public R<String> save(@RequestBody Dish dish){
        dishService.save(dish);

        return R.success("Add a new dish successful");
    }

}
