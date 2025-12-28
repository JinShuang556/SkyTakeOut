package com.qrs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工修改信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeUpdateDTO {
    //员工id
    private Long id;
    //账号名
    private String username;
    //员工姓名
    private String name;
    //联系电话
    private String phone;
    //性别
    private String sex;
    //身份证号
    private String idNumber;

}
