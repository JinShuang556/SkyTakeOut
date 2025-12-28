package com.qrs.dto;

import lombok.Data;

@Data
public class PageDTO {
    //员工姓名
    private String name;
    //页码
    private Integer page;
    //每页大小
    private Integer pageSize;
}
