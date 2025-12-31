package com.qrs.service;

import com.qrs.dto.*;
import com.qrs.entity.Employee;
import com.qrs.vo.PageVO;

public interface EmployeeService {
    /**
     * 员工登录
     * @param employeeLoginDto 登录参数
     * @return 员工信息
     */
    Employee login(EmployeeLoginDTO employeeLoginDto);

    /**
     * 修改密码
     * @param employeeEditPasswordDto 修改密码参数
     * @return 修改结果
     */
    Integer editPassword(EmployeeEditPasswordDTO employeeEditPasswordDto);

    /**
     * 保存员工（新增员工）
     * @param employeeDto 员工参数
     */
    void save(EmployeeDTO employeeDto);

    /**
     * 员工分页查询
     * @param employeePageDto 分页参数
     * @return 分页结果
     */
    PageVO page(EmployeePageDTO employeePageDto);

    /**
     * 启动，禁用员工账号
     * @param status 员工状态
     * @param id 员工id
     */
    void StartORStop(Integer status, Long id);

    /**
     * 修改员工信息
     * @param employeeUpdateDto 员工参数
     */
    void updateEmployee(EmployeeUpdateDTO employeeUpdateDto);

    /**
     * 根据id查询员工信息
     * @param id 员工id
     * @return 员工信息
     */
    Employee selectById(Long id);
}
