package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.annotation.AutoFill;
import com.qrs.dto.EmployeePageDTO;
import com.qrs.entity.Employee;
import com.qrs.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名查询员工信息,实现登录功能
     * @param username 用户名
     * @return 员工信息
     */
    @Select("select * from employee where username=#{username}")
    Employee login(String username);

    /**
     * 根据ID查询员工信息
     * @param id 用户id
     * @return 员工信息
     */
    @Select("select * from employee where id=#{id}")
    Employee selectById(Long id);

    /**
     * 根据ID动态更新员工信息
     * @param employee 员工信息
     * @return 更新结果
     */
    @AutoFill(OperationType.UPDATE)
    Integer updateById(Employee employee);


    /**
     * 插入员工信息
     * @param employee 员工信息
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into employee (id_number, name, phone, sex, username, password, status, create_time, update_time, create_user,update_user) " +
            "value (#{idNumber}, #{name}, #{phone}, #{sex}, #{username}, #{password}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Employee employee);

    /**
     * 分页查询员工信息
     * @param employeePageDto 分页参数
     * @return 员工信息
     */
    Page<Employee> page(EmployeePageDTO employeePageDto);
}
