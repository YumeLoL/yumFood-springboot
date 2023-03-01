package com.yumfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yumfood.common.R;
import com.yumfood.dto.DishDto;
import com.yumfood.entity.Category;
import com.yumfood.entity.Dish;
import com.yumfood.service.CategoryService;
import com.yumfood.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    /**
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page<DishDto>> page(int page, int pageSize, String name){
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        queryWrapper.orderByAsc(Dish::getSort);

        dishService.page(pageInfo,queryWrapper);

        //copy dish to dishDto
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");

        //copy 'records' separately
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list=records.stream().map((item)->{
            DishDto dishDto=new DishDto();

            BeanUtils.copyProperties(item,dishDto);
            Long categoryId = item.getCategoryId();
            // to set categoryName by id
            Category category = categoryService.getById(categoryId);
            if(category!=null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);

        return R.success(dishDtoPage);
    }

    /**
     * add a new dish
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getId, dishDto.getCategoryId());
        String categoryName = categoryService.getOne(queryWrapper).getName();
        dishDto.setCategoryName(categoryName);

        dishService.saveWithFlavor(dishDto);

        return R.success("Add a new dish successful");
    }


    /**
     * get dish and flavor info by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> getByid(@PathVariable Long id){
        DishDto dishDto = dishService.getWithFlavor(id);
        return R.success(dishDto);
     }


     @PutMapping
    public R<String> update(@RequestBody DishDto dishDto){
         dishService.updateWithFlavor(dishDto);
         return R.success("Update dish successful");
     }
}
