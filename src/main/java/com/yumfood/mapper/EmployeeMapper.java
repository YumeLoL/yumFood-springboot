package com.yumfood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yumfood.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee>{
}
