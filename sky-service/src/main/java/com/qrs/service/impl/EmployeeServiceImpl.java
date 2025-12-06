package com.qrs.service.impl;

import com.qrs.dto.EmployeeDto;
import com.qrs.dto.EmployeeEditPasswordDto;
import com.qrs.entity.Employee;
import com.qrs.exception.BusinessException;
import com.qrs.mapper.EmployeeMapper;
import com.qrs.properties.JwtProperties;
import com.qrs.service.EmployeeService;
import com.qrs.utils.JwtUtil;
import com.qrs.vo.EmployeeLoginVO;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final JwtProperties jwtProperties;

    private final PasswordEncoder passwordEncoder;


    @Override
    public EmployeeLoginVO login(EmployeeDto employeeDto) {
        // 参数校验
        if (employeeDto == null || StringUtils.isEmpty(employeeDto.getUsername())
                || StringUtils.isEmpty(employeeDto.getPassword())) {
            throw new BusinessException("用户名和密码不能为空");
        }

        String username = employeeDto.getUsername();
        String password = employeeDto.getPassword();

        // 查询用户
        Employee employee = employeeMapper.login(username);
        if (employee == null) {
            throw new BusinessException("用户名或密码错误");
        }

        // 验证密码
        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        // 检查账号状态
        if (employee.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }

        // 生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("empId", employee.getId());
        claims.put("username", employee.getUsername());

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
        log.info("用户{}登录成功", username);

        return employeeLoginVO;
    }

    @Override
    public void editPassword(EmployeeEditPasswordDto employeeEditPasswordDto) {

        log.info("传入的参数：{}", employeeEditPasswordDto);

//        // 参数校验
//        if (employeeEditPasswordDto == null) {
//            throw new IllegalArgumentException("密码修改信息不能为空");
//        }
//
//        Long empId = employeeEditPasswordDto.getEmpId();
//        String oldPassword = employeeEditPasswordDto.getOldPassword();
//        String newPassword = employeeEditPasswordDto.getNewPassword();
//
//        if (empId == null || StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
//            throw new IllegalArgumentException("必要参数不能为空");
//        }
//
//        // 获取员工信息
//        Employee employee = employeeMapper.selectById(empId);
//        if (employee == null) {
//            throw new BusinessException("员工不存在");
//        }
//
//        // 验证旧密码
//        if (!passwordEncoder.matches(oldPassword, employee.getPassword())) {
//            throw new BusinessException("原密码错误");
//        }
//
//        // 新密码强度检查
//        if (!isValidPassword(newPassword)) {
//            throw new BusinessException("新密码不符合要求");
//        }
//
//        // 加密新密码
//        String encodedPassword = passwordEncoder.encode(newPassword);
//
//        // 更新密码
//        employee.setPassword(encodedPassword);
//        employee.setUpdateTime(LocalDate.now());
//        employeeMapper.updateById(employee);
//
//        // 记录日志
//        log.info("员工{}修改密码成功", empId);
    }

    /**
     * 验证密码强度
     */
    private boolean isValidPassword(String password) {
        // 密码长度8-20位，必须包含大小写字母和数字
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$";
        return password != null && password.matches(pattern);
    }

}
