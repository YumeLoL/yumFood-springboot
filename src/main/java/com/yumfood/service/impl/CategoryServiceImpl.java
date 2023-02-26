package com.yumfood.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumfood.common.CustomException;
import com.yumfood.entity.Category;
import com.yumfood.entity.Dish;
import com.yumfood.entity.Setmeal;
import com.yumfood.mapper.CategoryMapper;
import com.yumfood.service.CategoryService;
import com.yumfood.service.DishService;
import com.yumfood.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long ids) {
        //check dish table, if there is any dish relates to a category, then cannot delete and throw exception
        //sql: select count(*) from dish where category_id = ?;
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, ids);
        int countDish = (int) dishService.count(dishLambdaQueryWrapper);

        if(countDish > 0){
            // trow a custom exception
            throw new CustomException("There are dishes under this category, cannot delete");
        }

        //check dish table, if there is any dish relates to a category, then cannot delete
        //sql: select count(*) from dish where category_id = ?;
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, ids);
        int countSetmeal = (int) setmealService.count(setmealLambdaQueryWrapper);

        if(countSetmeal > 0){
            // trow a custom exception
            throw new CustomException("There are setmeals under this category, cannot delete");
        }

        super.removeById(ids);
    }
}
