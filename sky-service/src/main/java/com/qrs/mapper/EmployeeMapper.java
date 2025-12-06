package com.qrs.mapper;

import com.qrs.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("select * from employee where username=#{username}")
    Employee login(String username);

    @Select("select * from employee where id=#{id}")
    Employee selectById(Long id);

    /**
     * 根据ID动态更新员工信息
     * @param employee 员工信息
     * @return 更新结果
     */
    int updateById(Employee employee);
}
