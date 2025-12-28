package com.qrs.controller.admin;

import com.qrs.constant.JwtClaimsConstant;
import com.qrs.constant.ResultConstant;
import com.qrs.dto.*;
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

    /**
     * 员工登录
     * @param employeeLoginDto 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> Login(@RequestBody EmployeeLoginDTO employeeLoginDto){
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

    /**
     * 员工登出
     * @return 登出结果
     */
    @PostMapping("/logout")
    public Result logout(){
        log.info("员工登出");
        return Result.success();
    }

    /**
     *  修改密码
     * @param employeeEditPasswordDto 新旧密码和用户
     * @return 修改结果
     */
    @PutMapping("/editPassword")
    public Result editPassword(@RequestBody EmployeeEditPasswordDTO employeeEditPasswordDto){
        log.info("修改密码");
        Integer status =  employeeService.editPassword(employeeEditPasswordDto);
        if(status>=ResultConstant.SUCCESS){
            return Result.success();
        }else{
            return Result.error("修改失败");
        }
    }

    /**
     * 新增员工
     * @param employeeDto 员工信息
     * @return 新增结果
     */
    @PostMapping
    public Result save(@RequestBody EmployeeDTO employeeDto){
        log.info("新增员工");
        employeeService.save(employeeDto);
        return Result.success();
    }

    /**
     * 分页查询员工
     * @param pageDto 分页参数
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result page(PageDTO pageDto){
        log.info("分页查询员工：{}",pageDto);
        PageVO pageVO = employeeService.page(pageDto);
        return Result.success(pageVO);
    }

    /**
     * 修改员工状态
     * @param status 状态
     * @param id 员工id
     * @return 修改结果
     */
    @PostMapping("/status/{status}")
    public Result StartORStop(@PathVariable Integer status ,Long id) {
        log.info("修改员工状态：{}，{}",status,id);
        employeeService.StartORStop(status,id);
        return Result.success();
    }

    /**
     * 修改员工信息
     * @param employeeUpdateDto 员工信息
     * @return 修改结果
     */
    @PutMapping()
    public Result updateEmployee(@RequestBody EmployeeUpdateDTO employeeUpdateDto) {
        log.info("修改员工：{}",employeeUpdateDto);
        employeeService.updateEmployee(employeeUpdateDto);
        return Result.success();
    }

    /**
     * 根据id查询员工
     * @param id 员工id
     * @return 员工信息
     */
    @GetMapping("/{id}")
    public Result<Employee> selectById(@PathVariable Long id) {
        log.info("查询员工：{}",id);
        Employee employee = employeeService.selectById(id);
        employee.setPassword("******");
        return Result.success(employee);
    }

}
