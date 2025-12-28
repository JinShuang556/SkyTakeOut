package com.qrs.mapper;

import com.github.pagehelper.Page;
import com.qrs.dto.PageDTO;
import com.qrs.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名查询员工信息
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
    Integer updateById(Employee employee);

    @Insert("insert into employee (id_number, name, phone, sex, username, password, status, create_time, update_time, create_user,update_user) " +
            "value (#{idNumber}, #{name}, #{phone}, #{sex}, #{username}, #{password}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Employee employee);

    Page<Employee> page(PageDTO pageDto);
}
