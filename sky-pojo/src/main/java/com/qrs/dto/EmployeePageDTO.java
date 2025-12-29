package com.qrs.dto;

import lombok.Data;

@Data
public class EmployeePageDTO {
    //员工姓名
    private String name;
    //页码
    private Integer page;
    //每页大小
    private Integer pageSize;
}
