package com.yumfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumfood.entity.DishFlavor;
import com.yumfood.mapper.DishFlavorMapper;
import com.yumfood.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
