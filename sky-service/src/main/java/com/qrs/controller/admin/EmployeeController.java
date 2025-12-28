package com.qrs.controller.admin;

import com.qrs.constant.JwtClaimsConstant;
import com.qrs.dto.EmployeeDto;
import com.qrs.dto.EmployeeLoginDto;
import com.qrs.dto.EmployeeEditPasswordDto;
import com.qrs.dto.PageDto;
import com.qrs.entity.Employee;
import com.qrs.properties.JwtProperties;
import com.qrs.result.Result;
import com.qrs.service.EmployeeService;
import com.qrs.utils.JwtUtil;
import com.qrs.vo.EmployeeLoginVO;
import com.qrs.vo.PageVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JwtProperties jwtProperties;


    @PostMapping("/login")
    public Result<EmployeeLoginVO> Login(@RequestBody EmployeeLoginDto employeeLoginDto){
        log.info("员工登录:{}", employeeLoginDto);
        Employee employee = employeeService.login(employeeLoginDto);

        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        claims.put(JwtClaimsConstant.USERNAME, employee.getUsername());



        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        // 构建返回对象
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        // 记录登录日志
        log.info("用户{}登录成功", employee.getUsername());


        return Result.success(employeeLoginVO);
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

    @PostMapping
    public Result save(@RequestBody EmployeeDto employeeDto){
        log.info("新增员工");
        employeeService.save(employeeDto);
        return Result.success();
    }

    @GetMapping("/page")
    public Result page(PageDto pageDto){
        log.info("分页查询员工：{}",pageDto);
        PageVO pageVO = employeeService.page(pageDto);
        return Result.success(pageVO);
    }

}
