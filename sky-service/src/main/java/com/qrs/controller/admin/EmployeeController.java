package com.qrs.controller.admin;

import com.qrs.dto.EmployeeDto;
import com.qrs.dto.EmployeeEditPasswordDto;
import com.qrs.result.Result;
import com.qrs.service.EmployeeService;
import com.qrs.vo.EmployeeLoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> Login(@RequestBody EmployeeDto employeeDto){
        log.info("员工登录:{}",employeeDto);
//        EmployeeLoginVO employeeLoginVO = employeeService.login(employeeDto);
//        return Result.success(employeeLoginVO);
        return Result.success();
    }


    @PostMapping("/logout")
    public Result logout(){
        log.info("员工登出");
        return Result.success();
    }

    @PutMapping("/editPassword")
    public Result editPassword(@RequestBody EmployeeEditPasswordDto employeeEditPasswordDto){
        log.info("修改密码");
        employeeService.editPassword(employeeEditPasswordDto);


        return null;
    }


}
