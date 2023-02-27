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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private SetmealService setmealService;
    @Autowired
    private DishService dishService;


    /**
     * Delete a category based on its id.
     * Check whether there are associated dish or setmeal before deleting the category
     * @param id
     */
    @Override
    public void remove(Long id) {

        // select count(*) from dish where category_id = xxxx;
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int countDish= (int) dishService.count(dishLambdaQueryWrapper);

        // if count > 0, throw exception
        if(countDish > 0){
            throw new CustomException("This category cannot be deleted, as there are associated dishes");
        }


        // select count(*) from setmeal where category_id = xxxx;
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int countSetmeal = (int) setmealService.count(setmealLambdaQueryWrapper);

        // if count > 0, throw exception
        if(countSetmeal > 0){
            throw new CustomException("This category cannot be deleted, as there are associated setmeal");
        }

        // if no any associated item, then delete
        super.removeById(id);
    }
}
