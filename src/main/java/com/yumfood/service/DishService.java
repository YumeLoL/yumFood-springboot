package com.yumfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumfood.dto.DishDto;
import com.yumfood.entity.Dish;


public interface DishService extends IService<Dish> {

    void saveWithFlavor(DishDto dishDto);

}
