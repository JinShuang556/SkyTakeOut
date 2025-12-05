package com.qrs.controller.admin;

import com.qrs.dto.EmployeeDto;
import com.qrs.result.Result;
import com.qrs.service.EmployeeService;
import com.qrs.vo.EmployeeLoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/login")
    public Result<EmployeeLoginVO> Login(@RequestBody EmployeeDto employeeDto){
        log.info("员工登录:{}",employeeDto);
        EmployeeLoginVO employeeLoginVO = employeeService.login(employeeDto);
        return Result.success(employeeLoginVO);
    }
}
