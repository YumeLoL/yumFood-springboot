package com.yumfood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yumfood.entity.Employee;
import com.yumfood.mapper.EmployeeMapper;
import com.yumfood.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
