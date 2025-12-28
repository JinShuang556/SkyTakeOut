package com.qrs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qrs.constant.PasswordConstant;
import com.qrs.constant.StatusConstant;
import com.qrs.context.BaseContext;
import com.qrs.dto.*;
import com.qrs.entity.Employee;
import com.qrs.exception.BusinessException;
import com.qrs.mapper.EmployeeMapper;
import com.qrs.service.EmployeeService;
import com.qrs.vo.PageVO;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDto) {
        // 参数校验
        if (employeeLoginDto == null || StringUtils.isEmpty(employeeLoginDto.getUsername())
                || StringUtils.isEmpty(employeeLoginDto.getPassword())) {
            throw new BusinessException("用户名和密码不能为空");
        }

        String username = employeeLoginDto.getUsername();
        String password = employeeLoginDto.getPassword();

        // 查询用户
        Employee employee = employeeMapper.login(username);
        if (employee == null) {
            throw new BusinessException("用户名或密码错误");
        }

        // 验证密码
        if (!password.equals(employee.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        // 检查账号状态
        if (employee.getStatus() == 0) {
            throw new BusinessException("账号已被禁用");
        }
        return employee;
    }

    @Override
    public Integer editPassword(EmployeeEditPasswordDTO employeeEditPasswordDto) {

        log.info("传入的参数：{}", employeeEditPasswordDto);

        // 参数校验
        if (employeeEditPasswordDto == null) {
            throw new IllegalArgumentException("密码修改信息不能为空");
        }

        Long empId = employeeEditPasswordDto.getEmpId();
        String oldPassword = employeeEditPasswordDto.getOldPassword();
        String newPassword = employeeEditPasswordDto.getNewPassword();

        if (empId == null || StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            throw new IllegalArgumentException("必要参数不能为空");
        }

        // 获取员工信息
        Employee employee = employeeMapper.selectById(empId);
        if (employee == null) {
            throw new BusinessException("员工不存在");
        }

        // 验证旧密码
        String oldPassword1 = employee.getPassword();
        if(!oldPassword1.equals(oldPassword)){
            throw new BusinessException("旧密码错误");
        }



//        // 新密码强度检查
//        if (!isValidPassword(newPassword)) {
//            throw new BusinessException("新密码不符合要求");
//        }


        // 更新密码
        employee.setPassword(newPassword);
        employee.setUpdateTime(LocalDateTime.now());
        Integer status = employeeMapper.updateById(employee);

        // 记录日志
        log.info("员工{}修改密码成功", empId);

        return status;
    }

    @Override
    public void save(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        //属性拷贝
        BeanUtils.copyProperties(employeeDto, employee);

        //设置账号状态
        employee.setStatus(StatusConstant.ENABLED);

        // 设置默认密码
        employee.setPassword(PasswordConstant.DEFAULT_PASSWORD);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置创建人id
        employee.setCreateUser(BaseContext.getCurrentId());
        employee.setUpdateUser(BaseContext.getCurrentId());

        //调用Mapper
        employeeMapper.insert(employee);

    }

    @Override
    public PageVO page(PageDTO pageDto) {
        PageHelper.startPage(pageDto.getPage(),pageDto.getPageSize());
        // 查询所有员工
        Page<Employee> p =  employeeMapper.page(pageDto);
        // 封装分页数据
        PageVO pageVO = PageVO.builder()
                .total(p.getTotal())
                .records(p.getResult())
                .build();
        return pageVO;
    }

    @Override
    public void StartORStop(Integer status, Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setStatus(status);

        employeeMapper.updateById(employee);
    }

    @Override
    public void updateEmployee(EmployeeUpdateDTO employeeUpdateDto) {
//        Employee employee = Employee.builder()
//                .id(employeeUpdateDto.getId())
//                .username(employeeUpdateDto.getUsername())
//                .name(employeeUpdateDto.getName())
//                .phone(employeeUpdateDto.getPhone())
//                .sex(employeeUpdateDto.getSex())
//                .idNumber(employeeUpdateDto.getIdNumber())
//                .updateTime(LocalDateTime.now())
//                .updateUser(BaseContext.getCurrentId())
//                .build();

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeUpdateDto, employee);
        employeeMapper.updateById(employee);
    }

    @Override
    public Employee selectById(Long id) {
        return employeeMapper.selectById(id);
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
