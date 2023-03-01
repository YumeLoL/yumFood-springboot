package com.yumfood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yumfood.dto.SetmealDto;
import com.yumfood.entity.Setmeal;


import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    /**
     * add a new promos with dishes
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * delete with dish
     * @param ids
     */
    public void removeWithDish(List<Long> ids);

}
