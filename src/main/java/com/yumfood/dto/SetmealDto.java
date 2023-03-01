package com.yumfood.dto;

import com.yumfood.entity.Setmeal;
import com.yumfood.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
