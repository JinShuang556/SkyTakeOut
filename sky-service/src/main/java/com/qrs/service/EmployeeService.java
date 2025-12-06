package com.qrs.service;

import com.qrs.dto.EmployeeDto;
import com.qrs.dto.EmployeeEditPasswordDto;
import com.qrs.vo.EmployeeLoginVO;

public interface EmployeeService {
    EmployeeLoginVO login(EmployeeDto employeeDto);

    void editPassword(EmployeeEditPasswordDto employeeEditPasswordDto);
}
