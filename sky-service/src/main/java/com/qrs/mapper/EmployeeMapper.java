package com.qrs.mapper;

import com.qrs.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("select * from employee where username=#{username}")
    Employee login(String username);
}
