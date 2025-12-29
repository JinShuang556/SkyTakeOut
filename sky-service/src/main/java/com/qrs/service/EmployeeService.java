package com.qrs.service;

import com.qrs.dto.*;
import com.qrs.entity.Employee;
import com.qrs.vo.PageVO;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDto);

    Integer editPassword(EmployeeEditPasswordDTO employeeEditPasswordDto);

    void save(EmployeeDTO employeeDto);

    PageVO page(EmployeePageDTO employeePageDto);

    void StartORStop(Integer status, Long id);

    void updateEmployee(EmployeeUpdateDTO employeeUpdateDto);

    Employee selectById(Long id);
}
