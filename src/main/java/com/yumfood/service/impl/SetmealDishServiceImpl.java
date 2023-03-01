package com.yumfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumfood.entity.SetmealDish;
import com.yumfood.mapper.SetmealDishMapper;
import com.yumfood.service.SetmealDishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {
}
