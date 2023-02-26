package com.yumfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumfood.entity.Category;

public interface CategoryService extends IService<Category> {

    void remove(Long ids);

}
