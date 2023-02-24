package com.yumfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumfood.entity.Category;
import com.yumfood.mapper.CategoryMapper;
import com.yumfood.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{


}
