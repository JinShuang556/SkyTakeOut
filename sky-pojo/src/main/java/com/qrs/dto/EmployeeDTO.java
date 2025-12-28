package com.qrs.dto;

import lombok.Data;

/**
 * 新增员工信息
 *
 */

@Data
public class EmployeeDTO {
    private String IdNumber; //身份证
    private String name; //姓名
    private String phone; //电话
    private String sex; //性别
    private String username; //用户名

}
