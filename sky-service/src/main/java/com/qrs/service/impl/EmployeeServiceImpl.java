package com.qrs.service.impl;

import com.qrs.dto.EmployeeDto;
import com.qrs.entity.Employee;
import com.qrs.mapper.EmployeeMapper;
import com.qrs.properties.JwtProperties;
import com.qrs.service.EmployeeService;
import com.qrs.utils.JwtUtil;
import com.qrs.vo.EmployeeLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    private final JwtProperties jwtProperties;


    @Override
    public EmployeeLoginVO login(EmployeeDto employeeDto) {

        String username = employeeDto.getUsername();
        String password = employeeDto.getPassword();

        Employee employee = employeeMapper.login(username);

        if(employee==null || !employee.getPassword().equals(password)){
            System.out.println("用户名或密码错误");
            return null;
        }
        //账号密码正确，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("empId", employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl()
                ,claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return employeeLoginVO;
    }
}
