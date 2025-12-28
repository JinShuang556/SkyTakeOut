package com.qrs.service;

import com.qrs.dto.EmployeeDto;
import com.qrs.dto.EmployeeLoginDto;
import com.qrs.dto.EmployeeEditPasswordDto;
import com.qrs.dto.PageDto;
import com.qrs.entity.Employee;
import com.qrs.vo.PageVO;

public interface EmployeeService {
    Employee login(EmployeeLoginDto employeeLoginDto);

    void editPassword(EmployeeEditPasswordDto employeeEditPasswordDto);

    void save(EmployeeDto employeeDto);

    PageVO page(PageDto pageDto);
}
