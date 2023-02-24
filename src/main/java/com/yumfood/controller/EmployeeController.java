package com.yumfood.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yumfood.common.R;
import com.yumfood.entity.Employee;
import com.yumfood.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * login
     * 1. hash password - md5
     * 2. search and check username, password, status
     * 3. save user info into session
     * @param employee
     * @param request
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request){
        log.info("employee login: {}", employee);

        // hash user password by md5
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        // search the username in database
        // sql: SELECT * FROM Employee WHERE username = '?'
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(employeeLambdaQueryWrapper);

        // if there is no such username exists in the database
        if(emp == null){
            return R.error("The account does not exist!");
        }

        // check password
        if(!emp.getPassword().equals(password)){
            return R.error("Wrong password");
        }

        // check user account status
        if(emp.getStatus() == 0){
            return R.error("This account has been disabled");
        }

        // if login, save employee info in session
        request.getSession().setAttribute("employee", emp.getId());

        return R.success(emp);
    }


    /**
     * remove 'employee' in session
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){

        request.getSession().removeAttribute("employee");

        return R.success("Logout successful");
    }

}
